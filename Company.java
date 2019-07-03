package coupons.coupon.system.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {
	
    @Id
    @GeneratedValue
	private long id;
    @Column(unique = true , nullable = false)
	private String compName;
    @Column(nullable = false)
	private String password , email;
    @JsonIgnore
    @OneToMany ( mappedBy = "company",fetch = FetchType.LAZY, cascade= CascadeType.PERSIST ) 
	private Collection<Coupon> coupons = new ArrayList<Coupon>();
	
	
	public Company() {
	}

	public Company(String compName, String password, String email) {
		super();
		this.compName = compName;
		this.password = password;
		this.email = email;
		
	}

	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}

	
	
	

}
