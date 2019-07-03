package coupons.coupon.system.services;

import java.util.List;

import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;

public interface TempUserService {
	
	public List<Coupon> getCouponsByType(CouponType type);
	public List<Coupon> getCouponsByTitle(String title);
	public Coupon getCoupon(String name);

}
