package coupons.coupon.system.webServices;

import javax.servlet.annotation.WebFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;

//import coupons.coupon.system.entities.LoggedUser;
import coupons.coupon.system.exceptions.NotConnectedError;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@WebFilter("/REST/*")
public class LoginFilterService implements Filter {
	 
	 
@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {

	 System.err.println("Login o.k");
	 		
	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	HttpSession session = httpServletRequest.getSession(false);
	System.err.println(session + " loginfilter");
		if(session == null) {
			try {
			throw new NotConnectedError("you are not loggin");
			} catch (Exception e) {
				e.getMessage();
				}
			}
	
			System.out.println("HEY");
			chain.doFilter(request, response);
			//LoggedUser user =(LoggedUser)session.getAttribute("User");
		}

		 
	}



