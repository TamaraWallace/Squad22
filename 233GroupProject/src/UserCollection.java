import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

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
	
//	Method name: loadUsers
//	Parameters: String fname, the file name to load users from
//	Return: a new User Collection to access user data
//	Functionality: reads user information from file and stores information in user collection object
	public static UserCollection loadUsers(String fname) {
		UserCollection allUsers = new UserCollection();
		// based on method 3 from this website:https://examples.javacodegeeks.com/core-java/java-8-read-file-line-line-example/ is used for basic file reading
				try {
		            BufferedReader br = new BufferedReader(new FileReader(fname));
		            Stream <String> lines = br.lines();
		            String[] userList = lines.toArray(String[] :: new);
		            lines.close();
		            br.close();
		            for(String user : userList) {
		            	String[] splitUser = user.split(",");
	            		User next = new User(splitUser[1].trim(), splitUser[2].trim());
	            		allUsers.users.add(next);
		            }
		        } catch (IOException io) {
		            io.printStackTrace();
		        }
		return allUsers;
	}
	
	// Method Name: saveUsers
	// Parameters: fname (the name of the file to save users to)
	// Return: void
	// Functionality: save all the users the file, including new users and updates
	// NOTE: needed to make not static
	// NOTE 2: since we're loading up all the users anyways, I just decided to resave them all
	public void saveUsers(String fname) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
            Stream <String> lines = br.lines();
            String[] userList = lines.toArray(String[] :: new);
            lines.close();
            br.close();
            FileWriter bw = new FileWriter(fname);
            int lastKey = userList.length - 1;
            ArrayList<String> uList = new ArrayList<String>();
            for(String s: userList) {
            	uList.add(s+"\n");
			}
            
            for(User u : this.users) {
            	if (lastKey<u.getUsrID()) {
            		uList.add(String.valueOf(u.getUsrID())
            				+", "+ u.getUsrName()+", "+ u.getUsrPassword()+"\n");
            	}
            }
            
            for(String str : uList) {
            	bw.write(str);
            }
            
            bw.close();
		} catch (IOException io) {
			
		}
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
					User userCopy = new User(user);
					return userCopy;
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