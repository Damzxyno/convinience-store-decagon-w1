package models;

import enums.StorageType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StorageTest {
    Storage cart;
    Storage goods_display_shelves;
    Product jewelry1;
    Product jewelry2;


    @Before
    public void setUp() throws Exception {
        Category jewelries = new Category("JEWELRIES", "Gold, Silver, Diamond Jewelries from all over the world" );
        cart = new Storage(StorageType.CUSTOMER_CART);
        goods_display_shelves = new Storage(StorageType.GOODS_DISPLAY_SHELVES);
        jewelry1 = new Product("Chinese gold necklace", 250_000, jewelries);
        jewelry2 = new Product("Arabian steel ankle chain", 50_000, jewelries);

    }

    @Test
    public void setProductsListAndGetProductsList(){
        assertTrue("Check for empty storage!", cart.getProductsList().isEmpty());

        cart.setProductsList(jewelry1);
        assertTrue("Storage (cart) should now contain jewelry1", cart.getProductsList().containsKey(jewelry1));
        assertTrue("Storage (cart) should contain only 1 jewelry1", cart.getProductsList().get(jewelry1) == 1);

        cart.setProductsList(jewelry1);
        assertTrue("Storage (cart) should now contain  2 jewelry1", cart.getProductsList().get(jewelry1) == 2);
        assertFalse("Storage (cart) should not contain jewelry2 since its not added yet", cart.getProductsList().containsKey(jewelry2));

        cart.setProductsList(jewelry2);
        assertTrue("Storage (cart) should now contain jewelry2", cart.getProductsList().containsKey(jewelry2));


        goods_display_shelves.setProductsList(jewelry2, 30);
        assertTrue("Testing the overloading method: setProductList I", goods_display_shelves.getProductsList().get(jewelry2) == 30);
        assertFalse("Testing the overloading method: setProductList II", goods_display_shelves.getProductsList().get(jewelry2) == 20);
    }


    @Test
    public void removeProduct() {
        cart.setProductsList(jewelry2);
        cart.setProductsList(jewelry1);
        cart.removeProduct(jewelry2);
        assertFalse("Test for removeProduct() method, storage should not include jewelry2", cart.getProductsList().containsKey(jewelry2));
        assertTrue("Test for removeProduct() method, storage should still include jewelry1", cart.getProductsList().containsKey(jewelry1));

    }

    @Test
    public void reduceProductQuantity() {
    }

    @Test
    public void clearProductList() {
    }

    @Test
    public void testToString() {
    }
}