
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class BankOfFlynn {
    private double balance = 0.0;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    class Transaction {
        String date;
        String type;
        double amount;

        public Transaction(String date, String type, double amount) {
            this.date = date;
            this.type = type;
            this.amount = amount;
        }
    }

    public static void main(String[] args) {
        BankOfFlynn bank = new BankOfFlynn();
        bank.run();
    }

    public void run() {
        transactions = readData("src/MicroBank/BoFinput.data");
        this.transactions = transactions;
        for (Transaction t : transactions) {
            if (t.type.equals("withdrawal")) {
                balance -= t.amount;
            } else if (t.type.equals("deposit")) {
                balance += t.amount;
            }
        }
        System.out.println("Welcome to Bank of Flynn! Your current balance is: " + String.format("$%.2f", balance));
        
        Scanner scanner = new Scanner(System.in);
        while (true) {    
            System.out.println("What would you like to do? Type 'help' for options. Enter 'exit' to quit");
            switch (scanner.nextLine().toLowerCase()) {
                case "help" -> {
                    System.out.println("Available commands:");
                    System.out.println("  balance - Check your current balance");
                    System.out.println("  withdrawal history - View your withdrawal history");
                    System.out.println("  deposit history - View your deposit history");
                    System.out.println("  give me money - Request money from the bank");
                    System.out.println("  exit - Quit the application");
                }
                case "exit" -> {
                    System.out.println("Thank you for banking with Bank of Flynn. Happy consuming!");
                    return;
                }
                case "balance" -> System.out.println(String.format("Current Balance: $%.2f", balance));
                case "withdrawal history" -> {
                    for (Transaction t : transactions) {
                    if (t.type.equals("withdrawal")) {
                    System.out.println(t.date + ", " + t.type + ", " + String.format("$%.2f", t.amount));
                      }
                    }
                }
                case "deposit history" -> {
                    for (Transaction t : transactions) {
                    if (t.type.equals("deposit")) {
                    System.out.println(t.date + ", " + t.type + ", " + String.format("$%.2f", t.amount));
                      }
                    }
                }
                case "give me money" -> System.out.println("No!");
                  }
                 }
                }
    
    ArrayList<Transaction> readData(String filename) {
        ArrayList<Transaction> list = new ArrayList<>();
        // open a text file and read each line, putting the fields into a Transaction
        // object.
        try {
            Files.lines(Paths.get(filename))
                    .forEach(line -> {
                        String[] parts = line.split(", ");
                        if (parts.length == 3) {
                            list.add(new Transaction(parts[0], parts[1], Double.parseDouble(parts[2])));
                        }
                    });
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
        return list;
    }
}