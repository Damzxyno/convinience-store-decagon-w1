package operations.implementations;

import enums.Designation;
import exceptions.InsufficientFundException;
import exceptions.InvalidOperationException;
import exceptions.NotAuthorizedException;
import exceptions.OutOfStockException;
import models.Company;
import models.Customer;
import models.Product;
import models.Staff;
import operations.interfaces.AdministrativeOperations;

import java.util.Date;

public class AdministrativeOperationsImpl implements AdministrativeOperations {
    @Override
    public void sellProductsInCart(Company company, Staff staff, Customer customer) throws OutOfStockException, InsufficientFundException, NotAuthorizedException, InvalidOperationException {
        if (customer.getWalletValue() < customer.getCart().getTotalPriceOfGoods()) throw new InsufficientFundException("Customer fund is less than the products grand price!");
        else if (!customer.hasCheckedOut()) throw new InvalidOperationException("Customer has not checkedOut!");
        else if (staff.getDesignation().equals(Designation.CASHIER)){
           String receiptBody = customer.getCart().generateRegisterAndRemoveProductsFromAnotherStorage(company.getCompanyGoods());
            customer.deductFromWalletValue(customer.getCart().getTotalPriceOfGoods());
            company.fundCompanyAccount(customer.getCart().getTotalPriceOfGoods());
            String receipt = " -------------- " +
                    company.getCompanyName() +
                    " -------------- \n :::::::::: RECEIPT ::::::::::" +
                    "\n :: Customer Name: " +
                    customer.getFirstName() +
                    ", " +
                    customer.getLastName() +
                    ". ::" +
                    receiptBody +
                    "\n Thanks for patronising us at " +
                    company.getCompanyName() + ".";

            Date date = new Date();
            createAFileToSaveData("receipt" + date.getTime() + ".txt", receipt);
        } else throw new NotAuthorizedException("Only Cashiers can sell and dispense receipts to customers!");
    }

    @Override
    public void addProductToCompany(Company company, Staff staff, Product product, int quantity) throws NotAuthorizedException {
        if(staff.getDesignation().equals(Designation.MANAGER)){
            company.getCompanyGoods().addToProductsList(product, quantity);
        } else throw new NotAuthorizedException("Only Manager can add product to sell in company!");
    }
}
