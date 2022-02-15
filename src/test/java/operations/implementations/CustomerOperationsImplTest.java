package operations.implementations;

import exceptions.InsufficientFundException;
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
    private Product fan1;
    private Product fan2;
    private CustomerOperationsImpl customerOperations;

    @Before
    public void setUp(){
        company = new Company("Damzxyno Convenience Store");
        customerOperations = new CustomerOperationsImpl();
        customer1 = new Customer("Ebube", "Chukwu");
        Category fans = new Category("FANS", "Best made quality ceiling and standing fans");
        fan1 = new Product("OX Electric Fan", 18_000.00, fans);
        fan2 = new Product("ORL Electric Fan", 20_000.00, fans);
    }

    @Test
    public void testToSuccessfullyAddProductToCartList() throws OutOfStockException, StockDoesNotExistException {
        company.getCompanyGoods().addToProductsList(fan1, 20);
        assertTrue("Check for empty cart, customer 1", customer1.getCart().isEmpty());
        customerOperations.addProductToCart(company, customer1, fan1,15);
        assertTrue("Check for presence of fan1 in customer 1's cart", customer1.getCart().containsProduct(fan1));assertFalse("Check for presence of fan2 which is not added in customer 1's cart", customer1.getCart().containsProduct(fan2));
        assertEquals("Check for correct quantity of product(fan1) in customer's cart", 15, customer1.getCart().getProductQuantity(fan1));
        Exception exception = assertThrows(OutOfStockException.class, () -> customerOperations.addProductToCart(company, customer1, fan1, 21));
        assertTrue("The company does not have up to the quantity required!".contains(exception.getMessage()));
    }

   @Test
    public  void testToSuccessfullyShowCashierThatCustomerIsReadyToBuyProductsInCart() throws InsufficientFundException {
        assertFalse("Customer isn't initially ready to checkout", customer1.hasCheckedOut());
        customerOperations.purchaseGoodsInCart(customer1);
        assertTrue("Customer is now ready to have cashier sell him/her product", customer1.hasCheckedOut());
   }

   @Test
    public void customerDepositShouldReflectInHisAccount() {
        assertEquals("Customer initially has no money" , 0.0, customer1.getWalletValue(), 0);
        customerOperations.deposit(customer1, 40_000);
        assertEquals("Customer has now deposited 40_000 in his account", 40_000, customer1.getWalletValue(), 0);
   }

}