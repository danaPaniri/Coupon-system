package coupons.coupon.system.webServices;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.entities.Customer;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.CouponException;
import coupons.coupon.system.exceptions.InCorrectValueExeption;
import coupons.coupon.system.exceptions.UniqueValueException;
import coupons.coupon.system.servicesImpl.CustomerServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("REST/customer")
public class CustomerWebService {
	
	
	@Autowired
	private CustomerServiceImpl customer;
	
	private LoggedUser loggedUsser(HttpSession session) {
		return (LoggedUser) session.getAttribute("LoggedUser");
	}
	/**
	 * This method return an arrayList of all the coupons that this customer
	 * purchased.
	 * @param session (user id)
	 * @return coupons that the customer purchased.
	 */
	@RequestMapping( path = "getCoupons", method=RequestMethod.GET)
	public Collection<Coupon> getAllCustomerCoupons( HttpSession session){
		return customer.getAllCustomerCoupons( loggedUsser(session).getId() );
	}
	
	/**
	 * This method return an arrayList of all coupons that the customer ordered from
	 * the same type.
	 * @param type coupon type
	 * @param session (user id)
	 * @return coupons of the same type
	 */
	@RequestMapping( path = "getCouponsByType", method=RequestMethod.GET)
	public List<Coupon> getCustomerCouponByType(@RequestParam CouponType type , HttpSession session){
		return customer.getCustomerCouponByType(type , loggedUsser(session).getId());
	}
	
	/**
	 * his method return an arrayList of all coupons that the customer ordered up
	 * to a specific price.
	 * @param price price of the coupon
	 * @param session (user id)
	 * @return coupons up to a specific price.
	 */
	@RequestMapping( path = "getCouponsByPrice", method=RequestMethod.GET)
	public List<Coupon> getCustomerCouponByPrice(@RequestParam Double price , HttpSession session){
		return customer.getCustomerCouponByPrice(price , loggedUsser(session).getId());
	}
	
	/**
	 * This method allow the customer to purchase a coupon only if the amount is
	 * over 0 and if the coupon date was'nt expire.
	 * @param id (coupon id)
	 * @param session (user id)
	 * @throws UniqueValueException when the value is in correct
	 * @throws CouponException when there is a error in the coupon
	 */
	@RequestMapping( path = "purchaseCoupon", method=RequestMethod.POST)
	public void purchaseCoupon(@RequestParam long id , HttpSession session) throws UniqueValueException, CouponException{
		customer.purchaseCoupon(id , loggedUsser(session).getId());
	}
	
	/**
	 * This method return the customer's details.
	 * @param session (user id)
	 * @return customer's details
	 * @throws InCorrectValueExeption when the value is in correct
	 */
	@RequestMapping( path = "getCustomer", method=RequestMethod.GET)
	public Customer getCustomer( HttpSession session ) throws InCorrectValueExeption {
		return customer.getCustomer( loggedUsser(session).getName() );
	}
	
	/**
	 * This method return specific coupon.
	 * @param name coupon name
	 * @param session (user id)
	 * @return specific coupon's details.
	 */
	@RequestMapping( path = "getCoupon", method=RequestMethod.GET)
	public Coupon getCoupon(String name, HttpSession session) {
		return customer.getCoupon( loggedUsser(session).getId() ,name);
	}
	

}
