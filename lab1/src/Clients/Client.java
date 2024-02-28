package Clients;

import Accounts.Transaction;
import Accounts.TransactionHistory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private String name;
    private String surname;
    private String address;
    private int series;
    private int number;
    protected final TransactionHistory transactionHistory = new TransactionHistory();

    public Client(String name,
                  String surname,
                  String address,
                  int series,
                  int number) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.series = series;
        this.number = number;
    }

    public Transaction getTransactionByID(long transactionID) {
        for (Transaction transaction : transactionHistory.getHistory()) {
            if (transaction.getCurrentID() == transactionID) {
                return transaction;
            }
        }

        return null;
    }

    public void showNotification(String message) {
        System.out.println("---NOTIFICATION---");
        System.out.println(message);
    }
}
