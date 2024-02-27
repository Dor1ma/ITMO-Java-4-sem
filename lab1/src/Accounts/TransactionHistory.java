package Accounts;

import java.util.ArrayList;

public class TransactionHistory {
    private final ArrayList<Transaction> history = new ArrayList<>();
    private final ArrayList<Transaction> cancellationHistory = new ArrayList<>();

    public void Update(Transaction transaction) {
        history.add(transaction);
    }

    public boolean Exists(long transactionID) {
        for (Transaction transaction : history) {
            if (transaction.getCurrentID() == transactionID) {
                return true;
            }
        }

        return false;
    }

    public void updateCancellationHistory(Transaction transaction) {
        cancellationHistory.add(transaction);
    }

    public boolean IsCanceled (long transactionID) {
        for (Transaction transaction : cancellationHistory) {
            if (transaction.getCurrentID() == transactionID) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Transaction> getHistory() {
        return history;
    }
}
