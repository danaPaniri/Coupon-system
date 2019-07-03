package coupons.coupon.system.webServices;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.InCorrectValueExeption;
import coupons.coupon.system.servicesImpl.AdminServiceImpl;
import coupons.coupon.system.servicesImpl.CompanyServiceImpl;
import coupons.coupon.system.servicesImpl.CustomerServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("LoginUser")
public class LoginServlete extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AdminServiceImpl admin;
	@Autowired
	private CompanyServiceImpl company;
	@Autowired
	private CustomerServiceImpl customer;
	
	private LoggedUser lu = null;
	
	public LoginServlete() {
	
	}


	/**
	 * This method perform a log in to the system.
	 * @param loggedUser user
	 * @param request session 
	 * @return response entity
	 * @throws InCorrectValueExeption when the value is in correct
	 */
	@RequestMapping( path = "login", method = RequestMethod.POST )
	public ResponseEntity<?> login(@RequestBody LoggedUser loggedUser, HttpSession request) throws InCorrectValueExeption{
	System.out.println(loggedUser.getName());
            switch (loggedUser.getClientType()) {
			case ADMIN:
					lu = admin.login(loggedUser.getName(), loggedUser.getPassword(), loggedUser.getClientType() );
			if(lu != null) {
				request.setAttribute( "LoggedUser", lu );
				 return new ResponseEntity<String>(  HttpStatus.OK );
				}else
					 throw new InCorrectValueExeption("Name or password incorrect!!!", null);
				
			case COMPANY:
			
					lu = company.login(loggedUser.getName(), loggedUser.getPassword(), loggedUser.getClientType() );
				
				if(lu != null) {
					request.setAttribute( "LoggedUser", lu );
				System.out.println( "COMPANY O.K" );
				return new ResponseEntity<String>( HttpStatus.OK );
				}
				throw new InCorrectValueExeption("Name or password incorrect!!!", null);
			case CUSTOMER:
				lu = customer.login( loggedUser.getName(), loggedUser.getPassword(), loggedUser.getClientType() );
				if( lu != null ) {
					request.setAttribute( "LoggedUser", lu );
				System.err.println( "CUSTOMER O.K" );
				 return new ResponseEntity<String>(  HttpStatus.OK );
				}
				throw new InCorrectValueExeption("Name or password incorrect!!!", null);
			}
					
           return new ResponseEntity<String>( HttpStatus.CONFLICT );
            //return null;
	}
	
	
	@RequestMapping( path = "logOut", method = RequestMethod.POST )
	public ResponseEntity<?> logOut( HttpSession request) throws InCorrectValueExeption{
		if(request != null) {
		request.invalidate();
		lu = null;
		}
		
		 return new ResponseEntity<String>(  HttpStatus.OK );
	}
	
	
    
}
