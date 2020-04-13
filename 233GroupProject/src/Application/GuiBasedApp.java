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

	private static User user;
	private static UserCollection users;
	private static TaskCollection tasks;
	private static Task selectedTask;
	
	private static Stage window;
	private static SceneNavigator navigator = new SceneNavigator();
	
	public static void main(String[] args) {
		launch(args);
	}
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
			
			GuiBasedApp.users = UserCollection.loadUsers("users.txt");
			
			window = primaryStage;
			Image taskilla_icon = new Image(getClass().getResourceAsStream("/taskilla_icon.png"));
			window.setTitle("Welcome to Taskilla ");
			window.getIcons().add(taskilla_icon);
			
			launchLoginScene();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void saveTasks() {
		tasks.saveTasks("tasks.txt");
	}
	
	private static void saveUsers() {
		users.saveUsers("users.txt");
	}
	
	public static User getUser() {
		return new User(user);
	}
	
	public static boolean validateUsernameAndPassword(String name, String pword) {
		return users.validateUsernameAndPassword(name, pword);
	}
	
	public static boolean doesUsernameExist(String name) {
		return users.doesUsernameExist(name);
	}
	
	// Returns a copy of the selected Task (to prevent privacy leak)
	public static Task getSelectedTask() {
		return new Task(selectedTask);
	}
	
	public static void setSelectedTaskByID(String taskID) {
		UUID id = UUID.fromString(taskID);
		selectedTask = tasks.getTaskByID(id);
	}
	
	public static void editSelectedTask(String newName, String newNotes, LocalDate newDate) {
		if (selectedTask != null) {
			selectedTask.setName(newName);
			selectedTask.setNotes(newNotes);
			selectedTask.setDueDate(newDate);
			selectedTask = null;
		}
	}
	
	public static void updateUser(String newName, String newEmail) {
		user.setUsrName(newName);
		user.setUsrEmail(newEmail);
	}
	
	public static void completeSelectedTask() {
		if (selectedTask != null) {
			selectedTask.complete();
			selectedTask = null;
		}
	}
	
	public static void deleteSelectedTask() {
		if (selectedTask != null) {
			selectedTask.delete();
			selectedTask = null;
		}
	}
	
	public static void completeTaskByID(String taskID) {
		UUID id = UUID.fromString(taskID);
		tasks.getTaskByID(id).complete();
	}
	
	public static void deleteTaskByID(String taskID) {
		UUID id = UUID.fromString(taskID);
		tasks.getTaskByID(id).delete();
	}
	
	public static void setTasks(TaskCollection tasks) {
		GuiBasedApp.tasks = tasks;
	}
	public static void loginUser(String username) {
		user = users.getUserByName(username);
		tasks = TaskCollection.loadUsrTasks("tasks.txt", user.getUsrID());
		launchHomeScreenScene();
	}
	
	public static void logout() {
		saveTasks();
		user = null;
		selectedTask = null;
		tasks = null;
		launchLoginScene();
	}
	
	public static void newUser(String name, String pword, String email) {
		User newUser = new User(name, pword, email);
		users.addUser(newUser);
		loginUser(name);
	}
	public static void addTask(String taskName, String taskNotes, LocalDate taskDate) {
		Task newTask = new Task(user.getUsrID(), taskName, taskNotes, taskDate);
		GuiBasedApp.tasks.addTask(newTask);

		System.out.println("New task created:\n" + newTask.toString());
	}
	public static ArrayList<Task> getActiveTasks() {
		tasks.sortTasks();
		return tasks.getActiveTasks();
	}
	//returns the percentage of complete tasks as a decimal
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
	public static int getTotalNumOfTasks() {
		ArrayList<Task> allTasks = tasks.getAllTasks();
		int counter = 0;
		for (Task t: allTasks) {
			if(!(t.getName().compareTo("")==0)) {
				counter+=1;
			}
		}
		return counter;
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
		tasks = null;
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
