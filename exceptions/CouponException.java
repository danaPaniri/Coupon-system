package coupons.coupon.system.exceptions;

public class CouponException extends Exception {
	
  /** 
   * This exception describe the exceptions that can created when there is problem
   *  in the coupon.
   */
	private static final long serialVersionUID = 1L;
private String errorValue;
private long errorVal;
	
	public CouponException(String message, String errorValue) {
		super(message);
		this.setErrorValue(errorValue);
	}
	
	

	public String getErrorValue() {
		return errorValue;
	}

	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}
	public long getErrorVal() {
		return errorVal;
	}
	

}
