package Accounts;

public class CreditAccount extends Account {
    public CreditAccount(double amount, int timeLimit, double commission) {
        super(amount, 0, timeLimit, commission);
    }

    @Override
    public void setAmount(double amount) {
        if (super.amount - amount < 0) {
            super.amount -= amount + commission * amount;
            System.out.println("Your balance is below 0. Operation will be held with commission");
        } else {
            super.amount -= amount;
        }
    }
}
