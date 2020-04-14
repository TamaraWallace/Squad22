package Application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.*;

public class GuiBasedApp extends Application{

	private static User user; // the logged in User
	private static UserCollection users; // collection of all Users
	private static TaskCollection tasks; // collection of the logged in User's Tasks
	private static Task selectedTask; // the logged in User's currently selected Task
	
	private static Stage window; // the primaryStage of the app, passed as a parameter for Scene Navigation
	private static SceneNavigator navigator = new SceneNavigator(); // an Object to launch Scenes.
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//Method Purpose: 	Initializes program by setting the window as the primaryStage and launching the Login Scene.
	//Parameters: 		Stage primaryStage
	//Return Value: 	Void
	//Functionality:	Defines functionality for CloseRequests to save the Users and Tasks and exit the program.
	//					Initializes the users instance variable by loading all Users from the "users.txt" file
	//					Initializes the window instance variable as the primaryStage
	//					Updates the title of the primaryStage to "Welcome to Taskilla"
	//					Adds our logo as an Icon to the primaryStage
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
				primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					saveUsers();
					if (tasks != null) saveTasks();
					System.out.println("Session ended, see you soon!");
					System.exit(0);
				}
			} );
			users = UserCollection.loadUsers("users.txt");
	
			window = primaryStage;
			Image taskilla_icon = new Image(getClass().getResourceAsStream("/taskilla_icon.png"));
			window.setTitle("Welcome to Taskilla ");
			window.getIcons().add(taskilla_icon);
			
			launchLoginScene();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// ----------------------- SAVE FUNCTIONS -----------------------
	
	
	//Method Purpose: 	Save all tasks to the "tasks.txt" file
	//Parameters: 		
	//Return Value: 	Void
	private static void saveTasks() {
		tasks.saveTasks("tasks.txt");
	}
	
	//Method Purpose: 	Save all users to the "users.txt" file
	//Parameters: 		
	//Return Value: 	Void
	private static void saveUsers() {
		users.saveUsers("users.txt");
	}
	
	
	// ----------------------- VARIABLE MUTATORS -----------------------
	//  (these methods change the references of the instance variables)
	
	
	//Method Purpose: 	Logs in User with given username, loads their tasks, and launches the Home Screen Scene
	//Parameters: 		String username
	//Return Value: 	Void
	public static void loginUser(String username) {
		user = users.getUserByName(username);
		tasks = TaskCollection.loadUsrTasks("tasks.txt", user.getUsrID());
		launchHomeScreenScene();
	}
	
	//Method Purpose: 	Creates a new User with given name, pword, and email. Adds that User to users. Then logs that User in
	//Parameters: 		String name, String pword, String email
	//Return Value: 	Void
	public static void newUser(String name, String pword, String email) {
		User newUser = new User(name, pword, email);
		users.addUser(newUser);
		user = newUser;
		tasks = new TaskCollection();
		launchHomeScreenScene();
	}
	
	//Method Purpose:	Saves logged in User's tasks. Sets user, selectedTask, and tasks to null. Launches Login Scene
	//Parameters:		
	//Return Value:		Void
	public static void logout() {
		saveTasks();
		user = null;
		selectedTask = null;
		tasks = null;
		launchLoginScene();
	}
	
	//Method Purpose: 	Given a string representation of a UUID, sets the selectedTask to Task with the given UUID
	//Parameters: 		String taskID
	//Return Value: 	Void
	public static void setSelectedTaskByID(String taskID) {
		UUID id = UUID.fromString(taskID);
		selectedTask = tasks.getTaskByID(id);
	}
	
	
	// ----------------------- APP FUNCTIONALITY -----------------------
	
	
	//Method Purpose: 	Creates a new Task with given name, notes, and dueDate. Adds that Task to tasks.
	//Parameters: 		String name, String notes, LocalDate dueDate
	//Return Value: 	Void
	public static void addTask(String name, String notes, LocalDate dueDate) {
		Task newTask = new Task(user.getUsrID(), name, notes, dueDate);
		tasks.addTask(newTask);
	}
	
	//Method Purpose: 	Edits the logged in User with the given new values.
	//Parameters: 		String newName, String newEmail
	//Return Value: 	Void
	//Functionality:	newName and newEmail may or may not be different from the current values of the logged in User.
	//					newEmail may also be an empty string as emails are optional.
	public static void editUser(String newName, String newEmail) {
		user.setUsrName(newName);
		user.setUsrEmail(newEmail);
	}
	
	//Method Purpose: 	Edits the selectedTask with the given new values
	//Parameters: 		String newName, String newNotes, LocalDate newDate
	//Return Value: 	Void
	//Functionality:	newName, newNotes, and newDate may or may not be different from the current values of the
	//					selectedTask.
	public static void editSelectedTask(String newName, String newNotes, LocalDate newDate) {
		selectedTask.setName(newName);
		selectedTask.setNotes(newNotes);
		selectedTask.setDueDate(newDate);
	}
	
	//Method Purpose: 	Completes the selectedTask
	//Parameters: 		
	//Return Value: 	Void
	public static void completeSelectedTask() {
		selectedTask.complete();
	}
	
	//Method Purpose:	Deletes the selectedTask
	//Parameters:
	//Return Value:		Void
	public static void deleteSelectedTask() {
		selectedTask.delete();
	}
	
	//Method Purpose: 	Given a string representation of a UUID, completes the corresponding Task
	//Parameters: 		String taskID
	//Return Value: 	Void
	public static void completeTaskByID(String taskID) {
		UUID id = UUID.fromString(taskID);
		tasks.getTaskByID(id).complete();
	}
	
	//Method Purpose: 	Given a string representation of a UUID, deletes the corresponding Task
	//Parameters: 		String taskID
	//Return Value: 	Void	
	public static void deleteTaskByID(String taskID) {
		UUID id = UUID.fromString(taskID);
		tasks.getTaskByID(id).delete();
	}
	
	
	// ----------------------- HELPERS -----------------------

	
	//Method Purpose: 	Returns the logged in user's Username
	//Parameters:
	//Return Value: 	String
	//Notes:			Used to Initialize the HomeScreen and Settings Scenes.
	public static String getUsername() {
		return user.getUsrName();
	}
	
	//Method Purpose: 	Returns the logged in user's Email
	//Parameters:
	//Return Value: 	String
	//Notes:			Used to Initialize the Settings Scene.
	public static String getUserEmail() {
		return user.getUsrEmail();
	}
	
	//Method Purpose: 	Returns an array of the selectedTasks name, notes and dueDate.
	//Parameters: 		
	//Return Value: 	Task
	//Notes:			Used to Initialize the TaskMenu and EditTask Scenes.
	public static String[] getSelectedTasksInfo() {
		String[] vals = new String[3];
		vals[0] = selectedTask.getName();
		vals[1] = selectedTask.getNotes();
		vals[2] = selectedTask.getDueDate().toString();
		return vals;
	}	

	//Method Purpose: 	Determines if the given name & password match the credentials of a User in users
	//Parameters: 		String name, String pword
	//Return Value: 	boolean
	public static boolean validateUsernameAndPassword(String name, String pword) {
		return users.validateUsernameAndPassword(name, pword);
	}

	//Method Purpose:	Determines if a the given name is a valid User
	//Parameters: 		String name
	//Return Value: 	boolean
	public static boolean doesUsernameExist(String name) {
		return users.doesUsernameExist(name);
	}
	
	//Method Purpose: 	Returns a sorted list of Strings that correspond to the data members of this user's
	//					currently active tasks.
	//Parameters: 		
	//Return Value: 	ArrayList<Task>
	//Notes:			Used to initialize ListViews
	public static ArrayList<String> getActiveTasks() {
		tasks.sortTasks();
		ArrayList<Task> activeTasks = tasks.getActiveTasks();
		ArrayList<String> activeTaskStrings = new ArrayList<String>();
		
		for (Task task: activeTasks) {
			String display;
			String taskId = task.getTaskID().toString();
			display = taskId +","+"Task: "+ task.getName()+ "\nNotes: " +task.getNotes() + "\nDue: "+ task.getDueDate().toString();
			activeTaskStrings.add(display);
		}
		return activeTaskStrings;
	}
	
	//Method Purpose: 	Returns a decimal representation of the percentage of the User's Tasks that are complete.
	//Parameters: 		
	//Return Value: 	double
	public static double getPercentageComplete() {
		double numActive = getActiveTasks().size();
		double totalTasks = getTotalNumOfTasks();
		
		if (totalTasks==0) {
			return 0;
		}
		else {
			double numComplete = totalTasks - numActive;
			return numComplete/totalTasks;
		}
	}
	
	//Method Purpose: 	Returns the total number of tasks that a user has.
	//Parameters: 		
	//Return Value: 	int
	public static int getTotalNumOfTasks() {
		return tasks.getNumberOfTasks();
	}
	
	
	// -------------------- SCENE LAUNCHERS --------------------
	
	
	public static void launchAddTaskScene() {
		try {
			navigator.launchAddTaskScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchCreateUserScene() {
		try {
			navigator.launchCreateUserScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchDisplayTasksScene() {
		try {
			navigator.launchDisplayTasksScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchEditTaskScene() {
		try {
			navigator.launchEditTaskScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchHomeScreenScene() {
		try {
			navigator.launchHomeScreenScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchLoginScene() {
		try {
			navigator.launchLoginScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchSettingsScene() {
		try {
			navigator.launchSettingsScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchTaskMenuScene() {
		try {
			navigator.launchTaskMenuScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
