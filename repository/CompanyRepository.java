package coupons.coupon.system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import coupons.coupon.system.entities.Company;



@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	 Company findByCompName( String compName );
	
	
	
	 
	
		 
	 

}
