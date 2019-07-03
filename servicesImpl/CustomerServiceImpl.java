package coupons.coupon.system.servicesImpl;

import java.util.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coupons.coupon.system.entities.ClientType;
import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.entities.Customer;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.CouponException;
import coupons.coupon.system.exceptions.InCorrectValueExeption;
import coupons.coupon.system.exceptions.UniqueValueException;
import coupons.coupon.system.repository.CouponRepository;
import coupons.coupon.system.repository.CustomerRepository;
import coupons.coupon.system.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CouponRepository coupRepository;
	@Autowired
	private CustomerRepository custRepository;

	private LoggedUser lu = new LoggedUser();
	

	/**
	 * This method return an arrayList of all the coupons that this customer
	 * purchased
	 */
	@Override
	public Collection<Coupon> getAllCustomerCoupons(long customerId) {

		return custRepository.getCustomersCoupons(customerId);
	}

	/**
	 * This method return an arrayList of all coupons that the customer ordered from
	 * the same type.
	 */
	@Override
	public List<Coupon> getCustomerCouponByType(CouponType type, long customerId) {

		return custRepository.getCouponByType(customerId, type);
	}

	/**
	 * This method return an arrayList of all coupons that the customer ordered up
	 * to a specific price.
	 */
	@Override
	public List<Coupon> getCustomerCouponByPrice(Double price, long customerId) {

		return custRepository.getCustomerCouponByPrice(price, customerId);
	}

	/**
	 * This method allow the customer to purchase a coupon only if the amount is
	 * over 0 and if the coupon is not expired.
	 * @throws UniqueValueException when the value is unique and the user give the same value
	 * @throws CouponException error with the coupon
	 */
	@Override
	@Transactional
	public void purchaseCoupon(long id, long customerId) throws UniqueValueException, CouponException {

		Coupon coupon = null;
		System.err.println(customerId);
		Coupon customerCoupon = (Coupon) custRepository.findCouponByCustomerId(customerId, id);
		System.out.println(customerCoupon);
		if (customerCoupon != null) {
				throw new UniqueValueException("You have allready purchased this coupon!!!", customerCoupon.getTitle());
		} else {
			coupon = coupRepository.findById(id).get();
			if (coupon.getAmount() <= 0) {
					throw new CouponException("This coupon sold out!", null);
			}else if (coupon.getEndDate().before(new Date(System.currentTimeMillis()))) {
					throw new CouponException("This coupon expiered!", null);
			}else {
			List<Customer> customers = (List<Customer>) coupon.getCustomers();
			System.out.println(customerId);
			Customer cust = custRepository.findById(customerId).get();
			List<Coupon> customerCoupons = (List<Coupon>) cust.getCupons();
			customerCoupons.add(coupon);
			coupon.setAmount(coupon.getAmount() - 1);
			customers.add(cust);
			custRepository.save(cust);
			coupRepository.save(coupon);
			}
		}
	}

	/**
	 * This method perform a log in to the system
	 * @throws InCorrectValueExeption when the value is in correct
	 */
	@Override
	public LoggedUser login(String name, String password, ClientType type ) throws InCorrectValueExeption {
		Customer customer;
		
		if( name == null || name.isEmpty()) {
			throw new InCorrectValueExeption("customer name must Exsist!!!", name);
		}
		
		 customer = custRepository.findByCustName(name.trim());
		 System.err.println(customer);
		if(customer == null) {
				throw new InCorrectValueExeption("This customer name is not Exsist!!!", name);
		}else {
			if(type.equals(ClientType.CUSTOMER)) {
			if (password.trim().equals(customer.getPassword().trim())) {
				this.lu.setName(customer.getCustName());
				this.lu.setPassword(customer.getPassword());
				this.lu.setId(customer.getCustomerId());
				this.lu.setClientType(ClientType.CUSTOMER);
				return lu;
			}else {
				throw new InCorrectValueExeption("The password is incorrect!!!", password);
			}
		}throw new InCorrectValueExeption("The Client type incorrect!!!", null);
		}
		}
	

	/**
	 * This method return the customer's details
	 */
	@Override
	public Customer getCustomer(String name) {
		
			return custRepository.findByCustName(name);
		}
		
	

	/**
	 * This method return specific coupon.
	 */
	@Override
	public Coupon getCoupon(long customerId, String name) {
		
		return custRepository.getCustomerCoupon(customerId, name) ;
	}


	
}
