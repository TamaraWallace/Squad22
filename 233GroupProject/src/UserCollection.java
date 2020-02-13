import java.util.ArrayList;

public class UserCollection {
	private ArrayList<User> users;
	
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
	
	public void addUser(User u) {
		users.add(u);
	}
	
	public ArrayList<User> getUsers() {
		ArrayList<User> temp = new ArrayList<User>();
		for (User u: users) {
			temp.add(new User(u));	
		}
		return temp;
	}
	
	public boolean isEmpty() {
		return users.isEmpty();
	}
	
	public User findUser(String s) {
		// TODO
		return null;
	}
	
	public void display() {
		for (User u: users) {
			System.out.println(u.toString());
		}
	}
}