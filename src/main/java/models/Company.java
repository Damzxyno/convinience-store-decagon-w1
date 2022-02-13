package models;

import enums.StorageType;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyName;
    private Storage companyGoods;
    private String address;
    private double companyAccount;
//    private List <Applicant> applicantList;
    private List <Staff> staffList;

    public Company (String companyName, String address) {
        this.companyName = companyName;
        this.address = address;
        this.companyAccount = companyAccount;
        companyGoods = new Storage(StorageType.GOODS_DISPLAY_SHELVES);
        staffList = new ArrayList<>();
    }

    public String getCompanyName() {return companyName;}

    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public Storage getCompanyGoods() {return companyGoods;}

    public void setCompanyGoods(Storage companyGoods) {this.companyGoods = companyGoods;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public double getCompanyAccount() {return companyAccount;}

    public void fundCompanyAccount(double amount) {this.companyAccount += amount;}

    public List<Staff> getStaffList() {return staffList;}
}
