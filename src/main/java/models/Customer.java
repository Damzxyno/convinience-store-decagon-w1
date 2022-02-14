package models;

import enums.StorageType;

public class Customer extends Person{
    private double wallet;
    private Storage cart;
    private Storage purchasedItems;
    private boolean checkOut;

    public Customer(String lastName, String firstName) {
        super(lastName, firstName);
        cart = new Storage(StorageType.CUSTOMER_CART);
        purchasedItems = new Storage(StorageType.PURCHASED_ITEMS);
    }

    public double getWalletValue() {return wallet;}

    public void fundWalletValue(double amount) {wallet += amount;}

    public void deductFromWalletValue(double amount) {wallet -= amount;}

    public Storage getCart() {return cart;}

    public Storage getPurchasedItems() {return purchasedItems;}

    public boolean hasCheckedOut(){return checkOut;}

    public void checkOut () {this.checkOut = true;}

    public void unCheckOut() {this.checkOut = false;}




}
