package Banks;

import Accounts.*;
import Clients.Client;
import Clients.ClientBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private final HashMap<Client, ArrayList<Account>> clients = new HashMap<>();
    private double transactionLimit;
    private double debitPercent;
    private double depositPercent;
    private double creditAmount;
    private double creditTimeLimit;
    private int creditCommission;

    public Bank(double transactionLimit, double debitPercent, double depositPercent, double creditAmount, double creditTimeLimit, int creditCommission) {
        this.transactionLimit = transactionLimit;
        this.debitPercent = debitPercent;
        this.depositPercent = depositPercent;
        this.creditAmount = creditAmount;
        this.creditTimeLimit = creditTimeLimit;
        this.creditCommission = creditCommission;
    }

    public void addNewClient() {
        ClientBuilder clientBuilder = new ClientBuilder();
        String temp = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("---New client registration---");
        while (temp.equals("")) {
            System.out.println("Enter your name:");
            temp = scanner.nextLine();
            clientBuilder.setName(temp);
        }

        temp = "";

        while (temp.equals("")) {
            System.out.println("Enter your surname:");
            temp = scanner.nextLine();
            clientBuilder.setSurname(temp);
        }

        temp = "";

        System.out.println("Enter your address (you can leave this field empty):");
        temp = scanner.nextLine();
        clientBuilder.setAddress(temp);

        System.out.println("Enter the series of your passport (you can leave this field empty):");
        temp = scanner.nextLine();
        if (temp.equals("")) {
            clientBuilder.setSeries(0);
        } else {
            int digit = Integer.parseInt(temp);
            clientBuilder.setSeries(digit);
        }

        System.out.println("Enter the number of your passport (you can leave this field empty):");
        temp = scanner.nextLine();
        if (temp.equals("")) {
            clientBuilder.setNumber(0);
        } else {
            int digit = Integer.parseInt(temp);
            clientBuilder.setNumber(digit);
        }

        clients.put(clientBuilder.createClient(), new ArrayList<Account>());

        System.out.println("A new client was successfully added to the system");
    }

    public Client loginClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--LOGIN---");
        System.out.println("Enter your name: ");

        String name = scanner.nextLine();
        System.out.println("Enter your surname: ");
        String surname = scanner.nextLine();
        int series;
        int number = 0;

        for (Map.Entry<Client, ArrayList<Account>> entry : clients.entrySet()) {
            Client key = entry.getKey();
            if (key.getName().equals(name) && key.getSurname().equals(surname)) {
                if (key.getSeries() == 0 && key.getNumber() == 0) {
                    return key;
                } else {
                    System.out.println("Enter the series of your passport: ");
                    series = scanner.nextInt();
                    System.out.println("Enter the number of your passport: ");
                    number = scanner.nextInt();
                    return key;
                }
            }
        }

        return null;
    }

    public void chargeInterest() {
        for (Map.Entry<Client, ArrayList<Account>> entry : clients.entrySet()) {
            for (Account account : entry.getValue()) {
                if (account instanceof DebitAccount || account instanceof DepositAccount) {
                    if (account.getTimeLimit() <= 0 && account instanceof DepositAccount) {
                        continue;
                    }

                    double percent = account.getPercent();
                    double amount = account.getAmount();
                    double daily = percent / 365;
                    double result = daily * amount / 100;
                    account.setDailyPercents(account.getDailyPercents() + result);
                    account.setTimeLimit(account.getTimeLimit() - 1);

                    if (account.getTimeLimit() % 30 == 0) {
                        account.setAmount(account.getAmount() + account.getDailyPercents());
                        account.setDailyPercents(0);
                    }
                }
            }
        }
    }

    public void createNewAccount(Client client) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select the option");
            System.out.println("1. Create debit account");
            System.out.println("2. Create deposit account");
            System.out.println("3. Create credit account");
            System.out.println("4. Cancel");
            
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    clients.get(client).add(new DebitAccount(debitPercent));
                    return;
                }
                case 2 -> {
                    clients.get(client).add(new DepositAccount(0, depositPercent, 0));
                    return;
                }
                case 3 -> {
                    clients.get(client).add(new CreditAccount(creditAmount, creditCommission, creditTimeLimit));
                    return;
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Wrong option");
            }
        }
    }

    public void refillAnAccount(Client client) {
        System.out.println("Select the desired account");
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < clients.get(client).size(); i++) {
            System.out.println(i + 1 + clients.get(client).get(i).getCurrentID());
        }

        int accountIndex = scanner.nextInt();

        System.out.println("Enter the amount of replenishment:");
        double sum = scanner.nextDouble();
        clients.get(client).get(accountIndex).refill(sum);
    }

    public void withdrawAnAccount(Client client) {
        System.out.println("Select the desired account");
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < clients.get(client).size(); i++) {
            System.out.println(i + 1 + clients.get(client).get(i).getCurrentID());
        }

        int accountIndex = scanner.nextInt();

        System.out.println("Enter the amount to be withdrawn");
        double sum = scanner.nextDouble();
        clients.get(client).get(accountIndex).withdrawal(sum);
    }

    public void transactionCancellation(Client client, long accountID, long transactionID) {
        for (Account account : clients.get(client)) {
            if (account.getCurrentID() == accountID) {
                if (client.getTransactionHistory().Exists(transactionID)
                    && !client.getTransactionHistory().IsCanceled(transactionID)) {
                    Transaction transaction = client.getTransactionByID(transactionID);
                    double sum = transaction.getSum();
                    account.setAmount(account.getAmount() + sum);
                    client.getTransactionHistory().updateCancellationHistory(transaction);
                }
            }
        }
    }

    public void changeAccountPercents(long accountID, double newPercents) {
        for (Map.Entry<Client, ArrayList<Account>> entry : clients.entrySet()) {
            for (Account account : entry.getValue()) {
                if (account.getCurrentID() == accountID) {
                    account.setPercent(newPercents);
                    entry.getKey().showNotification("The bank has changed your account percents\n" +
                            "New percent is: " + newPercents);
                }
            }
        }
    }

    public void changeTransactionsLimit(double newTransactionLimit) {
        this.transactionLimit = newTransactionLimit;
        for (Map.Entry<Client, ArrayList<Account>> entry : clients.entrySet()) {
            entry.getKey().showNotification("The bank has changed its transaction limit\n" +
                    "New transaction limit is: " + newTransactionLimit);
        }
    }

    public double getTransactionLimit() {
        return transactionLimit;
    }

    public double getDebitPercent() {
        return debitPercent;
    }

    public void setDebitPercent(double debitPercent) {
        this.debitPercent = debitPercent;
    }

    public double getDepositPercent() {
        return depositPercent;
    }

    public void setDepositPercent(double depositPercent) {
        this.depositPercent = depositPercent;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double getCreditTimeLimit() {
        return creditTimeLimit;
    }

    public void setCreditTimeLimit(double creditTimeLimit) {
        this.creditTimeLimit = creditTimeLimit;
    }

    public int getCreditCommission() {
        return creditCommission;
    }

    public void setCreditCommission(int creditCommission) {
        this.creditCommission = creditCommission;
    }
}