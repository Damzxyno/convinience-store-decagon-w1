package models;

public abstract class Person {
    private final String lastName;
    private final String firstName;

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {return lastName;}

    public String getFirstName() {return firstName;}
}
