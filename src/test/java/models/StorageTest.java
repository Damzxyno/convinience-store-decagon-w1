package models;

import enums.StorageType;
import exceptions.OutOfStockException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StorageTest {
    Storage cart;
    Storage goods_display_shelves;
    Product jewelry1;
    Product jewelry2;


    @Before
    public void setUp() {
        Category jewelries = new Category("JEWELRIES", "Gold, Silver, Diamond Jewelries from all over the world" );
        cart = new Storage(StorageType.CUSTOMER_CART);
        goods_display_shelves = new Storage(StorageType.GOODS_DISPLAY_SHELVES);
        jewelry1 = new Product("Chinese gold necklace", 250_000, jewelries);
        jewelry2 = new Product("Arabian steel ankle chain", 50_000, jewelries);

    }

    @Test
    public void setProductsListAndGetProductsList() throws OutOfStockException {
        assertTrue("Check for empty storage!", cart.isEmpty());

        cart.addToProductsList(jewelry1, 1);
        assertTrue("Storage (cart) should now contain jewelry1", cart.containsProduct(jewelry1));
        assertEquals("Storage (cart) should contain only 1 jewelry1", 1, cart.getProductQuantity(jewelry1));

        cart.addToProductsList(jewelry1, 1);
        assertEquals("Storage (cart) should now contain  2 jewelry1", 2, cart.getProductQuantity(jewelry1));
        assertFalse("Storage (cart) should not contain jewelry2 since its not added yet", cart.containsProduct(jewelry2));

        cart.addToProductsList(jewelry2, 1);
        assertTrue("Storage (cart) should now contain jewelry2", cart.containsProduct(jewelry2));


        goods_display_shelves.addToProductsList(jewelry2, 30);
        assertEquals("Testing the setProductList I", 30, goods_display_shelves.getProductQuantity(jewelry2));
        assertNotEquals("Testing the setProductList II", 20, goods_display_shelves.getProductQuantity(jewelry2));
    }


    @Test
    public void removeProduct() {
        cart.addToProductsList(jewelry2, 1);
        cart.addToProductsList(jewelry1, 1);
        cart.removeProduct(jewelry2);
        assertFalse("Test for removeProduct() method, storage should not include jewelry2", cart.containsProduct(jewelry2));
        assertTrue("Test for removeProduct() method, storage should still include jewelry1", cart.containsProduct(jewelry1));
    }

    @Test
    public void reduceProductQuantity() throws OutOfStockException {
        goods_display_shelves.addToProductsList(jewelry1, 10);
        goods_display_shelves.reduceProductQuantity(jewelry1, 8);
        assertEquals("Test for reducing product by quantity", 2, goods_display_shelves.getProductQuantity(jewelry1));
        goods_display_shelves.addToProductsList(jewelry1, 18);
        assertNotEquals("Jewelry should not have old quantity after increment", 2, goods_display_shelves.getProductQuantity(jewelry1));
        assertEquals("Jewelry quantity should be current quantity", 20, goods_display_shelves.getProductQuantity(jewelry1));
    }

    @Test
    public void clearProductList() {
        goods_display_shelves.addToProductsList(jewelry2, 500);
        assertFalse("To show cart wasn't initially empty", goods_display_shelves.isEmpty());
        goods_display_shelves.clearProductList();
        assertTrue("Storage should be empty now that it has been cleared", goods_display_shelves.isEmpty());
    }

    @Test
    public void generateProductRegisterTest() throws OutOfStockException {
        goods_display_shelves.addToProductsList(jewelry1, 10);
        goods_display_shelves.addToProductsList(jewelry2, 1);

        Category fans = new Category("FANS", "Best made quality ceiling and standing fans" );
        Product fan1 = new Product("OX Electric Fan", 18_000.00, fans);

        goods_display_shelves.addToProductsList(fan1, 1);
        System.out.println(goods_display_shelves.generateCompleteProductsRegister());
        System.out.println("----------------------");
        System.out.println(goods_display_shelves.generateProductRegisterByCategory(fans));
    }

    @Test
    public void testToString() {
        goods_display_shelves.addToProductsList(jewelry2, 1);
        String expectedOutput = "Storage{StorageType: GOODS_DISPLAY_SHELVES, productList: {Product{ProductName: 'Arabian steel ankle chain', productPrice: 50000.0, productCategory: Category{ categoryName: 'JEWELRIES', description: 'Gold, Silver, Diamond Jewelries from all over the world'}}=1}}";
        assertEquals("Test toString() override", expectedOutput, goods_display_shelves.toString());
    }
}