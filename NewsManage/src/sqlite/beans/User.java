package sqlite.beans;

public class User {
	private String userName;
	private String password;
	
	public User(String userName, String password) {
		// TODO Auto-generated constructor stub
		this.userName=userName;
		this.password=password;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
