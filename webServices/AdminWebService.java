package coupons.coupon.system.webServices;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import coupons.coupon.system.entities.Company;
import coupons.coupon.system.entities.Customer;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.MissingValueExeption;
import coupons.coupon.system.exceptions.NotConnectedError;
import coupons.coupon.system.exceptions.NotFoundException;
import coupons.coupon.system.exceptions.UniqueValueException;
import coupons.coupon.system.servicesImpl.AdminServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/REST/admin")
public class AdminWebService {
	
	
	@Autowired
	private AdminServiceImpl admin;
	
	private LoggedUser loggedUsser(HttpSession session) {
		return (LoggedUser) session.getAttribute("LoggedUser");
	}
	
	/**
	 * This method allow the admin to create a new company and insert it to the data base, the admin can
	 * not add a company with a name that already exist!
	 * @param company company details
	 * @param session user name
	 * @throws UniqueValueException when the value is unique and the user give the same value
	 * @throws NotConnectedError when the user is not connected
	 * @throws MissingValueExeption when the user did not send all the parameters
	 */
	@RequestMapping( path = "createCompany", method=RequestMethod.POST)
	public void createCompany( @RequestBody Company company, HttpSession session ) throws UniqueValueException, NotConnectedError, MissingValueExeption{
		if (loggedUsser(session).getName().equals("admin".trim())) {
		admin.createCompany(company);
		}else{
			throw new NotConnectedError("You are not connected");
			}
	}
	/**
	 * This method remove company, coupon of the company, coupon of the company that
	 * customer purchase from the data base..
	 * @param name company name
	 * @throws NotConnectedError when the user is not connected
	 * @throws NotFoundException when value was not found 
	 */
	@RequestMapping( path = "removeCompany", method=RequestMethod.DELETE)
	public void removeCompany( @RequestParam String name  ) throws NotConnectedError, NotFoundException {
		admin.removeCompany( name );
	}
	
	/**
	 * This method allow the admin update a company details beside
	 *  the company name.
	 * @param company company details
	 * @throws NotConnectedError  when the user is not connected
	 * @throws MissingValueExeption when the user did not send all the parameters
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "updateCompany", method=RequestMethod.PUT)
	public void updateCompany ( @RequestBody Company company ) throws NotConnectedError, MissingValueExeption, NotFoundException {
		admin.updateCompany( company );
	}
	
	/**
	 * This method allow the admin to create a new customer and insert it to the data base, you can
	 * not add a customer with a name that already exist!
	 * @param cust (customer)
	 * @throws UniqueValueException when the value is unique and the user give the same value
	 * @throws NotConnectedError when the user is not connected
	 * @throws MissingValueExeption when the user did not send all the parameters
	 */
	@RequestMapping( path = "createCustomer", method=RequestMethod.POST)
	public void createCustomer( @RequestBody Customer cust ) throws UniqueValueException, NotConnectedError, MissingValueExeption{
		admin.createCustomer( cust );
	}
	/**
	 * This method removing a customer from the data base, it also remove coupons
	 * that purchased by this customer.
	 * @throws NotConnectedError when the user is not connected
	 * @param name customer name
	 * @throws NotConnectedError when the user is not connected
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "removeCustomer", method=RequestMethod.DELETE)
	public void removeCustomer( @RequestParam String name ) throws NotConnectedError, NotFoundException {
		admin.removeCustomer( name );
	}
	
	/**
	 * This method allow the admin to update a customer details beside customer name.
	 * @param cust (customer)
	 * @throws NotConnectedError when the user is not connected
	 * @throws MissingValueExeption when the user did not send all the parameters
	 * @throws NotFoundException  when value was not found
	 */
	@RequestMapping( path = "updateCustomer", method=RequestMethod.PUT)
	public void updateCustomer( @RequestBody Customer cust ) throws NotConnectedError, MissingValueExeption, NotFoundException {
		admin.updateCustomer( cust );
	}
	
	/**
	 * This method show a specific customer details using the customer name.
	 * @param name customer name
	 * @return one customer details
	 * @throws NotConnectedError when the user is not connected
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "getCustomer", method=RequestMethod.GET)
	public Customer getCustomer( @RequestParam String name ) throws NotConnectedError, NotFoundException {
		return admin.getCustomer( name );
	}
	
	/**
	 * This method show all customers details.
	 * @return customers
	 * @throws NotConnectedError when the user is not connected
	 */
	@RequestMapping( path = "getAllCustomer", method=RequestMethod.GET)
	public List<Customer> getAllCustomers() throws NotConnectedError {
		return admin.getAllCustomers();
	}
	
	/**
	 * This method return an arrayList of all companies...
	 * @param session user details
	 * @return companies
	 * @throws NotConnectedError when the user is not connected
	 */
	@RequestMapping( path = "getAllCompanies", method=RequestMethod.GET)
	public List<Company> getAllCompanies(HttpSession session) throws NotConnectedError{
		return admin.getAllCompanies();
		
	}
	
	/**
	 * This method return a specific company details using the company name.
	 * @param name company name
	 * @return company's details
	 * @throws NotConnectedError when the user is not connected
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "getCompany", method=RequestMethod.GET)
	public Company getCompany( @RequestParam String name ) throws NotConnectedError, NotFoundException {
		return admin.getCompany( name );
	}
	
	/**
	 * This method return a specific company details using the company id.
	 * @param id company id
	 * @return company's details
	 * @throws NotConnectedError when the user is not connected
	 */
	@RequestMapping( path = "getCompanyId", method=RequestMethod.GET)
	public Company getCompanyById(@RequestParam long id) throws NotConnectedError {
       return admin.getCompanyById(id);
	}
	

}
