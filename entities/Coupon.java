package coupons.coupon.system.entities;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Coupon {
	
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true , nullable = false)
	private String title;
	@Column(nullable = false)
	private String message;
	@Column(nullable = true)
	private String image;
	@Column(nullable = false)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date startDate,endDate;
	@Column(nullable = false)
	private int amount;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CouponType type;
	@Column(nullable = false)
	private double price;
	@ManyToOne 
	private Company company;
	@JsonIgnore
	@ManyToMany(mappedBy = "coupons", cascade= CascadeType.PERSIST, fetch = FetchType.LAZY )
	private Collection<Customer> customers = new ArrayList<Customer>();

	public Coupon(String title, Date startDate , Date endDate, int amount, CouponType type, String message, double price,
			String image) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate =  endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}
	
	public Coupon() {
	
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String massege) {
		this.message = massege;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = (Date) startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = (Date) endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public CouponType getType() {
		return type;
	}
	public void setType(CouponType type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getId() {
		return id;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Coupon title: " + title + ", message: " + message + ", startDate: "
				+ startDate + ", endDate: " + endDate + ", amount: " + amount + ", type: " + type + ", price: " + price + "\n";
	}
	
	
	

}
