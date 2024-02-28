package Banks;

import java.util.ArrayList;

public class CentralBank {
    private final ArrayList<Bank> banks = new ArrayList<>();

    public void chargeInterests() {
        for (Bank bank : banks) {
            bank.chargeInterest();
        }
    }

    public void registerNewBank(Bank bank) {
        banks.add(bank);
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }
}
