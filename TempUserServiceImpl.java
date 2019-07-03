package coupons.coupon.system.servicesImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.repository.CouponRepository;
import coupons.coupon.system.services.TempUserService;

@Service
public class TempUserServiceImpl implements TempUserService {
	
	/**
	 * This is a guest user which can see all the coupons without logging in, he can only see
	 * the coupon but can not purchase without logging in as a customer.
	 */
	@Autowired
	private CouponRepository coupRepository;
	
	
	

    /**
     * This method return to the user list of coupons by their name.
     */
	@Override
	public List<Coupon> getCouponsByType(CouponType type) {
		
		return coupRepository.getCouponsByType(type);
	}


	/**
	 * This method return to the user list of coupons by their type.
	 */
	@Override
	public List<Coupon> getCouponsByTitle(String title) {
		
		return coupRepository.getCouponsByTitle(title);
	}


	/**
	 * This method return to the user one coupon by his name.
	 */
	@Override
	public Coupon getCoupon(String name) {
		
		return coupRepository.findByTitle(name);
	}


}
