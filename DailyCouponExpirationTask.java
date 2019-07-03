package coupons.coupon.system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import coupons.coupon.system.repository.CouponRepository;

@Service
public class DailyCouponExpirationTask {

	@Autowired
	private CouponRepository coupRepository;
		
	
	public DailyCouponExpirationTask() {
		
	}

	/**
	 * 
	 */
	@Scheduled(cron = "0 0 12 1/1 * ?")
	//@Scheduled(cron = "0 0/1 * 1/1 * ? ")
	public void run() {
		System.out.println("start");
		
			try {
				coupRepository.removeExpieredCoupons();
			} catch (Exception e1) {
		}
			  }
	}

	


