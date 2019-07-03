package coupons.coupon.system.webServices;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coupons.coupon.system.entities.Company;
import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.CouponException;
import coupons.coupon.system.exceptions.NotFoundException;
import coupons.coupon.system.exceptions.UniqueValueException;
import coupons.coupon.system.servicesImpl.CompanyServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("REST/company")
public class CompanyWebService {
	
	@Autowired
	private CompanyServiceImpl company;
	/**
	 * getting the user details
	 * @param session user id
	 * @return user details
	 */
	private LoggedUser loggedUsser(HttpSession session) {
		return (LoggedUser) session.getAttribute("LoggedUser");
	}
	/**
	 * create a new coupon by getting the parameters from the user and adding the coupon to the coupon table and the
	 * company_coupon table in the data base.
	 * @param coupon coupon details
	 * @param session (user id)
	 * @throws UniqueValueException when the value is unique and the user give the same value
	 * @throws CouponException error with the coupon
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "createCoupon", method=RequestMethod.POST)
    public void creatCoupon(@RequestBody Coupon coupon, HttpSession session ) throws UniqueValueException, CouponException, NotFoundException{
		company.creatCoupon(coupon, loggedUsser(session).getId());
	}
	/**
	 * This method update coupon's price and coupon's end date by getting the
	 * parameters from the user.
	 * @param coupon coupon details
	 * @param session (user id)
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "updateCoupon", method=RequestMethod.PUT)
	public void updateCoupon(@RequestBody Coupon coupon, HttpSession session ) throws NotFoundException{
		company.updateCoupon(coupon, loggedUsser(session).getId());
	}
	
	/**
	 * This method return to the user specific coupon details..
	 * @param name coupon name
	 * @param session (user id)
	 * @return coupon details
	 */
	@RequestMapping( path = "getCoupon", method=RequestMethod.GET)
	public Coupon getCoupon(@RequestParam String name, HttpSession session) {
		return company.getCoupon(name, loggedUsser(session).getId());
	}
	
	/**
	 * remove coupon from the company coupon table, customer coupon table and coupon
	 * table in the data base..
	 * @param name coupon name
	 * @param session user id
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "removeCoupon", method=RequestMethod.DELETE)
	public void removeCoupon(@RequestParam String name , HttpSession session) throws NotFoundException{
		company.removeCoupon(name, loggedUsser(session).getId());
	}
	
	/**
	 * This method return ArrayList of a company's coupon...
	 * @param session (use id)
	 * @return company's coupons
	 */
	@RequestMapping( path = "getAllCoupons", method=RequestMethod.GET)
	public List<Coupon> getCompanyCoupons( HttpSession session){
		return company.getCompanyCoupons( loggedUsser(session).getId() );
	
	}
	
	/**
	 *  This method returning ArrayList of all the coupons with the same type.
	 * @param type coupon type
	 * @param session (user id)
	 * @return coupons of the same type that the user choose.
	 */
	@RequestMapping( path = "getCouponByType", method=RequestMethod.GET)
	public ArrayList<Coupon> getCouponByType(@RequestParam CouponType type , HttpSession session) {
		return company.getCouponByType(type, loggedUsser(session).getId());
	}
	
	/**
	 * This method returning ArrayList of all the coupons up to price.
	 * @param price coupon price
	 * @param session (user id)
	 * @return coupons up to price that the user choose.
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "getCouponByPrice", method=RequestMethod.GET)
	public ArrayList<Coupon> getCouponByPrice(@RequestParam double price , HttpSession session) throws NotFoundException{
		return company.getCouponByPrice(price, loggedUsser(session).getId());
	}
	
	/**
	 * This method returning ArrayList of all the coupons up to specific date.
	 * @param endDate expiration date of the coupon 
	 * @param session (user id)
	 * @return coupons up to specific date that the user choose.
	 * @throws NotFoundException when value was not found
	 */
	@RequestMapping( path = "getCouponByDate", method=RequestMethod.GET)
	public ArrayList<Coupon> getCouponByDate(@RequestParam(name = "endDate") @DateTimeFormat(pattern="yyyy-mm-dd") Date endDate , HttpSession session) throws NotFoundException{
		return company.getCouponByDate(endDate, loggedUsser(session).getId());
	}

	/**
	 * This method return company details..
	 * @param session (user id)
	 * @return company details
	 */
	@RequestMapping( path = "getCompany", method=RequestMethod.GET)
	public Company getCompany(  HttpSession session ) {
		return company.getCompany(loggedUsser(session).getId());
	}
}
