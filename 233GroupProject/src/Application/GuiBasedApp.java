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
	private static SceneController controller = new SceneController();
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
				primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					save();
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
	
	public static void save() {
		if (tasks != null) {
			tasks.saveTasks("tasks.txt");
		}
		
		users.saveUsers("users.txt");
	}
	
	public static User getUser() {
		return user;
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
		GuiBasedApp.selectedTask = tasks.getTaskByID(id);
	}
	
	public static void editSelectedTask(String newName, String newNotes, LocalDate newDate) {
		if (selectedTask != null) {
			selectedTask.setName(newName);
			selectedTask.setNotes(newNotes);
			selectedTask.setDueDate(newDate);
			selectedTask = null;
		}
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
	public static Scene getScene() {
		return window.getScene();
	}
	public static void loginUser(String username) {
		user = users.getUserByName(username);
		tasks = TaskCollection.loadUsrTasks("tasks.txt", user.getUsrID());
	}
	public static void newUser(User newUser) {
		users.addUser(newUser);
		user = newUser;
		tasks = new TaskCollection();
	}
	public static void addTask(Task t) {
		GuiBasedApp.tasks.addTask(t);
	}
	public static ArrayList<Task> getActiveTasks() {
		tasks.sortTasks();
		return tasks.getActiveTasks();
	}
	//returns the percentage of complete tasks as a decimal
	public static double getPercentageComplete() {
		double fraction;
		ArrayList<Task> activeTasks = getActiveTasks();
		ArrayList<Task> allTasks = tasks.getAllTasks();
		
		if (allTasks.size()==0) {
			return 0;
		}
		else {
			double numComplete = allTasks.size()-activeTasks.size();
			fraction = numComplete/allTasks.size();
			return fraction;
		}
	}
	public static int getTotalTasks() {
		ArrayList<Task> allTasks = tasks.getAllTasks();
		return allTasks.size();
	}
	
	// -------------------- SCENE LAUNCHERS --------------------
	
	public static void launchAddTaskScene() {
		try {
			controller.launchAddTaskScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchCreateUserScene() {
		try {
			controller.launchCreateUserScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchDisplayTasksScene() {
		try {
			controller.launchDisplayTasksScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchEditTaskScene() {
		try {
			controller.launchEditTaskScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchHomeScreenScene() {
		try {
			controller.launchHomeScreenScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchLoginScene() {
		tasks = null;
		try {
			controller.launchLoginScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchSettingsScene() {
		try {
			controller.launchSettingsScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchTaskMenuScene() {
		try {
			controller.launchTaskMenuScene(window);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
