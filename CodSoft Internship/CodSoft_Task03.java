import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("***Welcome to ATM***");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public int getUserChoice() {
        int choice;
        do {
            System.out.print("Enter your choice between 1 to 4: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 4);

        return choice;
    }

    public void run() {
        int choice;

        do {
            displayOptions();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    break;
            }
        } while (choice != 4);
    }

    private void withdraw() {
        double amount;
        do {
            System.out.print("Enter the amount to withdraw: $");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); // consume the invalid input
            }
            amount = scanner.nextDouble();
        } while (amount <= 0);

        if (userAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful. Remaining balance: $" + userAccount.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }

    private void deposit() {
        double amount;
        do {
            System.out.print("Enter the amount to deposit: $");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next();
            }
            amount = scanner.nextDouble();
        } while (amount <= 0);

        userAccount.deposit(amount);
        System.out.println("Deposit successful. New balance: $" + userAccount.getBalance());
    }

    private void checkBalance() {
        System.out.println("Current balance: $" + userAccount.getBalance());
    }
}

class Main {
    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(1000);

        ATM atm = new ATM(userAccount);

        atm.run();
    }
}
