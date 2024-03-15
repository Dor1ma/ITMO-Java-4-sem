import Banks.Bank;
import Banks.CentralBank;

public class Main {
    public static void main(String[] args) {
        CentralBank centralBank = new CentralBank();
        Bank mainBank = new Bank(
                1000000,
                3,
                14,
                200000,
                365,
                1
        );
        centralBank.registerNewBank(mainBank);

        Menu menu = new Menu(centralBank);
        menu.run();
    }
}
