package coupons.coupon.system.exceptions;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	
    /**
     * This exception describe the exceptions that can created when the requesting parameters was'nt found.
     * @param message message about the exception
     */
	public NotFoundException(String message) {
		super(message);
	}

}
