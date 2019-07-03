package coupons.coupon.system.webServices;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.servicesImpl.TempUserServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("guest")
public class TempUserWebService {
	
	/**
	 * This is a guest user which can see all the coupons without logging in, he can only see
	 * the coupon but can not purchase without logging in as a customer.
	 */
	@Autowired
	private TempUserServiceImpl tempUser;
	
	/**
	 * This method return to the user list of coupons by their name.
	 * this method is allowed for everyone, without logging in. 
	 * @param name name of coupon
	 * @return coupons by name
	 */
	@RequestMapping( path = "getcouponByName", method=RequestMethod.GET)
	public ArrayList<Coupon> getCouponByTitle(String name) {
		return (ArrayList<Coupon>) tempUser.getCouponsByTitle(name);
	}
	
	/**
	 * This method return to the user list of coupons by their type.
	 * this method is allowed for everyone, without logging in. 
	 * @param type of coupon
	 * @return coupons by type
	 */
	@RequestMapping( path = "getcouponByType", method=RequestMethod.GET)
	public ArrayList<Coupon> getCouponByType(CouponType type) {
		return (ArrayList<Coupon>) tempUser.getCouponsByType(type);
	}
	
	/**
	 * This method return to the user one coupon by his name.
	 * this method is allowed for everyone, without logging in. 
	 * @param name coupon name
	 * @return coupon
	 */
	@RequestMapping( path = "getcoupon", method=RequestMethod.GET)
	public Coupon getCoupon(String name) {
		return tempUser.getCoupon(name);
	}
	
	

	
}
