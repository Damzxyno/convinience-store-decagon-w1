package operations.implementations;

import exceptions.OutOfStockException;
import exceptions.StockDoesNotExistException;
import models.Category;
import models.Company;
import models.Customer;
import models.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerOperationsImplTest {
    private Company company;
    private Customer customer1;
    private Category fans;
    private Category chairs;
    private Product chair1;
    private Product chair2;
    private Product fan1;
    private Product fan2;
    private CustomerOperationsImpl customerOperations;

    @Before
    public void setUp(){
        company = new Company("Damzxyno Convenience Store", "Victoria Island");
        customerOperations = new CustomerOperationsImpl();
        customer1 = new Customer("Ebube", "Chukwu");
        fans = new Category("FANS", "Best made quality ceiling and standing fans" );
        chairs = new Category("CHAIRS", "Trousers, dresses, skirts and shorts of different materials" );
        fan1 = new Product("OX Electric Fan", 18_000.00, fans);
        fan2 = new Product("ORL Electric Fan", 20_000.00, fans);
        chair1 = new Product("Burgundy three seater Chair", 230_000.00, chairs);
        chair2 = new Product("Blue leather couch", 230_000, fans);

    }

    @Test
    public void addProductToCartTest() throws OutOfStockException, StockDoesNotExistException {
        company.getCompanyGoods().addToProductsList(fan1, 20);
        assertTrue("Check for empty cart, customer 1", customer1.getCart().isEmpty());
        customerOperations.addProductToCart(company, customer1, fan1,15);
        assertTrue("Check for presence of fan1 in customer 1's cart", customer1.getCart().containsProduct(fan1));
        assertFalse("Check for presence of fan2 which is not added in customer 1's cart", customer1.getCart().containsProduct(fan2));
        assertEquals("Check for correct quantity of product(fan1) in customer's cart", 15, customer1.getCart().getProductQuantity(fan1));
        Exception exception = assertThrows(OutOfStockException.class, () -> {customerOperations.addProductToCart(company, customer1, fan1, 21);});
        assertTrue("The company does not have up to the quantity required!".contains(exception.getMessage()));

    }


}