package coupons.coupon.system.exceptions;

public class MySQLException extends Exception {

	/**
	 * This exception describe the exceptions that can created when there is problem with the sql.
	 */
	private static final long serialVersionUID = 1L;
	private String source;
	
	public MySQLException(String message, String source) {
		super(message);
		this.source = source;
	}

	public String getSource() {
		return source;
	}
	
}
