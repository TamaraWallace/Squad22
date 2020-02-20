import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TextBasedApp {
	
	private static User user;
	private static UserCollection users;
	private static TaskCollection tasks;
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		users = UserCollection.loadUsers("./users.txt");
		
		//test(); // put whatever you need in test(). use for debugging
		
		startMenu();
		tasks = TaskCollection.loadUsrTasks("./tasks.txt", user.getUsrID());
		mainMenu();
	}
	
	//Method Purpose:	Use for inspecting and navigating the program, allows you to subvert normal navigation
	//Parameters:
	//Return Value
	public static void test() {
		
		users.display();
		User testuser = users.findUser("student1");
		System.out.println(testuser);
		testuser = users.findUser("employee1");
		System.out.println(testuser);
		testuser = users.findUser("demo1");
		System.out.println(testuser);
		testuser = users.findUser("test");
		System.out.println(testuser);
		
		quit();
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
		System.out.println("\nMain Menu\n---------");
		
		String[] options = {"View All Tasks", "Add New Task", "Select Task", "Quit"};
		
		int choice = getChoice(options);
		
		if (choice == 1) { tasks.display(); mainMenu(); }
		if (choice == 2) addTask();
		if (choice == 3) taskMenu(selectTask());
		if (choice == 4) quit();
	}
	
	//Method Purpose:	Asks the user if they want to Complete the Task, Edit it, or return to Main Menu
	//Parameters: Task
	//Return Value:
	public static void taskMenu(Task t) {
		
		if (t == null) {
			System.out.println("User has no active tasks...");
			mainMenu();
		}else {
			System.out.println(t.toString());
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
		System.out.println("Saving...");
		tasks.saveTasks("./tasks.txt");
		users.saveUsers("./users.txt");
		System.out.println("Session ended, see you soon!");
		System.exit(0);
	}
	
	//Method Purpose: takes existing credentials, verifies, and transitions to mainMenu
	//Parameters:
	//Return Value: user's info
	private static User login() {
		
		Console cons = System.console();
		
		User usrLogin;
		
		boolean validUsrName = false;
		
		String prompt;
		String password;
		boolean validPass = false;
		
		System.out.print("Please enter your User Name: ");
		String usrName = keyboard.next();
		usrLogin = users.findUser(usrName);
		
		// check usrName does not exist
		while(!validUsrName) {			
			if (usrLogin == null) {
				System.out.print("The username entered does not exist. Please enter a diffrent user name: ");
				usrName = keyboard.next();
				usrLogin = users.findUser(usrName);
			}else {
				validUsrName = true;
			}
		}
		
		
		//checkPassword from User class
		String usrPassword = usrLogin.getUsrPassword();
		
		
		if (cons == null) {
			 System.out.print("Please enter your password: ");
			 password = keyboard.next();
			while (! validPass) {
				
				if (usrPassword.equals(password)){
					validPass = true;
					
				}else {
					System.out.print("The password you entered is incorrect! Please try again: ");
					password = keyboard.next();
				}
			}
		}else {
			prompt = "Please enter your password: ";
			password = readPassword(prompt);
			while (! validPass) {
				
							
				if (usrPassword.equals(password)){
					validPass = true;
					
				}else {
					prompt = "The passwords you entered do not match! Please try again: ";
					password = readPassword(prompt);
				}
			}
		}
		
		return usrLogin;
	}
	
	//Method Purpose: to use the User and UserCollection classes to add another user's info to file
	//Parameters:
	
	//Return Value: user's info
	private static User createNewUser() {
		//Question: how do we create a user ID #?
		Console cons = System.console();
		
				
		boolean validUsrName = false;
		
		String password = null;
		boolean passCreated = false;
		
		System.out.print("Please enter a User Name: ");
		String name = keyboard.next();
		
		//remove white spaces and display username
		
		// check usrName does not exist
		while(!validUsrName) {
			if (users.findUser(name) == null) {
				validUsrName = true;
			}else {
				System.out.print("The username entered already exists. Please enter a diffrent user name: ");
				name = keyboard.next();
			}
		}
		
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
		
		User newUser = new User(name, password);
		users.addUser(newUser);
		return newUser;
	}
	
	
	public static String readPassword (String prompt) {
			
			HidePass et = new HidePass(prompt);
			Thread mask = new Thread(et);
			mask.start();
	
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String password = "";
	
			try {
				password = in.readLine();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			// stop masking
			et.stopMasking();
			// return the password entered by the user
			return password;
			  
		}
	
	//Method Purpose: determines whether password is strong or not
	//Parameters: String password entered by user
	//Return Value:	boolean 
	private static boolean isPassStrong(String password) {
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
	
	
	
	
	
	//Method Purpose: will prompt Task class to create new task
	//Parameters:
	//Return Value:
	public static void addTask() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Task Title: ");
		String task_name = keyboard.nextLine();
		System.out.println("Notes: ");
		String task_notes = keyboard.nextLine();
		

		Date taskDate;
		
		while (true) {
			System.out.println("Due date(Ex: dd/MM/yyyy): ");
			String task_date = keyboard.nextLine();
			try {
				taskDate = new SimpleDateFormat("dd/MM/yyyy").parse(task_date);
				break;
			} catch (Exception ParseException) {
				System.out.println("Try again\n Date(Ex: dd/MM/yyyy):");
			}
		}
		

		System.out.println("Is the following information correct (yes or no)?");
		System.out.println(task_name+System.lineSeparator()+task_notes+System.lineSeparator()+taskDate);
			
		String answer = keyboard.nextLine();
		
		while(answer.toLowerCase().compareTo("no")==0 || answer.toLowerCase().compareTo("n")==0) {
			System.out.println("What would you like to edit?");
			String response = keyboard.nextLine();
			if (response.toLowerCase()=="title") {
				System.out.println("Task Title: ");
				task_name = keyboard.nextLine();
			} else if(response.toLowerCase()=="notes"){
				System.out.println("Notes: ");
				task_notes = keyboard.nextLine();
			} else if(response.toLowerCase()=="due date" || response.toLowerCase()=="date") {
				System.out.println("Due date: ");
				while (true) {
					System.out.println("Due date(Ex: dd/MM/yyyy): ");
					String task_date = keyboard.nextLine();
					try {
						taskDate = new SimpleDateFormat("dd/MM/yyyy").parse(task_date);
						break;
					} catch (Exception ParseException) {
						System.out.println("Try again\n Date(Ex: dd/MM/yyyy):");
					}
				}
				
				
			}
			System.out.println("Is the following information correct?");
			System.out.println(task_name+System.lineSeparator()+task_notes+System.lineSeparator()+taskDate);
				
			answer = keyboard.nextLine();
		}
		
		int userID = user.getUsrID();
		
		Task new_task = new Task(userID, task_name, task_notes, false, taskDate);
		
		tasks.addTask(new_task);
		
		mainMenu();
	}
	
	//Method Purpose: gives option to complete task by accessing data in Task object
	//Parameters: object of Task class 
	//Return Value:
	public static void completeTask(Task t) {
		t.setCompleted(true);
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