package operations.interfaces;

import exceptions.OutOfStockException;
import exceptions.StockDoesNotExistException;
import models.Company;
import models.Customer;
import models.Product;

public interface CustomerOperations extends  CommonOperations {
    public void addProductToCart(Company company, Customer customer, Product product, int quantity) throws OutOfStockException, StockDoesNotExistException;
    public void addProductToCart(Company company, Customer customer, Product product) throws OutOfStockException, StockDoesNotExistException;
    public void deposit(Customer customer, double amount);


}
