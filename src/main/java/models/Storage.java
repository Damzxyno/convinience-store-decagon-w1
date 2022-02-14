package models;

import enums.StorageType;
import exceptions.OutOfStockException;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Storage {
    private final StorageType storageType;
    private final Map<Product, Integer> productsList;
    private double totalPriceOfGoods;

    public Storage (StorageType storageType){
        this.storageType = storageType;
        productsList = new HashMap<>();
    }

    public StorageType getStorageType() {return storageType;}


    public void addToProductsList(Product product, int units) {
        totalPriceOfGoods += product.getProductPrice() * units;
        productsList.computeIfPresent(product, (currentProduct, currentUnits) -> currentUnits + units);
        productsList.putIfAbsent(product, units);
    }

    public double getTotalPriceOfGoods() {return totalPriceOfGoods;}

    public boolean containsProduct(Product product) {return productsList.containsKey(product);}

    //create Test
    public boolean containsProductInAParticularQuantity(Product product, int quantity) {return productsList.get(product) >= quantity;}

    public String generateRegisterAndRemoveProductsFromAnotherStorage(Storage storage) throws OutOfStockException {
       return generateRegister(null, storage);
    }

    public String  generateCompleteProductsRegister() throws OutOfStockException{
        return generateRegister(null, null);
    }

    public String  generateProductRegisterByCategory(Category category) throws OutOfStockException{
        return generateRegister(category, null);
    }

    private String generateRegister(Category category, Storage storage) throws OutOfStockException {
        if (productsList.isEmpty()) throw new OutOfStockException (storageType + " is currently empty!!");
        else {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
            StringBuilder productRegister = new StringBuilder();
            int totalProducts = 0;
            int serialNo = 1;

            for (var productSet : productsList.entrySet()){
                Product product = productSet.getKey();
                if (category == null || category == product.getProductCategory()) {
                    String categoryLine = "";

                    if (category == null) categoryLine =  " | Category: " + product.getProductCategory().getCategoryName();
                    if (storage != null){storage.reduceProductQuantity(product, productSet.getValue());}
                    int quantityOfProducts = productSet.getValue();
                    totalProducts += quantityOfProducts;

                    productRegister.append("\n ").append(serialNo).append(" | Product: ")
                            .append(product.getProductName())
                            .append(categoryLine)
                            .append(" | Quantity : ").append(quantityOfProducts)
                            .append(" | Price per Unit: ").append(formatter.format(product.getProductPrice()))
                            .append(" | Total Price of Product: ")
                            .append(formatter.format(product.getProductPrice() * quantityOfProducts));
                    serialNo++;
                }
            }

            if (productRegister.length() < 1) throw new OutOfStockException (storageType + " doesn't have any goods in the " + Objects.requireNonNull(category).getCategoryName() + " category.");

            productRegister.append("\n Total Products Units: ").append(totalProducts)
                    .append(" || GrandPrice: ").append(formatter.format(totalPriceOfGoods));


            return productRegister.toString();
        }
    }

    public void reduceProductQuantity(Product product, int units) throws OutOfStockException {
        if (!productsList.containsKey(product)) throw new OutOfStockException ("The current Product: " + product.getProductName() + " does not exist in " + storageType + ".");
        else if (productsList.get(product) > units) {
            productsList.computeIfPresent(product, (currentProduct, currentUnits) -> currentUnits - units);
            if (storageType == StorageType.CUSTOMER_CART && productsList.get(product).equals(0)){removeProduct(product);
            }
        }
    }

    public int getProductQuantity(Product product) throws OutOfStockException {
        if (!productsList.containsKey(product)) throw new OutOfStockException("The current Product: " + product.getProductName() + " does not exist in " + storageType + ".");
        else{return productsList.get(product);}
    }

    public void removeProduct(Product product){productsList.remove(product);}

    public void clearProductList(){productsList.clear();}

    public boolean isEmpty(){return productsList.isEmpty();}

    @Override
    public String toString(){return "Storage{StorageType: " + storageType + ", productList: " + productsList + "}";}
}
