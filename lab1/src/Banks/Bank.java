package Banks;

import Accounts.Account;
import Clients.Client;
import Clients.ClientBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bank {
    private HashMap<Client, ArrayList<Account>> clients;

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
}
