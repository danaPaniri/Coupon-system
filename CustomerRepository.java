package coupons.coupon.system.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
    Customer findByCustName( String name );
	
	@Query("select c.coupons from Customer c where c.customerId= ?1")
	public List<Coupon> getCustomersCoupons( long customerId );

	@Query("select c from Coupon c JOIN c.customers cust Where cust.customerId =:customerId And c.id =:id")
	public Coupon findCouponByCustomerId(@Param("customerId") long customerId, @Param("id") long id);
	
	@Query("select c from Coupon c JOIN c.customers cust Where c.type =:type And cust.customerId =:customerId")
	public List<Coupon> getCouponByType(@Param("customerId") long customerId, @Param("type")CouponType type);
	
	
	@Query("select c from Coupon c JOIN c.customers cust Where cust.customerId =:customerId AND c.price <=:price")
	public List<Coupon> getCustomerCouponByPrice(@Param("price")double price ,@Param("customerId") long customerId);
	
	 @Query("select c from Coupon c JOIN c.customers cust where cust.customerId =:customerId AND c.title=:title")
	 public Coupon getCustomerCoupon( long customerId, String title );
	


	 
	    
}
