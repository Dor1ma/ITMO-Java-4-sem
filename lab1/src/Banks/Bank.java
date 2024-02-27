package Banks;

import Accounts.Account;
import Clients.Client;
import Clients.ClientBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private final HashMap<Client, ArrayList<Account>> clients = new HashMap<>();

    public void addNewClient() {
        ClientBuilder clientBuilder = new ClientBuilder();
        String temp = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("---New client registration---");
        while (temp.equals("")) {
            System.out.println("Enter your name:");
            temp = scanner.nextLine();
            clientBuilder.setName(temp);
        }

        temp = "";

        while (temp.equals("")) {
            System.out.println("Enter your surname:");
            temp = scanner.nextLine();
            clientBuilder.setSurname(temp);
        }

        temp = "";

        System.out.println("Enter your address (you can leave this field empty):");
        temp = scanner.nextLine();
        clientBuilder.setAddress(temp);

        System.out.println("Enter the series of your passport (you can leave this field empty):");
        temp = scanner.nextLine();
        if (temp.equals("")) {
            clientBuilder.setSeries(0);
        } else {
            int digit = Integer.parseInt(temp);
            clientBuilder.setSeries(digit);
        }

        System.out.println("Enter the number of your passport (you can leave this field empty):");
        temp = scanner.nextLine();
        if (temp.equals("")) {
            clientBuilder.setNumber(0);
        } else {
            int digit = Integer.parseInt(temp);
            clientBuilder.setNumber(digit);
        }

        clients.put(clientBuilder.createClient(), new ArrayList<Account>());

        System.out.println("A new client was successfully added to the system");
    }

    public void loginClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--LOGIN---");
        System.out.println("Enter your name: ");

        String name = scanner.nextLine();
        System.out.println("Enter your surname: ");
        String surname = scanner.nextLine();
        int series;
        int number = 0;


        for (Map.Entry<Client, ArrayList<Account>> entry : clients.entrySet()) {
            Client key = entry.getKey();
            if (key.getName().equals(name) && key.getSurname().equals(surname)) {
                if (key.getSeries() == 0 && key.getNumber() == 0) {
                    break;
                } else {
                    System.out.println("Enter the series of your passport: ");
                    series = scanner.nextInt();
                    System.out.println("Enter the number of your passport: ");
                    number = scanner.nextInt();
                }
            }
        }
    }
}
