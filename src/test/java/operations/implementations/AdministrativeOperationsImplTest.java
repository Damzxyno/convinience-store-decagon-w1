package operations.implementations;

import enums.Designation;
import enums.Qualification;
import exceptions.*;
import models.*;
import operations.interfaces.AdministrativeOperations;
import operations.interfaces.CustomerOperations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministrativeOperationsImplTest {
    AdministrativeOperations administrativeOperations;
    CustomerOperations customerOperations;

    Customer stephen;
    Staff manager;
    Staff cashier;
    Company damzxynoStore;

    Product sweet1;
    Category sweets;

    @Before
    public void setUp(){
        administrativeOperations = new AdministrativeOperationsImpl();
        customerOperations = new CustomerOperationsImpl();

        stephen = new Customer("Stephen", "Ebube");
        manager = new Staff("Hope", "Odu", Designation.MANAGER);
        cashier = new Staff("Tobi", "Oluwaseun", Designation.CASHIER);
        damzxynoStore = new Company("Damzxyno's Convenience Store");
        sweets = new Category("SWEETS", "Nigerian made chocolate, vanilla and banana sweets");
        sweet1 = new Product("OK pop sweet", 300, sweets);
    }

    @Test
    public void testForSuccessfulAddingOfProductToCompany() throws NotAuthorizedException, OutOfStockException {
        assertTrue("Test for initial emptiness of company storage", damzxynoStore.getCompanyGoods().isEmpty());
        administrativeOperations.addProductToCompany(damzxynoStore, manager, sweet1, 100);
        assertFalse("Test for presence of product in company storage", damzxynoStore.getCompanyGoods().isEmpty());
        assertTrue("Test for a particular product in company store", damzxynoStore.getCompanyGoods().containsProduct(sweet1));
        assertEquals("Test for quantity of sweet1 in company", 100, damzxynoStore.getCompanyGoods().getProductQuantity(sweet1));
    }

    @Test
    public void testForUnauthorizedPersonelAddingAddingProductToCompany() {
        Exception exception = assertThrows(NotAuthorizedException.class, () -> administrativeOperations.addProductToCompany(damzxynoStore, cashier, sweet1, 600));
        assertTrue("Only Manager can add product to sell in company!".contains(exception.getMessage()));
    }

    @Test
    public void testForSellingToCustomerWhenTheyHaveNotCheckedOut()  {
        Exception exception = assertThrows(InvalidOperationException.class, () -> administrativeOperations.sellProductsInCart(damzxynoStore, manager, stephen));
        assertTrue("Customer has not checkedOut!".contains(exception.getMessage()));
    }

    @Test
    public void testForCashierCustomerBuyingWithInsufficientFund() throws OutOfStockException, StockDoesNotExistException, NotAuthorizedException {
        administrativeOperations.addProductToCompany(damzxynoStore, manager, sweet1, 100);
        customerOperations.addProductToCart(damzxynoStore, stephen, sweet1, 5);
        Exception exception = assertThrows(InsufficientFundException.class, ()-> administrativeOperations.sellProductsInCart(damzxynoStore, cashier, stephen));
        assertTrue("Customer fund is less than the products grand price!".contains(exception.getMessage()));
    }

    @Test
    public void testForSuccessfulSellingAndDispensingReceiptToCustomerByCashier() throws OutOfStockException, StockDoesNotExistException, NotAuthorizedException, InvalidOperationException, InsufficientFundException {
        assertEquals("Initial balance of company account is zero", 0.0, damzxynoStore.getCompanyAccount(), 0 );
        administrativeOperations.addProductToCompany(damzxynoStore, manager, sweet1, 200);
        customerOperations.deposit(stephen, 1_000);
        customerOperations.addProductToCart(damzxynoStore, stephen, sweet1, 3);
        customerOperations.purchaseGoodsInCart(stephen);
        String receiptContent = administrativeOperations.sellProductsInCart(damzxynoStore, cashier, stephen);
        assertEquals("Customer: Stephen's wallet balance should reduce by the price of his purchase",
                100, stephen.getWalletValue(), 0);
        assertEquals("Company balance is now 900.0", 900, damzxynoStore.getCompanyAccount(),0);
        System.out.println(receiptContent);
        assertTrue(receiptContent.contains("1 | Product: OK pop sweet | Category: SWEETS | Quantity : 3 | Price per Unit: ₦300.00 | Total Price of Product: ₦900.00\n" +
                " Total Products Units: 3 || GrandPrice: ₦900.00"));
    }

    @Test
    public void testForOtherPersonOtherThanTheCashierSellingAndDispensingToCustomer() throws InsufficientFundException {
        customerOperations.purchaseGoodsInCart(stephen);
        Exception exception = assertThrows(NotAuthorizedException.class, () -> administrativeOperations.sellProductsInCart(damzxynoStore, manager, stephen));
        assertTrue("Only Cashiers can sell and dispense receipts to customers!".contains(exception.getMessage()));
    }

    @Test
    public void testForUnauthorizedStaffHiringAnApplicant(){
        Applicant applicant = new Applicant("Chisom", "Uchenna", Qualification.SSCE);
        Exception exception = assertThrows(NotAuthorizedException.class, () -> administrativeOperations.hireCashier(damzxynoStore, cashier, applicant));
        assertEquals("Only the Manager can hire an applicant!", exception.getMessage());
    }

    @Test
    public void testForHiringApplicantWhoseInfoIsNotInTheApplicantListOfCompany(){
        Applicant applicant = new Applicant("Udeme", "Evangel", Qualification.OND);
        Exception exception = assertThrows(InvalidOperationException.class, () -> administrativeOperations.hireCashier(damzxynoStore, manager, applicant));
        assertEquals("Applicant object exist but not in company's record", "You can't hire applicant that has not applied!", exception.getMessage());
    }

    @Test
    public void testForSuccessfulCreationOfStaffAndPuttingInCompanyList() throws InvalidOperationException, NotAuthorizedException {
        Applicant applicant = new Applicant("Ifeoluwa", "Olaseyi", Qualification.SSCE);
        assertTrue("Initially, company does not contain any staff", damzxynoStore.getStaffList().isEmpty());
        new ApplicantOperationImpl().apply(damzxynoStore,applicant);
        administrativeOperations.hireCashier(damzxynoStore, manager, applicant);
        assertEquals("Now the staffList should increase by 1", 1, damzxynoStore.getStaffList().size());
    }
}