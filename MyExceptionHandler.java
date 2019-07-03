package coupons.coupon.system.exceptions;



import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import coupons.coupon.system.entities.ApiError;

@ControllerAdvice
public class MyExceptionHandler {

	/**
	 * This exception handles all the exceptions that catches when the incorrect value exception that was thrown.
	 * @param e exception
	 * @return response entity
	 */
	@ExceptionHandler( InCorrectValueExeption.class )
	public ResponseEntity<Object> handleInCorrectValueExeption( InCorrectValueExeption e ){
	System.out.println(e.getMessage());
	ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	return new ResponseEntity<Object>( apiError,HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	/**
	 * This exception handles all the exceptions that catches when the NotFound exception that was thrown.
	 * @param e exception
	 * @return response entity
	 */
	@ExceptionHandler( NotFoundException.class )
	public ResponseEntity<Object> handleNotFoundException( NotFoundException e ){
	ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	return new ResponseEntity<Object>( apiError,HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	/**
	 * This exception handles all the exceptions that catches when the UniqueValue exception that was thrown.
	 * @param e exception
	 * @return response entity
	 */
	@ExceptionHandler ( UniqueValueException.class )
	public ResponseEntity<Object> handleUniqueValueException( UniqueValueException e ){
	ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	return new ResponseEntity<Object>( apiError,HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	/**
	 * This exception handles all the exceptions that catches when the Coupon exception that was thrown.
	 * @param e exception
	 * @return response entity
	 */
	@ExceptionHandler( CouponException.class )
	public ResponseEntity<Object> handleCouponException( CouponException e ){
	ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	return new ResponseEntity<Object>( apiError,HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	  /**
	   * This exception handles all the exceptions that catches when the Throwable exception.
	   * @param e exception
	   * @return response entity
	   */
	  @ExceptionHandler( Throwable.class ) 
	  public ResponseEntity<Object> handleThrowable( Throwable e ){
	  ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	  return new ResponseEntity<Object>( apiError,HttpStatus.INTERNAL_SERVER_ERROR ); }
	 
	  /**
	   * This exception handles all the exceptions that catches when the NoHandlerFound exception.
	   * @param e exception
	   * @return response entity
	   */
	  @ExceptionHandler( NoHandlerFoundException.class )
	  public ResponseEntity<Object> handleResorceNotFound( NoHandlerFoundException e ){
	  ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Could not found " +e.getRequestURL() +"( + e.getHttpMethod() "+ " )");
	  return new ResponseEntity<Object>( apiError,HttpStatus.NOT_FOUND ); 
	  }
	  
	 /**
	  * This exception handles all the exceptions that catches when the Hibernate exception.
	  * @param e exception
	  * @return response entity
	  */
	  @ExceptionHandler( HibernateException.class ) public
	  ResponseEntity<Object> HibernateException(HibernateException e ){
	  ApiError apiError = new
	  ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
	  return new ResponseEntity<Object>( apiError,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	 
	  /**
	   * This exception handles all the exceptions that catches when the NotConnectedError exception.
	   * @param e exception
	   * @return response entity
	   */
	  @ExceptionHandler( NotConnectedError.class ) 
	  public ResponseEntity<Object> notConnectedError( NotConnectedError e ){ 
	  ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	  return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR );
	  }
	  
	  /**
	   * This exception handles all the exceptions that catches when the NullPointer exception.
	   * @param e exception
	   * @return response entity
	   */
	  @ExceptionHandler( NullPointerException.class ) 
	  public ResponseEntity<Object> NullPointerException( NullPointerException e ){ 
	  ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	  return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR );
		  }
	 
	  /**
	   * This exception handles all the exceptions that catches when the MissingValueExeption exception.
	   * @param e exception
	   * @return response entitiy
	   */
	  @ExceptionHandler( MissingValueExeption.class ) 
	  public ResponseEntity<Object> MissingValueExeption( MissingValueExeption e ){ 
	  ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	  return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR );
		  }
	  
	 
	
}
