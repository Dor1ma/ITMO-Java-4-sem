import Banks.Bank;
import Banks.CentralBank;
import Clients.Client;

import java.util.Scanner;

public class Menu {
    private final CentralBank centralBank;

    public Menu (CentralBank centralBank) {
        this.centralBank = centralBank;
    }

    public void run() {
        greetingsLayer();
    }

    private void greetingsLayer() {
        System.out.println("---Welcome page---");
        System.out.println("Choose an option:");
        System.out.println("1. Create an account in the bank");
        System.out.println("2. Login for account in the bank");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if (option == 1) {
            createAnAccount();
        } else {
            loginToBankSystem();
        }
    }

    private void showBanks() {
        for (int i = 0; i < centralBank.getBanks().size(); i++) {
            System.out.println("Bank number: " + i + 1);
        }
    }

    private void createAnAccount() {
        System.out.println("Choose the optional bank");
        showBanks();
        int index;
        Scanner scanner = new Scanner(System.in);
        index = scanner.nextInt();

        centralBank.getBanks().get(index - 1).addNewClient();
    }

    private void loginToBankSystem() {
        System.out.println("Choose the optional bank");
        showBanks();
        int index;
        Scanner scanner = new Scanner(System.in);
        index = scanner.nextInt();

        Client client = centralBank.getBanks().get(index - 1).loginClient();
    }

    private void actionsLayer(Bank bank, Client client) {
        System.out.println("---Actions page---");
        System.out.println("1. Open new account");
        System.out.println("1. Refill an account");
        System.out.println("2. Withdraw an account");
    }
}
