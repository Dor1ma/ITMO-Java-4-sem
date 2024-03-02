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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create an account in the bank");
            System.out.println("2. Login for account in the bank");
            System.out.println("3. Exit");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> createAnAccount();
                case 2 -> loginToBankSystem();
                case 3 -> {
                    System.out.println("Shut down");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Wrong option");
                    break;
                }
            }
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
        actionsLayer(centralBank.getBanks().get(index - 1), client);
    }

    private void actionsLayer(Bank bank, Client client) {
        System.out.println("---Actions page---");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the option");
            System.out.println("1. Open new account");
            System.out.println("2. Refill an account");
            System.out.println("3. Withdraw an account");
            System.out.println("4. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1 -> bank.createNewAccount(client);
                case 2 -> bank.refillAnAccount(client);
                case 3 -> bank.withdrawAnAccount(client);
                case 4 -> {
                    System.out.println("Exiting from bank system");
                    return;
                }
                default -> {
                    System.out.println("Wrong option");
                    break;
                }
            }
        }
    }
}
