package models;

import enums.StorageType;

public class Customer extends Person{
    private double wallet;
    private final Storage cart;
    private boolean checkOut;

    public Customer(String lastName, String firstName) {
        super(lastName, firstName);
        cart = new Storage(StorageType.CUSTOMER_CART);
    }

    public double getWalletValue() {return wallet;}

    public void fundWalletValue(double amount) {wallet += amount;}

    public void deductFromWalletValue(double amount) {wallet -= amount;}

    public Storage getCart() {return cart;}

    public boolean hasCheckedOut(){return checkOut;}

    public void checkOut () {this.checkOut = true;}

    public void unCheckOut() {this.checkOut = false;}




}
