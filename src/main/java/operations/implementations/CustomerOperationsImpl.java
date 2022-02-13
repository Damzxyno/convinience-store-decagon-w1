package operations.implementations;

import exceptions.OutOfStockException;
import exceptions.StockDoesNotExistException;
import models.Company;
import models.Customer;
import models.Product;
import operations.interfaces.CustomerOperations;

public class CustomerOperationsImpl implements CustomerOperations {
    @Override
    public void addProductToCart(Company company, Customer customer, Product product, int quantity) throws OutOfStockException, StockDoesNotExistException {
        if (!company.getCompanyGoods().containsProduct(product)) throw new StockDoesNotExistException("Company does not have this product!");
        else if (company.getCompanyGoods().containsProductInAParticularQuantity(product, quantity)) {
            customer.getCart().addToProductsList(product, quantity);
        } else throw new OutOfStockException("The company does not have up to the quantity required!");
    }

    @Override
    public void addProductToCart(Company company, Customer customer, Product product) throws OutOfStockException, StockDoesNotExistException {
        addProductToCart(company, customer, product, 1);
    }

    @Override
    public void deposit(Customer customer, double amount){
        customer.fundWalletValue(amount);
    }

    
}
