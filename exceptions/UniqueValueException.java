package coupons.coupon.system.exceptions;

public class UniqueValueException extends Exception {
	
  /** 
   * This exception describe the exceptions that can created when there is a duplicate value. 
   */
	private static final long serialVersionUID = 1L;
private String errorValue;
private long errorVal;
	
	public UniqueValueException(String message, String errorValue) {
		super(message);
		this.setErrorValue(errorValue);
	}
	public UniqueValueException(String message, long errorValue) {
		super(message);
		this.setErrorVal(errorValue);
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
	public void setErrorVal(long errorVal) {
		this.errorVal = errorVal;
	}


}
