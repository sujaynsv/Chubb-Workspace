package models;

import java.util.*;
import exceptions.*;

public class Member {
	private final String name;
	private final String memberid;
	private final int borrowlimit;
	
	private final Set<String> borrowedBooks= new HashSet<>();
	
	public Member(String name, String memberid, int borrowlimit) {
		if(name==null||name.isBlank()) {
			throw new IllegalArgumentException("name required");
		}
		if(memberid==null|| memberid.isBlank()) {
			throw new IllegalArgumentException("id required");
		}
		if(borrowlimit<=0) {
			throw new IllegalArgumentException("borrower limit should be greater that 0.");
		}
		this.name=name.trim();
		this.memberid=memberid.trim();
		this.borrowlimit=borrowlimit;
		
	}

	public String getName() {
		return name;
	}

	public String getMemberid() {
		return memberid;
	}

	public int getBorrowlimit() {
		return borrowlimit;
	}
	
	public void borrow(String book) throws MaxBooksIssuedException{
		if(borrowedBooks.size()>=borrowlimit) {
			throw new MaxBooksIssuedException("Member "+memberid+" has reached the borrowling limit");
		}
	}
	
	public void returned(String book) {
		borrowedBooks.remove(book);
	}
	
	public boolean hasborrowed(String book) {
		return borrowedBooks.contains(book);
	}
	
	public int borrowedCount() {
		return borrowedBooks.size();
	}
    public Set<String> getBorrowedIsbns() {
        return Collections.unmodifiableSet(borrowedBooks);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | Borrowed: %d/%d", memberid, name, borrowedBooks.size(), borrowlimit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member other = (Member) o;
        return Objects.equals(memberid, other.memberid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberid);
    }

}
