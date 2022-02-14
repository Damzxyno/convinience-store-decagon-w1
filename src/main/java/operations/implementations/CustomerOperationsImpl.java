package operations.implementations;

import exceptions.InsufficientFundException;
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
    public void purchaseGoodsInCart(Customer customer) throws InsufficientFundException{
        if (customer.getCart().getTotalPriceOfGoods() > customer.getWalletValue()) throw new InsufficientFundException("Amount in customer wallet is less the total amount of products.");
        else customer.checkOut();
    }

    @Override
    public void deposit(Customer customer, double amount){customer.fundWalletValue(amount);}


}
