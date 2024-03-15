package Accounts;

public class Transaction {
    private static long globalID;
    private final long currentID;
    private final double sum;

    public Transaction(double sum) {
        this.currentID = Transaction.globalID++;
        this.sum = sum;
    }

    public long getCurrentID() {
        return this.currentID;
    }

    public double getSum() {
        return this.sum;
    }
}
