package coupons.coupon.system.services;


import java.util.Collection;
import java.util.List;

import coupons.coupon.system.entities.ClientType;
import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.entities.Customer;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.CouponException;
import coupons.coupon.system.exceptions.InCorrectValueExeption;
import coupons.coupon.system.exceptions.UniqueValueException;


public interface CustomerService {
	
	
	public Collection<Coupon> getAllCustomerCoupons(long customerId);
	public Coupon getCoupon( long customerId, String name);
	public Customer getCustomer(String name);
	public List<Coupon> getCustomerCouponByType(CouponType typ, long customerId);
	public List<Coupon> getCustomerCouponByPrice(Double price, long customerId);
	public void purchaseCoupon(long id, long customerId) throws UniqueValueException, CouponException;
	public LoggedUser login(String name, String password, ClientType type) throws InCorrectValueExeption;
	
}
