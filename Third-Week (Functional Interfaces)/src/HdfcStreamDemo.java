import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class HdfcStreamDemo {

    static class Transaction {
        String txId, srcIfsc, destIfsc, employeeName;
        double amount;
        Transaction(String txId, String srcIfsc, String destIfsc, String employeeName, double amount) {
            this.txId = txId;
            this.srcIfsc = srcIfsc;
            this.destIfsc = destIfsc;
            this.employeeName = employeeName;
            this.amount = amount;
        }
        public String toString() {
            return txId + ": " + srcIfsc + " -> " + destIfsc + " | " + employeeName + " : ₹" + amount;
        }
    }

    static List<LocalDate> weekendsInNovember(int year) {
        YearMonth nov = YearMonth.of(year, Month.NOVEMBER);
        LocalDate start = nov.atDay(1);
        LocalDate end = nov.atEndOfMonth();
        return start.datesUntil(end.plusDays(1))
                .filter(d -> d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY)
                .collect(Collectors.toList());
    }

    static double totalAmountForHdfc(List<Transaction> txs) {
        return txs.stream()
                .filter(t -> t.srcIfsc.toUpperCase().contains("HDFC") || t.destIfsc.toUpperCase().contains("HDFC"))
                .mapToDouble(t -> t.amount)
                .sum();
    }

    static double totalAmountWhereSrcIsHdfc(List<Transaction> txs) {
        return txs.stream()
                .filter(t -> t.srcIfsc.toUpperCase().contains("HDFC"))
                .mapToDouble(t -> t.amount)
                .sum();
    }

    static List<String> employeesPaidAtHdfc(List<Transaction> txs) {
        return txs.stream()
                .filter(t -> t.destIfsc.toUpperCase().contains("HDFC"))
                .map(t -> t.employeeName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int year = Year.now().getValue();
        List<LocalDate> weekends = weekendsInNovember(year);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd (E)");
        System.out.println("Weekend dates in November " + year + ":");
        weekends.forEach(d -> System.out.println("  " + d.format(fmt)));

        List<Transaction> txs = Arrays.asList(
                new Transaction("T001", "ACME000001", "HDFC00002131", "Ajay", 23500.00),
                new Transaction("T002", "HDFC00001000", "HDFC00002302", "Roy", 18000.00),
                new Transaction("T003", "ACME000001", "SBI00002301", "Kriti", 20000.00),
                new Transaction("T004", "HDFC00001000", "BOI00002301", "Praveen", 15000.00),
                new Transaction("T005", "ICICI000001", "HDFC00002400", "Anil", 50000.00),
                new Transaction("T006", "ACME000001", "SBI00009999", "Reema", 12000.00)
        );

        System.out.println("\nAll transactions:");
        txs.forEach(System.out::println);

        double totalEither = totalAmountForHdfc(txs);
        double totalSrc = totalAmountWhereSrcIsHdfc(txs);
        List<String> employees = employeesPaidAtHdfc(txs);

        System.out.println("\nTotal amount (either src or dest contains HDFC): ₹" + totalEither);
        System.out.println("Total amount (source contains HDFC): ₹" + totalSrc);
        System.out.println("Employees paid into HDFC accounts:");
        employees.forEach(e -> System.out.println("  " + e));
    }
}
