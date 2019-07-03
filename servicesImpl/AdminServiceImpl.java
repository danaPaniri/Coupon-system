package coupons.coupon.system.servicesImpl;

import coupons.coupon.system.entities.ClientType;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import coupons.coupon.system.entities.Company;
import coupons.coupon.system.entities.Customer;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.InCorrectValueExeption;
import coupons.coupon.system.exceptions.MissingValueExeption;
import coupons.coupon.system.exceptions.NotConnectedError;
import coupons.coupon.system.exceptions.NotFoundException;
import coupons.coupon.system.exceptions.UniqueValueException;
import coupons.coupon.system.repository.CompanyRepository;
import coupons.coupon.system.repository.CouponRepository;
import coupons.coupon.system.repository.CustomerRepository;
import coupons.coupon.system.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CompanyRepository compRepository;
	@Autowired
	private CustomerRepository custRepository;
	@Autowired
	private CouponRepository coupRepository;
	
	private LoggedUser lu = new LoggedUser();
	
	

	/**
	 *  This method check if the user name and the password is correct and performing
	 * an entering to the system as the admin.
	 * 
	 */
	@Override
	public LoggedUser login(String name, String password, ClientType type) throws InCorrectValueExeption {

		if (type.equals(ClientType.ADMIN)) {
			if (name.trim().equals("admin") && password.trim().equals("1234")) {
				System.out.println("admin o.k");
				lu.setName(name);
				lu.setPassword(password);
				lu.setClientType(ClientType.ADMIN);
				return lu;
			} 
				throw new InCorrectValueExeption("Name or Password in correct!", null);
		}
		throw new InCorrectValueExeption("The client type in correct!", null);
	}

	/**
	 * This method allow the admin to create a new company and insert it to the data base, the admin can
	 * not add a company with a name that already exist!
	 */
	@Override
	public void createCompany(Company company) throws UniqueValueException, NotConnectedError, MissingValueExeption {
		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}
			if(company.getCompName()==null ||company.getCompName().isEmpty() || company.getEmail()==null || company.getEmail().isEmpty()|| company.getPassword()==null||company.getPassword().isEmpty() ){
				throw new MissingValueExeption("All parameters must exsist!");
			}
			try {
				compRepository.save(company);
			} catch (Exception ex) {
				throw new UniqueValueException("This company name is already exsist!!!", company.getCompName());
			}
		}

	/**
	 * This method remove company, coupon of the company, coupon of the company that
	 * customer purchase from the data base..
	 */
	@Transactional
	@Override
	public void removeCompany(String name) throws NotConnectedError, NotFoundException {
		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			Company upComp = compRepository.findByCompName(name);
			if( upComp == null) {
				throw new NotFoundException("No company found with this name: "+ name);
			}
			coupRepository.removeCompanyCoupons(upComp.getId());
			compRepository.deleteById(upComp.getId());
		}
	}

	/**
	 * This method allow the admin update a company details beside
	 *  the company name.
	 */
	@Override
	@Modifying
	@Transactional
	public void updateCompany(Company company) throws NotConnectedError, MissingValueExeption, NotFoundException {

		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			if(company.getCompName()==null ||company.getCompName().isEmpty() || company.getEmail()==null || company.getEmail().isEmpty()|| company.getPassword()==null||company.getPassword().isEmpty() ){
				throw new MissingValueExeption("All parameters must exsist!");
			}
			Company upComp = compRepository.findByCompName(company.getCompName());
			if( upComp == null) {
				throw new NotFoundException("No company found with this name: "+ company.getCompName());
			}
			
			upComp.setCompName(upComp.getCompName());
			upComp.setEmail(company.getEmail());
			upComp.setPassword(company.getPassword());
			compRepository.save(upComp);
		}
	}

	/**
	 * This method allow the admin to create a new customer and insert it to the data base, you can
	 * not add a customer with a name that already exist!
	 */
	@Override
	public void createCustomer(Customer cust) throws UniqueValueException, NotConnectedError, MissingValueExeption {
		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			if(cust.getCustName()==null ||cust.getCustName().isEmpty() || cust.getEmail()==null || cust.getEmail().isEmpty()|| cust.getPassword()==null||cust.getPassword().isEmpty() ){
				throw new MissingValueExeption("All parameters must exsist!");
			}
			try {
				custRepository.save(cust);
			} catch (Exception ex) {
				throw new UniqueValueException("This customer name is already exsist!!!", cust.getCustName());
			}
		}
	}

	/**
	 * This method removing a customer from the data base, it also remove coupons
	 * that purchased by this customer.
	 */
	@Override
	public void removeCustomer(String name) throws NotConnectedError, NotFoundException {
		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			Customer cust = custRepository.findByCustName(name);
			if( cust == null) {
				throw new NotFoundException("No customer found with this name: "+ name);
			}
			custRepository.deleteById(cust.getCustomerId());
		}
	}
	
	/**
	 * This method allow the admin to update a customer details beside customer name.
	 */
	@Override
	public void updateCustomer(Customer cust) throws NotConnectedError, MissingValueExeption, NotFoundException {
		
		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			if(cust.getCustName()==null ||cust.getCustName().isEmpty() || cust.getEmail()==null || cust.getEmail().isEmpty()|| cust.getPassword()==null||cust.getPassword().isEmpty() ){
				throw new MissingValueExeption("All parameters must exsist!");
			}
			Customer updateCustomer = custRepository.findByCustName(cust.getCustName());
			if(updateCustomer==null) {
				throw new NotFoundException("No customer found with this name: " + cust.getCustName());
			}
			updateCustomer.setEmail(cust.getEmail());
			updateCustomer.setPassword(cust.getPassword());
			custRepository.save(updateCustomer);
		}
	}

	/**
	 * This method show a specific customer details using the customer name.
	 */
	@Override
	public Customer getCustomer(String name) throws NotConnectedError, NotFoundException {
		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}
	        if (lu.getName().equals("admin")) {
			Customer customer = custRepository.findByCustName(name);
			if(customer==null) {
				throw new NotFoundException("No customer found with this name: " +name);
			}
			return customer;
			}return null;	
	}

	/**
	 * This method return a specific company details using the company name.
	 */
	@Override
	public Company getCompany(String name) throws NotConnectedError, NotFoundException {

		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			Company company = compRepository.findByCompName(name);
			if( company == null) {
				throw new NotFoundException("No company found with this name: "+ name);
			}
			return company; 
		}
		return null;
	}
	
	
	/**
	 * This method return a specific company details using the company id.
	 */
	@Override
	public Company getCompanyById(long id) throws NotConnectedError {

		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			Company company = compRepository.findById(id).get();
			System.out.println(company);
			return company; 
		}
		return null;
	}
	/**
	 * This method show all customers details.
	 */
	@Override
	public List<Customer> getAllCustomers() throws NotConnectedError {

		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			return custRepository.findAll();
		}
		return null;
	}

	/**
	 * This method return an arrayList of all companies...
	 */
	@Override
	public List<Company> getAllCompanies() throws NotConnectedError {
		try {
			if (lu.getName().equals(null));
		} catch (Exception e) {
			throw new NotConnectedError("You are not connected!!!");
		}if (lu.getName().equals("admin")) {
			return compRepository.findAll();
		}

		return null;
	}

}
