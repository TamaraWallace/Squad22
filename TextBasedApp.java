import java.util.ArrayList;
import java.util.Scanner;


//need other four classes to merge functionality
public class TextBasedApp {
	
	//the Type of these attributes is temporary for now (for the skeleton)
	//type = User
	int user;
	//type = UserCollection
	ArrayList<String> users = new ArrayList<String>();
	//type = TaskCollection
	ArrayList<String> userTasks = new ArrayList<String>();
	
	//Constructor - temporary format
	public TextBasedApp(int user, ArrayList user_collection, ArrayList task_collection) {
		this.user = user;
		this.users = user_collection;
		this.userTasks = task_collection;
		
	}
	//Method Purpose: start the program, prompt "Welcome!", display options to login
	//Parameters: 
	//Return Value:
	public static void start() {
		System.out.println("Welcome!");
		System.out.println("Are you an existing user: ");
		Scanner keyboard = new Scanner(System.in);
		String answer = keyboard.nextLine();
	
		if (answer.toLowerCase()=="yes") {
			//this.login();
		} else {
			//this.createNewUser();
		}
	}
	
	//Method Purpose: to use the User and UserCollection classes to add another user's info to file
	//Parameters:
	//Return Value: user's info
	public int createNewUser() {
		Scanner keyboard = new Scanner(System.in);
		//Question: how do we create a user ID #? 
		System.out.println("Please enter your name: ");
		String name = keyboard.nextLine();
		System.out.println("Please enter a password: ");
		String password = keyboard.nextLine();
		//User new_user = new User(userID, name, password);
		
		//Question: how would you enact the "addUser" method on new_user since it is of type User? 
		
		return user;
	}
	
	//Method Purpose: takes existing credentials, verifies, and transitions to mainMenu
	//Parameters:
	//Return Value: user's info
	public int login() {
		//checkName from User class
		//checkPassword from User class
		return user;
	}
	
	//Method Purpose: gives option to select or add a task
	//Parameters:
	//Return Value:
	public static void mainMenu() {
		displayActiveTasks();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Would you like to continue or quit the program (Enter \"C\" or \"Q\"): ");
		String answer = keyboard.nextLine();
		if (answer.toLowerCase()=="c") {
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
		System.exit(0);
	}
	

	public static void main(String[] args) {
		start();
		
		

	}

}
