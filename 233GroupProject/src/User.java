// User class creates User object with usrName, usrPassword and usrID attributes

public class User {
	
	private String usrName; // user name for each user
	private String usrPassword; // users password
	private int usrID; // unique Id for each user
	
	// Constructor Method: sets User's name, password and ID
	// Parameters: 
	//		usrName: string, name of user
	//		usrPassword: string, user's password for login
	//		usrID: unique ID for each user, used to identify user's tasks
	// Returns: Void
	public User(String usrName, String usrPassword, int usrID) {
		
		this.usrName = usrName;
		this.usrPassword = usrPassword;
		this.usrID = usrID;
	}
	
	//Returns User object id and user name as string
	// Parameters: None
	// Returns: String of usrID and yusrName
	public String toString() {
		return Integer.toString(usrID)+","+ usrName;
	}
	
	// Checks whether User's name matches
	// Parameters: 
	//		usrName: string, name of user to be checked
	// Returns: Boolean, whether userName matches	
	public boolean checkName(String usrName){
		return usrName.equals(this.usrName);
	}
	
	// Checks whether User's password matches
	// Parameters: 
	//		usrPassword: string, user's password for login to be checked
	// Returns: Boolean, whether usrPassword matches
	public boolean checkPassword(String usrPassword){
		return usrPassword.equals(this.usrPassword);
	}
	
	// Getter method for usrName
	public String getUsrName() {
		return usrName;
	}
	// Setter method for usrName
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	// Getter method for usrPassword
	public String getUsrPassword() {
		return usrPassword;
	}
	// Setter method for usrPassword
	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}
	// Getter method for usrID
	public int getUsrID() {
		return usrID;
	}
	// Setter method for usrID
	public void setUsrID(int usrID) {
		this.usrID = usrID;
	}
	


}