package Application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.*;

public class GuiBasedApp extends Application{
	
	private static User user;
	private static UserCollection users;
	private static TaskCollection tasks;
	
	private static Stage window;
	private static Scene prevScene;
	
	private static String lgnUserName;
	
	public static String getLgnUserName() {
		return lgnUserName;
	}
	public static void setLgnUserName(String lgnUserName) {
		GuiBasedApp.lgnUserName = lgnUserName;
	}
	
	public static User getUser() {
		return user;
	}
	
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
			
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			
			Scene loginScene = new Scene(root);
			
			
		
			
			loginScene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			
			
			Image takilla_icon = new Image(getClass().getResourceAsStream("/taskilla_icon.png"));
			
			window.setScene(loginScene);
			window.setTitle("Welcome to Taskilla ");
			window.getIcons().add(takilla_icon);
			
			
			window.show();
			
			
			
				
		
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
	
	public static UserCollection getUsers() {
		return users;
	}
	public static void setUsers(UserCollection users) {
		GuiBasedApp.users = users;
	}
	public static TaskCollection getTasks() {
		return tasks;
	}
	public static void setTasks(TaskCollection tasks) {
		GuiBasedApp.tasks = tasks;
	}
	public static Scene getScene() {
		return window.getScene();
	}
	public static Scene getPrevScene() {
		return prevScene;
	}
	public static void setPrevScene(Scene prevScene) {
		GuiBasedApp.prevScene = prevScene;
	}
	public static void loginUser(User loginUser) {
		user = loginUser;
		tasks = TaskCollection.loadUsrTasks("tasks.txt", user.getUsrID());
	}
	public static void newUser(User newUser) {
		users.addUser(newUser);
		user = newUser;
		tasks = new TaskCollection();
	}
	public static void addTask(Task t) {
		GuiBasedApp.tasks.addTask(t);
		tasks.display();
	}
	public static ArrayList<Task> getActiveTasks() {
		return tasks.getActiveTasks();
	}
}
