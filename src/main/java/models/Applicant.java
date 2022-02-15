package models;

import enums.Qualification;

public class Applicant extends Person{
    private final Qualification qualification;

    public Applicant(String lastName, String firstName, Qualification qualification) {

        super(lastName, firstName);
        this.qualification = qualification;
    }

    public Qualification getQualification(){return qualification;}
}
