package models;

import enums.StorageType;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private final StorageType storageType;
    private Map<Product, Integer> productsList;

    public Storage (StorageType storageType){
        this.storageType = storageType;
        productsList = new HashMap<>();
    }

    public Map<Product, Integer> getProductsList() {return  productsList;}

    public void reduceProductQuantity(Product product, int units) {
//        if (!productsList.containsKey(product)) throw new OutOfStockException ("The current Product: " + product.getProductName() + " does not exist in " + storageType + ".");
        if (productsList.get(product) > units) {productsList.computeIfPresent(product, (currentProduct, currentUnits) -> currentUnits - units);}
        if (storageType == StorageType.CUSTOMER_CART && productsList.get(product) == 0 ){removeProduct(product);}
    }

    public void setProductsList(Product product){addToProductsList(product, 1);}

    public void setProductsList(Product product, int units){addToProductsList(product, units);}

    private void addToProductsList(Product product, int units) {
        productsList.computeIfPresent(product, (currentProduct, currentUnits) -> currentUnits + units);
        productsList.putIfAbsent(product, units);
    }

    public void removeProduct(Product product){productsList.remove(product);}

    public void clearProductList(){productsList.clear();}

    @Override
    public String toString(){return "Storage{StorageType: " + storageType + "productList: " + productsList + "}";}
}
