package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    private  Product fan1;
    private Product fan2;
    private Product chair1;
    private Product chair2;
    private  Category fans;
    private  Category chairs;


    @Before
    public void setUp(){
        //Instantiate two products for test
        fans = new Category("FANS", "Best made quality ceiling and standing fans" );
        chairs = new Category("CHAIRS", "Trousers, dresses, skirts and shorts of different materials" );
        fan1 = new Product("OX Electric Fan", 18_000.00, fans);
        fan2 = new Product("ORL Electric Fan", 20_000.00, fans);
        chair1 = new Product("Burgundy three seater Chair", 230_000.00, chairs);
        chair2 = new Product("Blue leather couch", 230_000, fans);

    }

    @Test
    public void getProductName() {
        assertEquals("Test for the productName getter.", "OX Electric Fan", fan1.getProductName());
        assertNotEquals("Test for wrong name but same category", "ORL Electric Fan", fan1.getProductName());
    }

    @Test
    public void setProductName() {
        fan1.setProductName("Binatone ceiling fan");
        assertEquals("Product name should be modifiable with the setProductName() method", "Binatone ceiling fan", fan1.getProductName());
        assertNotEquals("Product name should not be its former name: OX Electric Fan", "OX Electric Fan", fan1.getProductName());
    }

    @Test
    public void getProductPrice() {
        assertEquals("Test for getProductPrice getter", 18_000.00, fan1.getProductPrice(), 0);
        assertNotEquals("Test for getProductPrice getter", 18_000.01, fan1.getProductPrice(), 0);
    }

    @Test
    public void setProductPrice() {
        fan1.setProductPrice(30_000.00);
        assertEquals("Product price should be modifiable with the setProductPrice() method", 30_000, fan1.getProductPrice(), 0);
        assertNotEquals("Product price should not eqal former price after modification", 18_000, fan1.getProductPrice(), 0);
    }

    @Test
    public void getProductCategory() {
        assertEquals("Different products of the same category should return same category object", fan1.getProductCategory(), fan2.getProductCategory());
        assertEquals("Different products of the different category should return different category object", fan1.getProductCategory(), chair1.getProductCategory());
    }

    @Test
    public void setProductCategory() {
        assertEquals("Initially, the category of chair2 is fans", fans, chair2.getProductCategory());
        assertNotEquals("Initially, the category of chair2 is not chairs", chairs, chair2.getProductCategory());
        chair2.setProductCategory(chairs);
        assertEquals("Product's category should be modifiable using the setProductCategory() method", chairs, chair2.getProductCategory());
        assertNotEquals("Product's category should not equal fans anymore", fans, chair2.getProductCategory());
    }
}