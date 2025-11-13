package models;

import exceptions.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Central library manager.
 */
public class Library {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private final List<BorrowRecord> borrowRecords = new ArrayList<>();

    // Add / find / list
    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public Optional<Book> findBook(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    public Collection<Book> listBooks() {
        return Collections.unmodifiableCollection(books.values());
    }

    public void addMember(Member member) {
        members.put(member.getMemberid(), member);
    }

    public Optional<Member> findMember(String memberId) {
        return Optional.ofNullable(members.get(memberId));
    }

    public Collection<Member> listMembers() {
        return Collections.unmodifiableCollection(members.values());
    }

    // Borrow
    public void borrowBook(String memberId, String isbn) throws BusinessException {
        Member member = members.get(memberId);
        if (member == null) throw new MemberNotFoundException("Member not found: " + memberId);

        Book book = books.get(isbn);
        if (book == null) throw new BookNotFoundException("Book not found: " + isbn);

        // Attempt borrow on book (may throw BookNotAvailableException)
        book.borrowOne();

        try {
            member.borrow(isbn); // may throw MaxBooksIssuedException
        } catch (MaxBooksIssuedException ex) {
            // rollback book borrow
            book.returnOne();
            throw ex;
        }

        borrowRecords.add(new BorrowRecord(memberId, isbn, LocalDateTime.now()));
    }

    // Return
    public void returnBook(String memberId, String isbn) throws BusinessException {
        Member member = members.get(memberId);
        if (member == null) throw new MemberNotFoundException("Member not found: " + memberId);

        Book book = books.get(isbn);
        if (book == null) throw new BookNotFoundException("Book not found: " + isbn);

        if (!member.hasborrowed(isbn)) {
            throw new InvalidOperationException("Member " + memberId + " did not borrow book " + isbn);
        }

        member.returned(isbn);
        book.returnOne();

        // remove first matching record
        for (Iterator<BorrowRecord> it = borrowRecords.iterator(); it.hasNext();) {
            BorrowRecord r = it.next();
            if (r.memberId.equals(memberId) && r.isbn.equals(isbn)) {
                it.remove();
                break;
            }
        }
    }

    public List<String> listBorrowRecords() {
        List<String> output = new ArrayList<>();
        for (BorrowRecord r : borrowRecords) {
            output.add(r.toString());
        }
        return Collections.unmodifiableList(output);
    }

    // Simple internal borrow record
    private static class BorrowRecord {
        private final String memberId;
        private final String isbn;
        private final LocalDateTime borrowedAt;

        BorrowRecord(String memberId, String isbn, LocalDateTime borrowedAt) {
            this.memberId = memberId;
            this.isbn = isbn;
            this.borrowedAt = borrowedAt;
        }

        @Override
        public String toString() {
            return String.format("%s borrowed %s at %s", memberId, isbn, borrowedAt);
        }
    }
}
