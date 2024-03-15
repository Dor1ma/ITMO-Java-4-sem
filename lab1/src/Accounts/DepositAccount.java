package Accounts;

public class DepositAccount extends Account {
    public DepositAccount(double amount, double percent, int timeLimit) {
        super(amount, percent, timeLimit, 0);
    }

    @Override
    public double getAmount() {
        if (timeLimit > 0) {
            System.out.println("Transaction declined. Deposit validity period has not come to the end yet");
            return 0;
        }

        return super.getAmount();
    }
}
