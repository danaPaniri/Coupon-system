package coupons.coupon.system.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private long customerId;
	@Column( unique = true , nullable = false )
	private String custName;
	@Column( nullable = false )
	private String password,email;
	@JsonIgnore
	@ManyToMany( cascade= CascadeType.PERSIST, fetch = FetchType.LAZY )
	private Collection<Coupon> coupons = new ArrayList<Coupon>();
	
	public Customer( String custName, String password, String email ) {
		super();
		this.custName = custName;
		this.password = password;
		this.email = email;
		
	}
	
	public Customer() {
		
	}

	public long getCustomerId() {
		return customerId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Coupon> getCupons() {
		return coupons;
	}

	public void setCupons(ArrayList<Coupon> cupons) {
		this.coupons = cupons;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "custName:" + custName + ", password:" + password + ", email:" + email + "\n";
	}
	

}
