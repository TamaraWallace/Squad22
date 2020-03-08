package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
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
		File f = new File(fname);
		if(!(f.exists())) {
			try{
				f.createNewFile();
			} catch (IOException io){}
		} else {
			// based on method 3 from this website:https://examples.javacodegeeks.com/core-java/java-8-read-file-line-line-example/ is used for basic file reading
				try {
		            BufferedReader br = new BufferedReader(new FileReader(fname));
		            Stream <String> lines = br.lines();
		            String[] userList = lines.toArray(String[] :: new);
		            lines.close();
		            br.close();
		            for(String userSaveString : userList) {
	            		User next = User.fromString(userSaveString);
	            		allUsers.users.add(next);
		            }
		        } catch (IOException io) {}
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
		File f = new File(fname);
		if(!(f.exists())) {
			try{
				f.createNewFile();
			} catch (IOException io){}
		} 
		try {
	        FileWriter bw = new FileWriter(fname);
	        /* This section of code is useful when:
	         * 		- Not all users are stored in memory
	         * 
	         * Because we are currently loading ALL users, and keeping ALL users during runtime
	         * only the last for loop is needed to save properly
	         * 
	         * Please do not remove this code.
	         * 
			BufferedReader br = new BufferedReader(new FileReader(fname));
			Stream <String> lines = br.lines();
	        String[] userList = lines.toArray(String[] :: new);
	        lines.close();
	        br.close();
	        
	        for (String s: userList) {
	        	UUID tempID = UUID.fromString(s.split(",")[0]);
	        	User temp = getUserByID(tempID);
	        	if (temp == null) {
	        		bw.write(s);
	        	} else {
	        		bw.write(temp.toSaveString());
	        		users.remove(temp);
	        	}
	        } */
	        for(User u: this.users) {
	        	bw.write(u.toSaveString());
	        }
	        bw.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	/* Unused...
	 * 
	private User getUserByID(UUID uuid) {
		User temp = null;
		for (User u: users) {
			if (u.getUsrID().compareTo(uuid) == 0) temp = u;
		}
		return temp;
	} */

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
		name = name.toLowerCase();
		if (!users.isEmpty()) {
			for (User user: users) {
				String username = user.getUsrName().toLowerCase();
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
//	Functionality: prints each user from the users ArrayList, does this by calling toString method on each user in the print function
//	
	public void display() {
		for (User u: users) {
			System.out.println(u.toString());
		}
	}
	
	public User getUser(UUID userID) {
		for (User user : users) {
			if (user.getUsrID().compareTo(userID) == 0) {
				return user;
			}
			else {
				return null;
			}
		}
		return null;
	}
}