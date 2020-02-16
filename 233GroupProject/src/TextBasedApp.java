import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class TextBasedApp {
	
	private static User user;
	private static UserCollection users;
	private static TaskCollection tasks;
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		users = UserCollection.loadUsers("users.txt");
		
		test(); // put whatever you need in test(). use for debugging
		
		startMenu();
		tasks = TaskCollection.loadUsrTasks("tasks.txt", user.getUsrID());
		mainMenu();
	}
	
	//Method Purpose:	Use for inspecting and navigating the program, allows you to subvert normal navigation
	//Parameters:
	//Return Value
	public static void test() {
		users.display();
		//quit();
	}
	
	
	// ----------------------- APP NAVIGATION -----------------------
	
	//Method Purpose: 	Welcomes user, asks them to Login, Create New Account or Quit
	//Parameters: 
	//Return Value:
	private static void startMenu() {
		System.out.println("Welcome!");
	
		String[] options = {"Login", "Create New Account", "Quit"};
		int choice = getChoice(options);
		
		if (choice == 1) { user = login(); }
		if (choice == 2) { user = createNewUser(); tasks = new TaskCollection(); }
		if (choice == 3) quit();
	}
	
	//Method Purpose: 	Asks user if they want to Create a New Task, Select an Existing Task, or Quit
	//Parameters: 
	//Return Value:
	public static void mainMenu() {
		System.out.println("Main Menu\n---------");
		
		String[] options = {"Add New Task", "Complete Task", "Quit"};
		
		int choice = getChoice(options);
		
		if (choice == 1) addTask();
		if (choice == 2) taskMenu(selectTask());
		if (choice == 3) quit();
	}
	
	//Method Purpose:	Asks the user if they want to Complete the Task, Edit it, or return to Main Menu
	//Parameters: Task
	//Return Value:
	public static void taskMenu(Task t) {
		if (t == null) {
			System.out.println("User has no active tasks...");
			mainMenu();
		}
		System.out.println("Task Menu\n---------");
		
		String[] options = {"Complete Selected Task", "Main Menu"};
		
		int choice = getChoice(options);
		
		if (choice == 1) completeTask(t);
		if (choice == 2) mainMenu();
	}
	
	
	
	// ----------------------- APP FUNCTIONALITY -----------------------
	
	//Method Purpose: exit program
	//Parameters:
	//Return Value:
	public static void quit() {
		System.out.println("Session ended, see you soon!");
		System.exit(0);
	}
	
	//Method Purpose: takes existing credentials, verifies, and transitions to mainMenu
	//Parameters:
	//Return Value: user's info
	private static User login() {
		//checkName from User class
		//checkPassword from User class
		// TODO
		return new User("john", "12345");
	}
	
	//Method Purpose: to use the User and UserCollection classes to add another user's info to file
	//Parameters:
	//Return Value: user's info
	private static User createNewUser() {
		//Question: how do we create a user ID #?
		Console cons = System.console();
		String password = null;
		boolean passCreated = false;
		
		System.out.print("Please enter your name: ");
		String name = keyboard.nextLine();
		
		// check usrName does not exist
		if (cons == null) {
			while (! passCreated) {
				
				System.out.print("Please enter a password: ");
				String password1 = keyboard.nextLine();
				System.out.print("Please confimr your password: ");
				String password2 = keyboard.nextLine();
				if (password1.equals(password2)){
					passCreated = true;
					password = password1;
				}else {
					System.out.println("The passwords you entered do not match! Please try again: ");
				}
			}
		}else {
			while (! passCreated) {
				
				String prompt = ("Please enter a password: ");
				char[] password1 = cons.readPassword(prompt);
				prompt = ("Please confimr your password: ");
				char[] password2 = cons.readPassword(prompt);
				if (Arrays.equals(password1,password2)){
					passCreated = true;
					password = password1.toString();
				}else {
					System.out.println("The passwords you entered do not match! Please try again: ");
				}
			}
		}
		User newUser = new User(name, password);
		users.addUser(newUser);
		return newUser;
	}
	
	//Method Purpose: will prompt Task class to create new task
	//Parameters:
	//Return Value:
	public static void addTask() {
		//addTask from TaskCollection
		mainMenu();
	}
	
	//Method Purpose: gives option to complete task by accessing data in Task object
	//Parameters: object of Task class 
	//Return Value:
	public static void completeTask(Task t) {
		//complete() from Task class
		mainMenu();
	}
	


	// ----------------------- HELPERS -----------------------
	
	//Method Purpose: prompts the user to select one of their active tasks
	//Parameters:
	//Return Value: Task
	public static Task selectTask() {
		ArrayList<Task> temp = tasks.getActiveTasks();
		
		if (temp.size() == 0) return null;
		
		String[] options = new String[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			options[i] = temp.get(i).getName();
		}
		
		int choice = getChoice(options);
		return temp.get(choice-1);
	}
	
	//Method Purpose: prompts user to select one of the specified options
	//Parameters: an array of Strings that represent the options available to the user
	//Return Value: int
	private static int getChoice(String[] options) {
		int choice = 0;
		// if there are 3 options, validInputs = [1, 2, 3]
		ArrayList<Integer> validInputs = new ArrayList<Integer>();
		for (Integer i = 1; i<=options.length; i++) validInputs.add(i);
		
		while (true) {
			System.out.println("Please select one of the following:");
			for (int i = 0; i < options.length; i++) 
				System.out.println("\t" + validInputs.get(i) + ". " + options[i]);
			if (keyboard.hasNextInt()) {
				choice = keyboard.nextInt();
				if (validInputs.contains(choice)) break;
				else System.out.println("'" + choice + "'" + " is not one of the options " + validInputs.toString());
			} else {
				System.out.println("'" + keyboard.next() + "'" + " is not one of the options " + validInputs.toString());
			}
			keyboard.reset();
		}
		return choice;
	}

}