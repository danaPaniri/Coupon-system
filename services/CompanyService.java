package coupons.coupon.system.services;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import coupons.coupon.system.entities.ClientType;
import coupons.coupon.system.entities.Company;
import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.CouponException;
import coupons.coupon.system.exceptions.InCorrectValueExeption;
import coupons.coupon.system.exceptions.NotFoundException;
import coupons.coupon.system.exceptions.UniqueValueException;



public interface CompanyService {
	
	
	public Company findCompany(String name);
	public Company getCompany(long id);
	public void updateCoupon( Coupon coupon, long companyId ) throws NotFoundException;
	public Coupon getCoupon(String name, long companyId);
	public LoggedUser login(String name, String password, ClientType type) throws InCorrectValueExeption;
	public void removeCoupon(String name, long companyId);
	public List<Coupon> getCompanyCoupons(long companyId);
	public void creatCoupon(Coupon coupon, long companyId) throws UniqueValueException, CouponException, NotFoundException;
	public ArrayList<Coupon> getCouponByType(CouponType type, long companyId);
	public ArrayList<Coupon> getCouponByPrice(double price, long companyId);
	public ArrayList<Coupon> getCouponByDate(Date endDate, long companyId);
	
	
	
	
	
	
	
	

}
