package coupons.coupon.system.exceptions;

public class MissingValueExeption extends Exception {
	
	/**
	 * This exception describe the exceptions that can created when there is a missing value.
	 */
	private static final long serialVersionUID = 1L;
	public MissingValueExeption(String message) {
		super(message);
		
	}

}
