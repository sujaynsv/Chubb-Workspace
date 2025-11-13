import java.io.*;
import java.util.*;

public class FileTransferProcessor {


    static class Account {
        String name;
        String country;
        String phone;
        String ifsc;
        double balance;

        Account(String name, String country, String phone, String ifsc, double balance) {
            this.name = name;
            this.country = country;
            this.phone = phone;
            this.ifsc = ifsc;
            this.balance = balance;
        }

        void debit(double amt) { this.balance -= amt; }
        void credit(double amt) { this.balance += amt; }

        @Override
        public String toString() {
            return String.format("%s (%s) IFSC:%s Balance:%.2f", name, phone, ifsc, balance);
        }
    }


    private static String keyFor(String name, String ifsc) {
        return name + "|" + ifsc;
    }

    public static void main(String[] args) {
        String filePath = "transfers.txt";
        if (args.length > 0) filePath = args[0];

        Map<String, Account> accounts = new HashMap<>();
        double totalPaidByHdfc = 0.0;
        int lineNo = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNo++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] tokens = line.split("\\s*,\\s*");
                if (tokens.length < 11) {
                    System.out.println("Line " + lineNo + ": invalid format (expected 11 fields). Skipping.");
                    continue;
                }


                String sName = tokens[0];
                String sCountry = tokens[1];
                String sPhone = tokens[2];
                String sIfsc = tokens[3];
                double sBalance;
                double transferAmount;
                try {
                    sBalance = Double.parseDouble(tokens[4]);
                    transferAmount = Double.parseDouble(tokens[5]);
                } catch (NumberFormatException nfe) {
                    System.out.println("Line " + lineNo + ": number parse error. Skipping.");
                    continue;
                }
                String transCode = tokens[6];


                String dName = tokens[7];
                String dCountry = tokens[8];
                String dPhone = tokens[9];
                String dIfsc = tokens[10];

                String sKey = keyFor(sName, sIfsc);
                Account src = accounts.get(sKey);
                if (src == null) {
                    src = new Account(sName, sCountry, sPhone, sIfsc, sBalance);
                    accounts.put(sKey, src);
                } else {
                }
                

                String dKey = keyFor(dName, dIfsc);
                Account dest = accounts.get(dKey);
                if (dest == null) {
                    dest = new Account(dName, dCountry, dPhone, dIfsc, 0.0);
                    accounts.put(dKey, dest);
                }

                System.out.printf("Line %d: %s -> %s | Amount: %.2f | TxnCode: %s\n",
                        lineNo, src.name, dest.name, transferAmount, transCode);

                if (transferAmount <= 0) {
                    System.out.println("  -> Skipped: transfer amount must be > 0.");
                    continue;
                }

                if (src.balance < transferAmount) {
                    System.out.println("  -> Skipped: insufficient balance. Source balance = " + src.balance);
                    continue;
                }

                src.debit(transferAmount);
                dest.credit(transferAmount);
                System.out.println("  -> Success: transfer completed.");

                if (src.ifsc != null && src.ifsc.toUpperCase().contains("HDFC")) {
                    totalPaidByHdfc += transferAmount;
                }
            }

            System.out.println("\n=== Summary ===");
            System.out.printf("Total amount paid by HDFC (sources with IFSC containing 'HDFC'): %.2f\n", totalPaidByHdfc);
            System.out.println("\nFinal account states:");
            for (Account a : accounts.values()) {
                System.out.println("  - " + a);
            }

        } catch (FileNotFoundException fnf) {
            System.err.println("File not found: " + filePath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
