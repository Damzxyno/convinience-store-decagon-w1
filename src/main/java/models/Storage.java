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

    public Storage (StorageType storageType){
        this.storageType = storageType;
        productsList = new HashMap<>();
    }



    public void addToProductsList(Product product, int units) {
        productsList.computeIfPresent(product, (currentProduct, currentUnits) -> currentUnits + units);
        productsList.putIfAbsent(product, units);
    }

    public boolean containsProduct(Product product) {return productsList.containsKey(product);}

    //create Test
    public boolean containsProductInAParticularQuantity(Product product, int quantity) {return productsList.get(product) >= quantity;}

    public String [] generateCompleteProductsRegister() throws OutOfStockException{
        return generateRegister(null);
    }

    public String [] generateProductRegisterByCategory(Category category) throws OutOfStockException{
        return generateRegister(category);
    }

    private String [] generateRegister(Category category) throws OutOfStockException {
        if (productsList.isEmpty()) throw new OutOfStockException (storageType + " is currently empty!!");
        else {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "NG"));
            StringBuilder stringBuilder = new StringBuilder();
            String [] productRegister = new String[2];
            double totalPrice = 0.0;
            int totalProducts = 0;
            int serialNo = 1;

            for (var productSet : productsList.entrySet()){
                Product product = productSet.getKey();
                if (category == null || category == product.getProductCategory()) {
                    String categoryLine = "";

                    if (category == null) categoryLine =  " | Category: " + product.getProductCategory().getCategoryName();

                    double unitPriceOfProduct = product.getProductPrice();
                    int quantityOfProducts = productSet.getValue();
                    totalPrice += unitPriceOfProduct * quantityOfProducts;
                    totalProducts += quantityOfProducts;

                    stringBuilder.append("\n " + serialNo + " | Product: " +  product.getProductName() + categoryLine
                            +  " | Quantity : " + quantityOfProducts +
                            " | Price per Unit: " + formatter.format(product.getProductPrice()) +
                            " | Total Price of Product: " + formatter.format(product.getProductPrice() * quantityOfProducts));
                    serialNo++;
                }
            }

            if (stringBuilder.length() < 1) throw new OutOfStockException (storageType + " doesn't have any goods in the " + Objects.requireNonNull(category).getCategoryName() + " category.");

            stringBuilder.append("\n Total Products Units: " + totalProducts + " || GrandPrice: " + formatter.format(totalPrice));

            productRegister[0] = stringBuilder.toString();
            productRegister[1] = String.valueOf(totalPrice);

            return productRegister;
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
