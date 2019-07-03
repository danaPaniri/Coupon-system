package coupons.coupon.system.services;


import java.util.List;

import coupons.coupon.system.entities.ClientType;
import coupons.coupon.system.entities.Company;
import coupons.coupon.system.entities.Customer;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.InCorrectValueExeption;
import coupons.coupon.system.exceptions.MissingValueExeption;
import coupons.coupon.system.exceptions.NotConnectedError;
import coupons.coupon.system.exceptions.NotFoundException;
import coupons.coupon.system.exceptions.UniqueValueException;

public interface AdminService {

	
	public void createCompany(Company company) throws UniqueValueException, NotConnectedError, MissingValueExeption;
	public void removeCompany(String name) throws NotConnectedError, NotFoundException ;
	public void updateCompany (Company company) throws NotConnectedError, MissingValueExeption, NotFoundException ;
	public void createCustomer(Customer cust) throws UniqueValueException, NotConnectedError, MissingValueExeption ;
	public void removeCustomer(String name) throws NotConnectedError, NotFoundException;
	public void updateCustomer( Customer cust ) throws NotConnectedError, MissingValueExeption, NotFoundException ; 
	public Customer getCustomer(String name) throws NotConnectedError, NotFoundException ;
	public List<Customer> getAllCustomers() throws NotConnectedError ;
	public List<Company> getAllCompanies() throws NotConnectedError;
	public Company getCompany(String name) throws NotConnectedError, NotFoundException;
	public LoggedUser login(String name, String password, ClientType type) throws InCorrectValueExeption;
	public Company getCompanyById(long id) throws NotConnectedError;
	
}
