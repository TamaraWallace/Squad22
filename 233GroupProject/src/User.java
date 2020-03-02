import java.util.UUID;

// User class creates User object with usrName, usrPassword and usrID attributes

public class User {

	private static UUID usrID; // unique Id for each user
	private String usrName; // user name for each user
	private String usrPassword; // users password
	private String usrEmail; // user's email
	
	
	public User() {	}
	
	// Constructor Method: sets User's name, password and ID
	// Parameters: 
	//		usrName: string, name of user
	//		usrPassword: string, user's password for login
	//		usrID: unique ID for each user, used to identify user's tasks
	// Returns: Void
	public User(String newUsrName, String newUsrPassword, String newUsrEmail) {
		this.usrName = newUsrName;
		this.usrPassword = newUsrPassword;
		this.usrEmail = newUsrEmail;
		this.usrID = UUID.randomUUID();
	}
	
	public User(User u) {
		usrName = u.usrName;
		usrPassword = u.usrPassword;
		usrEmail = u.usrEmail;
		usrID = u.usrID;
	}

	//Returns User object id and user name as string
	// Parameters: None
	// Returns: String of usrID and yusrName
	public String toString() {
		return "\tUsername: " + usrName +
				"\n\tPassword: " + usrPassword + 
				"\n\tEmail: " + usrEmail +
				"\n\tUserID " + usrID;
	}
	
	//
	public String toSaveString() {
		return usrID.toString()+ ","
				+ usrName + ","
				+ usrPassword + ","
				+ usrEmail + "\n";
	}
	
	public static User fromString(String s) {
		User temp = new User();
		String[] vals = s.split(",");
		temp.usrID = UUID.fromString(vals[0]);
		temp.usrName = vals[1];
		temp.usrPassword = vals[2];
		temp.usrEmail = vals[3];
		return temp;
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
	public static UUID  getUsrID() {
		return usrID;
	}
}