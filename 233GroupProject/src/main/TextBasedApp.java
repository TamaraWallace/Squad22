package main;

import java.time.LocalDate;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TextBasedApp {
	
	private static UUID userID;
	private static UserCollection users;
	private static TaskCollection tasks;
	private static Scanner keyboard = new Scanner(System.in);
	
	//Method Purpose: 	Runs the programs
	//					Begins by loading all the users from the save file "users.txt"
	//					Displays startMenu which prompts user to login or create new user account
	//Parameters:
	//Return Value:
	public static void main(String[] args) {
		//test(); // put whatever you need in test(). use for debugging
		startMenu();
	}
//	
	//Method Purpose:	Use for inspecting and navigating the program, allows you to subvert normal navigation
	//Parameters:
	//Return Value:
	public static void test() {
		users.display();
		quit();
	}
	
	
	
	// ----------------------- APP NAVIGATION -----------------------
	
	//Method Purpose: 	Welcomes user, asks them to Login, Create New Account or Quit
	//Parameters: 
	//Return Value:
	private static void startMenu() {
		users = UserCollection.loadUsers("users.txt");
		boolean loggedIn = false;
		while (!loggedIn) {
			System.out.println("Welcome to Taskilla!");
			
			String[] options = {"Login", "Create New Account", "Quit"};
			int choice = getChoice(options);
			
			if (choice == 1) {
				userID = login();
				if (userID != null) {
					loggedIn = true;
					tasks = TaskCollection.loadUsrTasks("tasks.txt", userID);
					}
				}
			if (choice == 2) {
				userID = createNewUser();
				tasks = TaskCollection.loadUsrTasks("tasks.txt", userID);
				loggedIn = true;
				}
			if (choice == 3) quit();
		}
		mainMenu();
		
	}
	
	//Method Purpose: 	Asks user if they want to Create a New Task, Select an Existing Task, or Quit
	//Parameters: 
	//Return Value:
	public static void mainMenu() {
		System.out.println("\nMain Menu\n---------");
		
		String[] options = {"View All Tasks", "Add New Task", "Select Task", "Logout", "Quit"};
		
		int choice = getChoice(options);
		
		if (choice == 1) { tasks.display(); mainMenu(); }
		if (choice == 2) addTask();
		if (choice == 3) taskMenu(selectTask());
		if (choice == 4) logout();
		if (choice == 5) quit();
	}
	
	//Method Purpose:	Asks the user if they want to Complete the Task, Edit it, or return to Main Menu
	//Parameters: Task
	//Return Value:
	public static void taskMenu(Task t) {
		if (t == null) {
			System.out.println("User has no active tasks...");
			mainMenu();
		}else {
			System.out.println("Selected Task\n-------------\n" + t.toString());
		}
		System.out.println("\nTask Menu\n---------");
		
		String[] options = {"Complete Selected Task", "Edit Task", "Main Menu"};
		
		int choice = getChoice(options);
		
		if (choice == 1) completeTask(t);
		if (choice == 2) editTask(t);
		if (choice == 3) mainMenu();
	}
	
	
	
	// ----------------------- APP FUNCTIONALITY -----------------------
	
	//Method Purpose: exit program
	//Parameters:
	//Return Value:
	private static void quit() {
		System.out.println("Saving...");
		if (!(tasks == null)) {	tasks.saveTasks("tasks.txt"); }
		users.saveUsers("users.txt");
		System.out.println("Session ended, see you soon!");
		System.exit(0);
	}
	
	//Method Purpose: return to login menu
	//Parameters:
	//Return Value:
	private static void logout() {
		System.out.println("Saving...");
		tasks.saveTasks("tasks.txt");
		users.saveUsers("users.txt");
		System.out.println("Session ended, see you soon!\n");
		userID = null;
		tasks = null;
		startMenu();
	}
	//Method Purpose: takes existing credentials, verifies, and transitions to mainMenu
	//Parameters:
	//Return Value: UUID userID
	private static UUID login() {
		Console cons = System.console();
		
		User usrLogin = null;
		
		boolean returnStart = false;

		String password;
		boolean validUsrName = false;
		
		System.out.print("Please enter your User Name: ");
		String usrName = keyboard.next();
		validUsrName = (users.doesUsernameExist(usrName));

		
		// Ensure that user name exists
		while(!validUsrName) {
			System.out.print("The username entered does not exist. Please enter a diffrent user name: ");
			usrName = keyboard.nextLine();
			validUsrName = users.doesUsernameExist(usrName);
		}
		
		
		//checkPassword from User class
		int attempts = 0;
		
		System.out.print("Please enter your password: ");
		password = keyboard.next();
		attempts++;
		usrLogin = users.validateUsernameAndPassword(usrName, password);
		
		while ((usrLogin == null) && !returnStart) {
			if (attempts == 3) {
				System.out.println("You have attempted 3 times to enter a password. You have one last attempt!! ");
			} else if(attempts > 3) {
				System.out.println("You have attempted too many times! Program will return to start menu.");
				attempts = 0;
				returnStart = true;
			} else {				
				System.out.println("Number of attempts: "+attempts);
			}	
			if (!returnStart) {
				System.out.print("The password you entered is incorrect! Please try again: ");
				password = keyboard.next();
				attempts++;
				usrLogin = users.validateUsernameAndPassword(usrName, password);
			}
		}
		return usrLogin.getUsrID();
	}
	
	//Method Purpose: to use the User and UserCollection classes to add another user's info to file
	//Parameters:
	//Return Value: user's info
	private static UUID createNewUser() {
		Console cons = System.console();
		
		boolean validUsrName = false;
		
		String password = null;
		boolean passCreated = false;
		
		System.out.print("Please enter a User Name: ");
		String name = keyboard.next();
		
		//remove white spaces and display username
		
		// check usrName does not exist
		while(!validUsrName) {
			if (users.doesUsernameExist(name)) {
				validUsrName = true;
			}else {
				System.out.print("The username entered already exists. Please enter a diffrent user name: ");
				name = keyboard.next();
			}
		}
		
		System.out.print("Please enter your email: ");
		String email = keyboard.next();
		
		
		if (cons == null) {
			while (! passCreated) {
				
				System.out.print("Please enter a password: ");
				String password1 = keyboard.next();
				System.out.print("Please confirm your password: ");
				String password2 = keyboard.next();
				if (password1.equals(password2)){
					password = password1;
					//System.out.println("strong pass: " + isPassStrong(password));
					if (isPassStrong(password)) {
						passCreated = true;
					}else {
						System.out.println("and so, please enter a stronger passsword.");
					}
				}else {
					System.out.println("The passwords you entered do not match! Please try again: ");
				}
			}
		}else {
			while (! passCreated) {
				
				String prompt = ("Please enter a password: ");
				String password1 = readPassword(prompt);
				prompt = ("Please confirm your password: ");
				String password2 = readPassword(prompt);
				if (password1.equals(password2)){
					password = password1;
					//System.out.println("password: "+password+" strong pass: " + isPassStrong(password));
					if (isPassStrong(password)) {
						passCreated = true;
					}else {
						System.out.println("and so, please enter a stronger passsword.");
					}
					
					
				}else {
					System.out.println("The passwords you entered do not match! Please try again.");
				}
			}
		}
		
		User newUser = new User(name, password, email);
		users.addUser(newUser);
		return newUser.getUsrID();
	}
	//Method Purpose: to use the User and UserCollection classes to add another user's info to file
	//Parameters:
	//Return Value: user's info
	public static UUID createNewUser(String name, String email, String password1, String password2) {

		String password = null;
		boolean passCreated = false;


		while (! passCreated) {

			if (password1.equals(password2)){
				password = password1;
				if (isPassStrong(password)) {
					passCreated = true;
				}else {
					return null;
				}
			}else {
				return null;
			}
		}


		User newUser = new User(name, password, email);
		users.addUser(newUser);
		return newUser.getUsrID();
	}
	
	//Method Purpose: will prompt Task class to create new task
	//Parameters:
	//Return Value:
	public static void addTask() {
		keyboard.nextLine();
		System.out.println("Task Title: ");
		String task_name = keyboard.nextLine();
		System.out.println("Notes: ");
		String task_notes = keyboard.nextLine();
		

		LocalDate taskDate;
		
		while (true) {
			System.out.print("Due date (YYYY-MM-DD): ");
			String task_date = keyboard.nextLine();
			try {
				taskDate = LocalDate.parse(task_date);
				break;
			} catch (Exception ParseException) {
				System.out.println("Try again\n");
			}
		}
		

		System.out.println("Is the following information correct (yes or no)?");
		System.out.println("Task Title: "+task_name+System.lineSeparator()+"Notes: "+task_notes+System.lineSeparator()+"Due Date: "+taskDate);
			
		String answer = keyboard.nextLine();
		
		while(answer.toLowerCase().compareTo("no")==0 || answer.toLowerCase().compareTo("n")==0) {
			System.out.print("What would you like to edit (Title, Notes, or Date)?");
			String response = keyboard.nextLine();
			
			
			if (response.toLowerCase().compareTo("title")==0) {
				System.out.print("Task Title: ");
				task_name = keyboard.nextLine();
			} else if(response.toLowerCase().compareTo("notes")==0){
				System.out.print("Notes: ");
				task_notes = keyboard.nextLine();
			} else if(response.toLowerCase().compareTo("due date")==0 || response.toLowerCase().compareTo("date")==0) {
				while (true) {
					System.out.print("Due Date (YYYY-MM-DD): ");
					String task_date = keyboard.nextLine();
					try {
						taskDate = LocalDate.parse(task_date);
						break;
					} catch (Exception ParseException) {
						System.out.println("Try again\n");
					}
				}
				
				
			}
			System.out.println("Is the following information correct?");
			System.out.println("Task Title: "+task_name+System.lineSeparator()+"Notes: "+task_notes+System.lineSeparator()+"Due Date: "+taskDate);
				
			answer = keyboard.nextLine();
		}
		
		Task new_task = new Task(userID, task_name, task_notes, false, taskDate);
		
		tasks.addTask(new_task);
		
		mainMenu();
	}
	
	//Method Purpose: gives option to complete task by accessing data in Task object
	//Parameters: Task t 
	//Return Value:
	public static void completeTask(Task t) {
		t.complete();
		mainMenu();
	}
	
	
	//Method Purpose: prompts user to edit the fields of the selected task
	//Parameters: Task t
	public static void editTask(Task t) {
		System.out.println("Not implemented... returning to Task Menu");
		taskMenu(t);
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
	
	//Method Purpose: Reads the hidden password input by user.
	//Parameters: String prompt
	//Return Value: String password
	private static String readPassword (String prompt) {
			
			HidePass et = new HidePass(prompt);
			Thread mask = new Thread(et);
			mask.start();
	
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String password = "";
	
			try { password = in.readLine();
			} catch (IOException ioe) { ioe.printStackTrace(); }
			// stop masking
			et.stopMasking();
			// return the password entered by the user
			return password;
		}
	
	//Method Purpose: determines whether password is strong or not
	//Parameters: String password entered by user
	//Return Value:	boolean 
	public static boolean isPassStrong(String password) {
		boolean passStrong = false;
		//boolean hasUpprC , hasDigit, hasLwrC, hasSpcl;
		int upprC , lwrC, digit, spcl;
		
		upprC = 0;
		lwrC = 0;
		digit = 0;
		spcl = 0;
		
		//hasUpprC = false;
		//hasDigit = false;
		//hasLwrC = false;
		//hasSpcl = false;
		
		if(password.length() >= 8) {
			for (int i = 0; i < password.length(); i++) {
				char ch;
				ch = password.charAt(i);
				//System.out.println("Ch:" + ch+" hasupper: "+(Character.isUpperCase(ch))+" hasslwr: "+(Character.isLowerCase(ch)));
				if( Character.isDigit(ch)) {
					digit ++;
				}
				if ( Character.isLowerCase(ch)) {
					lwrC ++;
				}
				if ( Character.isUpperCase(ch)) {
					upprC ++;
				}
				if (! (Character.isDigit(ch) || Character.isAlphabetic(ch))) {
					spcl ++;
				}
				
			}
			
		}else {
			System.out.println("Password must have ATLEAST 8 characters ");
			passStrong = false;
			return passStrong;
		}
		
		if ((digit > 0) && (lwrC > 0 ) && (upprC > 0 ) && (spcl > 0)) {
			passStrong = true;
			return passStrong; 
		}else {
			System.out.println();
			System.out.print("The password entered is not strong enough. You are missing: ");
			
		}
		
		if(upprC == 0) {
			System.out.print("an Upper case letter, ");			
		}
		if(digit == 0) {
			System.out.print("a Digit, ");			
		}
		if(lwrC == 0) {
			System.out.print("a Lower case letter, ");			
		}
		if(spcl == 0) {
			System.out.print("a Special character (!@#$&), ");			
		}
		return passStrong;
	}
	public static void setUserID(UUID userID) {
		TextBasedApp.userID = userID;
	}
	public static void setUsers(UserCollection users) {
		TextBasedApp.users = users;
	}
	public static void setTasks(TaskCollection tasks) {
		TextBasedApp.tasks = tasks;
	}
}