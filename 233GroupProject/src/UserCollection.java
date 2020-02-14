import java.util.ArrayList;

public class UserCollection {
	private ArrayList<User> users;
	
	
//	Method name: UserCollection()
//	Parameters: none
//	Return: none
//	Functionality: class constructor 
//	
	public UserCollection() {
		users = new ArrayList<User>();
	}
	
	public static UserCollection loadUsers(String fname) {
		// TODO
		return new UserCollection();
	}
	
	public static void saveUsers(String fname) {
		// TODO
	}
	
	
//	Method name: addUser
//	Parameters: User u
//	Return: void
//	Functionality: adds a user to the users arraylist 
//	
	public void addUser(User u) {
		users.add(u);
	}
	
	
//	Method name: getUsers
//	Parameters: none
//	Return: ArrayList <user>
//	Functionality: getter method for the arraylist users
//	
	
	public ArrayList<User> getUsers() {
		ArrayList<User> temp = new ArrayList<User>();
		for (User u: users) {
			temp.add(new User(u));	
		}
		return temp;
	}
	
	
	
//	Method name: isEmpty
//	Parameters: none
//	Return: boolean
//	Functionality: checks if the users arraylist is empty or not
//	
	public boolean isEmpty() {
		return users.isEmpty();
	}
	
	// 	
//	Method name: findUser 
//	Parameters: String name
//	Return: User 
//	Functionality: checks if a user's username already exists in the users ArrayList, if it does then return that user. Otherwise return null
//	
	public User findUser(String name) {
	
		if (!users.isEmpty()) {
			for (User user: users) {
				String username = user.getUsrName();
				if (username.contentEquals(name)){
					return user;
				}
			}
			return null;
		}
		else {
			return null;
		}
	}
	
//	Method name: Display
//	Parameters: none
//	Return: void 
//	Functionality: prints each user from the users arraylist, does this by calling toString method on each user in the print function
//	
	public void display() {
		for (User u: users) {
			System.out.println(u.toString());
		}
	}
}