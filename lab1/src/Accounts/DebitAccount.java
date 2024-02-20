package Accounts;

public class DebitAccount extends Account {
    public DebitAccount(double percent) {
        super(0, percent, 0, 0);
    }

    @Override
    public void setAmount(double sum) {
        if (super.amount + sum < 0) {
            System.out.println("Transaction diverted. Insufficient funds on account");
            return;
        }

        super.setAmount(sum);
    }
}
