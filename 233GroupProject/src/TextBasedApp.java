import java.util.ArrayList;


//need to import the other four classes to merge functionality
public class TextBasedApp {
	
	//Question: Guys I'm a bit confused - do we want to import objects of the other classes as attributes here? 
	
	//the Type of these attributes is temporary for now (for the skeleton)
	int user;
	ArrayList<String> users = new ArrayList<String>();
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
	}
	
	//Method Purpose: to use the User and UserCollection classes to add another user's info to file
	//Parameters:
	//Return Value: user's info
	public int createNewUser() {
		return user;
	}
	
	//Method Purpose: takes existing credentials, verifies, and transitions to mainMenu
	//Parameters:
	//Return Value: user's info
	public int login() {
		return user;
	}
	
	//Method Purpose: gives option to select or add a task
	//Parameters:
	//Return Value:
	public static void mainMenu() {
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
	}
	
	//Method Purpose: gives option to complete task by accessing data in Task object
	//Parameters: object of Task class 
	//Return Value:
	public static void completeTask(/*Task task*/) {
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
