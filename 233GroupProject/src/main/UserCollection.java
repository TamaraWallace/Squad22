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
	            		User next = new User(userSaveString);
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
	public void saveUsers(String fname) {
		System.out.println("Saving users!");
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
	

//	Method name: addUser
//	Parameters: User u
//	Return: void
//	Functionality: adds a user to the users arraylist 
	public void addUser(User u) {
		users.add(u);
	}
	
	
//	Method name: getUsers
//	Parameters: none
//	Return: ArrayList <user>
//	Functionality: getter method for the arraylist users
	public ArrayList<User> getUsers() {
		return users;
	}
	
//	Method name: isEmpty
//	Parameters: none
//	Return: boolean
//	Functionality: checks if the users arraylist is empty or not
	public boolean isEmpty() {
		return users.isEmpty();
	}

//	Method name: findUser 
//	Parameters: String name
//	Return: User 
//	Functionality: checks if a user's username already exists in the users ArrayList, if it does then return that user. Otherwise return null
//	
	public boolean doesUsernameExist(String name) {
		if (users.isEmpty()) return false;
		else {
			for (User user: users) {
				if (user.checkName(name)) return true;
			}
		}
		return false;
	}
	
	
	public boolean validateUsernameAndPassword(String name, String pword) {
		for (User u: users) {
			if (u.checkName(name) && u.checkPassword(pword)) return true;
		}
		return false;
	}
	
//	Method name: Display
//	Parameters: none
//	Return: void 
//	Functionality: prints each user from the users ArrayList, does this by calling toString method on each user in the print function
	public void display() {
		for (User u: users) {
			System.out.println(u.toString());
		}
	}
	
	public User getUser(UUID userID) {
		for (User user : users) {
			if (userID == user.getUsrID()) {
				return user;
			}
		}
		return null;
	}
	
	public User getUserByName(String name) {
		for (User user: users) {
			if(name.compareToIgnoreCase(user.getUsrName()) == 0) {
				return user;
			}
		}
		return null;
	}
}