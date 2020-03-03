package Application;

import java.util.UUID;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.TaskCollection;
import main.TextBasedApp;
import main.UserCollection;

public class GuiBasedApp extends Application{
	
	private static UUID userID;
	private static UserCollection users;
	private static TaskCollection tasks;
	private static Stage window;
	private static Scene prevScene;
	
	public static void main(String args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			
			GuiBasedApp.users = UserCollection.loadUsers("users.txt");
			TextBasedApp.setUsers(users);
			
			window = primaryStage;
			
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			
			Scene loginScene = new Scene(root);
			
			
			//this.newUserScene = newUserScene;
			
			loginScene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			
			//String url = "https://github.com/TamaraWallace/Taskilla-05-22/blob/master/233GroupProject/images/taskilla_logo.jpg";
			
			//String path = GuiBasedApp.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			
			Image takilla_icon = new Image("file:///C:\\Users\\karim\\git\\Squad22\\233GroupProject\\images\\taskilla_logo.jpg");
			
			window.setScene(loginScene);
			window.setTitle("Welcome to Taskilla ");
			window.getIcons().add(takilla_icon);
			
			
			window.show();
			
			
			
				
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static UUID getUserID() {
		return userID;
	}
	public static void setUserID(UUID userID) {
		GuiBasedApp.userID = userID;
		TextBasedApp.setUserID(userID);
	}
	public static UserCollection getUsers() {
		return users;
	}
	public static void setUsers(UserCollection users) {
		GuiBasedApp.users = users;
		TextBasedApp.setUsers(users);
	}
	public static TaskCollection getTasks() {
		return tasks;
	}
	public static void setTasks(TaskCollection tasks) {
		GuiBasedApp.tasks = tasks;
		TextBasedApp.setTasks(tasks);
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
	
	
}
