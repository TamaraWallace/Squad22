import java.util.Scanner;


//need other four classes to merge functionality
public class TextBasedApp {
	
	private static User user;
	private static UserCollection users;
	private static TaskCollection tasks;
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		users = UserCollection.loadUsers("users.csv");
		start();
		tasks = TaskCollection.loadUsrTasks("tasks.csv", user.getUsrID());
		
		// a method for programmer tests
		// feel free to move this wherever you want
		test();
		
		mainMenu();
	}
	
	public static void test() {
		users.display();
		tasks.display();
	}
	
	//Method Purpose: start the program, prompt "Welcome!", display options to login
	//Parameters: 
	//Return Value:
	private static void start() {
		System.out.println("Welcome!");
		System.out.println("Are you an existing user: ");
		String answer = keyboard.nextLine();
	
		if (answer.toLowerCase().compareTo("yes")==0
				&& !users.isEmpty()) {
			user = login();
		} else {
			user = createNewUser();
		}
	}
	
	//Method Purpose: to use the User and UserCollection classes to add another user's info to file
	//Parameters:
	//Return Value: user's info
	private static User createNewUser() {
		//Question: how do we create a user ID #?
		System.out.print("Please enter your name: ");
		String name = keyboard.nextLine();
		System.out.print("Please enter a password: ");
		String password = keyboard.nextLine();
		
		User newUser = new User(name, password);
		users.addUser(newUser);
		return newUser;
	}
	
	//Method Purpose: takes existing credentials, verifies, and transitions to mainMenu
	//Parameters:
	//Return Value: user's info
	private static User login() {
		//checkName from User class
		//checkPassword from User class
		// TODO
		return null;
	}
	
	//Method Purpose: gives option to select or add a task
	//Parameters:
	//Return Value:
	public static void mainMenu() {
		displayActiveTasks();
		System.out.println("Would you like to continue or quit the program (Enter \"C\" or \"Q\"): ");
		String answer = keyboard.nextLine();
		if (answer.toLowerCase().compareTo("c") == 0) {
			System.out.println("Would you like to Select or Add a task (Enter \"1\" or \"2\"): ");
			int reply = keyboard.nextInt();
			if (reply==1) {
				selectTask();
			} else {
				addTask();
			}
		} else {
			quit();
		}
	}
	
	//Method Purpose: gives option to complete, edit, or delete a task while 
	//                displaying current tasks at top (using taskMenu method)
	//Parameters:
	//Return Value:
	public static void selectTask() {
	}
	
	//Method Purpose: will prompt Task class to create new task
	//Parameters:
	//Return Value:
	public static void addTask() {
		//addTask from TaskCollection
	}
	
	//Method Purpose: displays current tasks 
	//Parameters: object of Task class (list/array of tasks for 1 user)
	//Return Value:
	public static void taskMenu(/*Task task*/) {
	}
	
	
	
	//Question: How is this method different from taskMenu?? 
	
	//Method Purpose:
	//Parameters:
	//Return Value:
	public static void displayActiveTasks() {
		//getActiveTasks() from TaskCollection
	}
	
	//Method Purpose: gives option to complete task by accessing data in Task object
	//Parameters: object of Task class 
	//Return Value:
	public static void completeTask(/*Task task*/) {
		//complete() from Task class 
	}
	
	//Method Purpose: exit program
	//Parameters:
	//Return Value:
	public static void quit() {
		System.out.println("Session ended, see you soon!");
		System.exit(0);
	}
}