package coupons.coupon.system.entities;

public class LoggedUser {

	private long id;
	private ClientType clientType;
	private String name;
	private String password;
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	
	  public String getName(){
		  return name;
		  }
	 
	  public void setName(String name) {
		  this.name = name; 
		  }
	 

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	
	
}
