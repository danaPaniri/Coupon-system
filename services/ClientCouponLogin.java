package coupons.coupon.system.services;

import coupons.coupon.system.entities.ClientType;

public interface ClientCouponLogin {
	
	public ClientCouponLogin logIn(String name, String password, ClientType type);
}
