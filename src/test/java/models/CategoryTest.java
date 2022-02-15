package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    private Category fans;
    private Product hoodie;
    private Product trouser;
    private Product fan1;

    @Before
    public void setUp() {
        fans = new Category("FANS", "Best made quality ceiling and standing fans");
        Category clothes = new Category("CLOTHES", "Trousers, dresses, skirts and shorts of different materials");
        hoodie = new Product("Green developers' hodie", 54_000, clothes);
        trouser = new Product("Blue chinos trouser", 12_000, clothes);
        fan1 = new Product("OX standing fan", 18_000, clothes);
    }

    @Test
    public void getCategoryName() {
        assertEquals("Testing the getCategory() method.", "FANS", fans.getCategoryName());
        assertEquals("Category Name should remain constant from products to Product.",hoodie.getProductCategory(), trouser.getProductCategory());
    }

    @Test
    public void setCategoryName() {
        assertNotEquals("Product category should be modifiable Test 1", fans, fan1.getProductCategory());
        fan1.setProductCategory(fans);
        assertEquals("Product category should be modifiable Test 2", fans, fan1.getProductCategory());
    }

    @Test
    public void setCategoryDescription(){

    }

    @Test
    public void testToString() {
        System.out.println(fan1.toString());
    }
}