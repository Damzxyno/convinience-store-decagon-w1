package operations.interfaces;

import exceptions.InsufficientFundException;
import exceptions.InvalidOperationException;
import exceptions.NotAuthorizedException;
import exceptions.OutOfStockException;
import models.*;

public interface AdministrativeOperations extends CommonOperations {
    void sellProductsInCart(Company company, Staff staff, Customer customer) throws OutOfStockException, InsufficientFundException, NotAuthorizedException, InvalidOperationException;
    void addProductToCompany(Company company, Staff staff, Product product, int quantity) throws NotAuthorizedException;
    void hireCashier(Company company, Staff manager, Applicant applicant) throws NotAuthorizedException, InvalidOperationException;
}
