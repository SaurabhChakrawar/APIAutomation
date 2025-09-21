package models;

public class Phone {
    private String type;   // e.g., mobile, home, work
    private String number;

    // Constructors
    public Phone() {}

    public Phone(String type, String number) {
        this.type = type;
        this.number = number;
    }

    // Getters & Setters
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
