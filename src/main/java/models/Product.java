package models;

public class Product {
    private String productName;
    private double productPrice;
    private Category productCategory;


    public Product(String productName, double productPrice, Category productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public String getProductName() {return productName;}

    public void setProductName(String productName) {this.productName = productName;}

    public double getProductPrice() {return productPrice;}

    public void setProductPrice(double productPrice) {this.productPrice = productPrice;}

    public Category getProductCategory() {return productCategory;}

    public void setProductCategory(Category productCategory) {this.productCategory = productCategory;}

    @Override
    public String toString(){
        return "Product{ productName: '" + productName + "', productPrice: " + productPrice + ", productCategory: " + productCategory ;
    }
}
