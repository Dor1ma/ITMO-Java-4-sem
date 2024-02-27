package Accounts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {
    protected double amount;
    protected double percent;
    protected int timeLimit;
    protected double commission;
    protected boolean isDubious;
    protected double dailyPercents = 0;

    public Account(double amount, double percent, int timeLimit, double commission) {
        this.amount = amount;
        this.percent = percent;
        this.timeLimit = timeLimit;
        this.commission = commission;
    }
}
