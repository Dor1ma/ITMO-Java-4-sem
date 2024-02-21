package Clients;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private String name;
    private String surname;
    private String address;
    private int series;
    private int number;

    public Client(String name,
                  String surname,
                  String address,
                  int series,
                  int number) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.series = series;
        this.number = number;
    }
}
