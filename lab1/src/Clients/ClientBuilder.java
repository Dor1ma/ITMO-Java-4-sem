package Clients;

public class ClientBuilder implements Builder {
    private String name = null;
    private String surname = null;
    private String address = null;
    private int series = 0;
    private int number = 0;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    public Client createClient() {
        return new Client(name, surname, address, series, number);
    }
}
