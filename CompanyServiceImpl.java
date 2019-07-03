package coupons.coupon.system.servicesImpl;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import coupons.coupon.system.entities.ClientType;
import coupons.coupon.system.entities.Company;
import coupons.coupon.system.entities.Coupon;
import coupons.coupon.system.entities.CouponType;
import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.CouponException;
import coupons.coupon.system.exceptions.InCorrectValueExeption;
import coupons.coupon.system.exceptions.NotFoundException;
import coupons.coupon.system.exceptions.UniqueValueException;
import coupons.coupon.system.repository.CompanyRepository;
import coupons.coupon.system.repository.CouponRepository;
import coupons.coupon.system.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository compRepository;

	@Autowired
	private CouponRepository coupRepository;

	LoggedUser lu = new LoggedUser();

	/**
	 * create a new coupon and adding the coupon to the coupon table and the
	 * company_coupon table in the data base.
	 * @throws UniqueValueException when the value is unique and the user give the same value
	 * @throws CouponException error with the coupon
	 * @throws NotFoundException when value was not found
	 */
	@Override
	public void creatCoupon(Coupon coupon, long companyId)
			throws UniqueValueException, CouponException, NotFoundException {

		Company company = compRepository.findById(companyId).get();
		if (company == null) {
			throw new NotFoundException("No company found with this id: " + companyId);
		}
		if(coupon.getEndDate().before(coupon.getStartDate())) {
			throw new CouponException("start date must be before end date!", null);
		}if(coupon.getTitle() == null || coupon.getTitle().isEmpty()) {
			throw new CouponException("Coupon name must exsist!", null);
		} else {
			try {
				coupon.setCompany(company);
				coupRepository.save(coupon);
			} catch (Exception ex) {
				throw new UniqueValueException("This coupon name is already exsist!!!", coupon.getTitle());
			}
		}
	}

	/**
	 * remove coupon from the company coupon table, customer coupon table and coupon
	 * table in the data base..
	 * 
	 */
	@Override
	public void removeCoupon(String name, long companyId)  {
		coupRepository.deleteBycouponName(name, companyId);
	}

	/**
	 * This method return ArrayList of a company's coupon...
	 * 
	 */
	@Override
	public List<Coupon> getCompanyCoupons(long companyId) {

		return coupRepository.getAllCompanyCoupons(companyId);
		
	}

	/**
	 * This method return company details...
	 */
	public Company findCompany(String name) {
		return compRepository.findByCompName(name);
	}

	/**
	 * log in with name ,password and client type.
	 * 
	 * @throws InCorrectValueExeption when the value is in correct
	 */
	@Override
	public LoggedUser login(String name, String password, ClientType type) throws InCorrectValueExeption {
		Company company = null;
		if (name == null || name.isEmpty()) {
			throw new InCorrectValueExeption("Company name must Exsist!!!", name);
		}
		company = compRepository.findByCompName(name.trim());
		System.out.println(company);
		if (company == null) {
			throw new InCorrectValueExeption("This company name is not exsist: " + name, name);
		} else {

			if (type.equals(ClientType.COMPANY)) {
				if (password.trim().equals(company.getPassword().trim())) {
					lu.setId(company.getId());
					lu.setName(company.getCompName());
					lu.setPassword(company.getPassword());
					lu.setClientType(ClientType.COMPANY);
					System.out.println("COMPANY O.K");
					return lu;
				}
				throw new InCorrectValueExeption("The password incorrect!!!", password);
			}
			throw new InCorrectValueExeption("The Client type incorrect!!!", null);
		}
	}

	/**
	 * This method update coupon's price and coupon's end date.
	 */
	@Override
	public void updateCoupon(Coupon coupon, long companyId) {
		System.out.println("Update coupon: " + coupon.getTitle());
		Coupon c = getCoupon(coupon.getTitle(), companyId);
		System.out.println("Coupon: " + c);
		if (c != null) {
			c.setEndDate(coupon.getEndDate());
			c.setPrice(coupon.getPrice());
			coupRepository.save(c);
		}
	}

	/**
	 * This method return coupon details by id...
	 */
	@Override
	public Coupon getCoupon(String name, long companyId) {
		return coupRepository.getCompanyCoupon(companyId, name);
	}

	/**
	 * This method returning ArrayList of all the coupons with the same type
	 */
	@Override
	public ArrayList<Coupon> getCouponByType(CouponType type, long companyId) {

		return coupRepository.getCouponByType(type, companyId);

	}

	/**
	 * This method returning ArrayList of all the coupons up to price. throws
	 * NotFoundException
	 */
	@Override
	public ArrayList<Coupon> getCouponByPrice(double price, long companyId) {

		return coupRepository.getCouponByPrice(price, companyId);
	}

	/**
	 * This method returning ArrayList of all the coupons up to specific date.
	 * 
	 */
	@Override
	public ArrayList<Coupon> getCouponByDate(Date endDate, long companyId){

		return coupRepository.getCouponByDate(endDate, companyId);
	}

	/**
	 * This method return company details..
	 */
	@Override
	public Company getCompany(long id) {

		return compRepository.findById(id).get();
	}

}
