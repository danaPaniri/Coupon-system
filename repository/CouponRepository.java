package coupons.coupon.system.repository;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
	 public List<Coupon> findByCompany_id(long id); 
	 
	 
	 @Query("select c from Coupon c JOIN c.company com where com.id = ?1")
	 public List<Coupon> getAllCompanyCoupons( long compId );
	 
	 @Query("select c from Coupon c JOIN c.company com where com.id = ?1 AND c.title=?2")
	 public Coupon getCompanyCoupon( long compId, String name );
	 
	 @Modifying
	 @Transactional
	 @Query("DELETE from Coupon c where company_id = ?1")
	 public void removeCompanyCoupons(long company_id);
	 
	 @Query("select c from Coupon c JOIN c.company com where c.type = ?1 and com.id = ?2")
	 public ArrayList<Coupon> getCouponByType(CouponType type, long id);

	 @Query("select c from Coupon c JOIN c.company com where c.price <= ?1 and com.id = ?2")
	 public ArrayList<Coupon> getCouponByPrice(double price, long id);
	 
	 @Query("select c from Coupon c JOIN c.company com where c.endDate <= ?1 and com.id = ?2")
	 public ArrayList<Coupon> getCouponByDate(Date endDate, long id);
	 
	 @Modifying
	 @Query(value = "insert into Coupon (customer_coupons) VALUES =(customer_customer_id:customerId) WHERE id =:id", nativeQuery = true)
	 @Transactional
	 public void addCustomer ( long id, long customerId );
	 
	 @Modifying
	 @Query(value = "insert into Coupon VALUES =(companyId:id) WHERE id =:coupId", nativeQuery = true)
	 @Transactional
	 public void addCompanyIdToCoupon(long coupId, long id);
	 
	 public Coupon findByTitle(String title);
	 
	 @Modifying 
	 @Transactional
	 @Query( value = "DELETE from Coupon WHERE end_Date < GETDATE()", nativeQuery = true )
	 public void removeExpieredCoupons();
	
	 @Modifying 
	 @Transactional
	 @Query("delete from Coupon c where c.title = ?1 and c.company.id=?2")
	public void deleteBycouponName(String name,long compnayId);

	 @Query("select c from Coupon c where c.type = ?1")
	 public ArrayList<Coupon>getCouponsByType(CouponType type);
		 
	 @Query("select c from Coupon c where c.title like %?1%")
	 public List<Coupon> getCouponsByTitle(String title);
	 
	
	 
}
