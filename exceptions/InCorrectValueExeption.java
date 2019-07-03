package coupons.coupon.system.exceptions;

public class InCorrectValueExeption extends Exception {
	
	/**
	 * This exception describe the exceptions that can created when there is an incorrect value.
	 */
	private static final long serialVersionUID = 1L;
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public InCorrectValueExeption(String message, String source) {
		super(message);
		this.source = source;
	}

}
