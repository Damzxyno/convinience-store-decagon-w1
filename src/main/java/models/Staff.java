package models;

import enums.Designation;

public class Staff extends Person{
    private final Designation designation;

    public Staff(String lastName, String firstName, Designation designation) {
        super(lastName, firstName);
        this.designation = designation;
    }

    public Designation getDesignation() {return designation;}
}
