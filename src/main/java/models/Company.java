package models;

import enums.StorageType;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final String companyName;
    private final Storage companyGoods;
    private double companyAccount;
    private final List <Applicant> applicantList;
    private final List <Staff> staffList;

    public Company (String companyName) {
        this.companyName = companyName;
        companyGoods = new Storage(StorageType.GOODS_DISPLAY_SHELVES);
        staffList = new ArrayList<>();
        applicantList = new ArrayList<>();
    }

    public String getCompanyName() {return companyName;}

    public Storage getCompanyGoods() {return companyGoods;}

    public void fundCompanyAccount(double amount) {this.companyAccount += amount;}

    public double getCompanyAccount() {return companyAccount;}

    public List<Staff> getStaffList() {return staffList;}

    public List<Applicant> getApplicantList() {return applicantList;}
}
