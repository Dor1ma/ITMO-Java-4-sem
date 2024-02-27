package Accounts;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
public abstract class Account {
    protected static long globalID;
    protected long currentID;
    protected double amount;
    protected double percent;
    protected int timeLimit;
    protected double commission;
    protected boolean isDubious;
    protected double dailyPercents = 0;

    public Account(double amount, double percent, int timeLimit, double commission) {
        this.currentID = Account.globalID++;
        this.amount = amount;
        this.percent = percent;
        this.timeLimit = timeLimit;
        this.commission = commission;
    }

    public void withdrawal(double sum) {
        if (sum > this.amount) {
            System.out.println("Transaction denied. Insufficient funds");
            return;
        }

        this.amount -= sum;
    }
}
