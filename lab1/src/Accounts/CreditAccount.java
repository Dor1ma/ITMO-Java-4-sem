package Accounts;

public class CreditAccount extends Account {
    public CreditAccount(double amount, int timeLimit, double commission) {
        super(amount, 0, timeLimit, commission);
    }
}
