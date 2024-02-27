package Banks;

import java.util.ArrayList;

public class CentralBank {
    private final ArrayList<Bank> banks = new ArrayList<>();

    public void chargeInterests() {
        for (Bank bank : banks) {
            bank.chargeInterest();
        }
    }
}
