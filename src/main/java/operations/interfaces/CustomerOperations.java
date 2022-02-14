package operations.interfaces;

import exceptions.InsufficientFundException;
import exceptions.OutOfStockException;
import exceptions.StockDoesNotExistException;
import models.Company;
import models.Customer;
import models.Product;

public interface CustomerOperations extends  CommonOperations {
     void addProductToCart(Company company, Customer customer, Product product, int quantity) throws OutOfStockException, StockDoesNotExistException;
     void purchaseGoodsInCart(Customer customer) throws InsufficientFundException;
     void deposit(Customer customer, double amount);
}
