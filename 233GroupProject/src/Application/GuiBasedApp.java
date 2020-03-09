package Application;

import java.util.Optional;
import java.util.UUID;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.TaskCollection;
import main.TextBasedApp;
import main.User;
import main.UserCollection;

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
	public static void setUser(User user) {
		GuiBasedApp.user = user;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			
				//System.out.println(UUID.randomUUID());
				primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					save();
					System.out.println("Session ended, see you soon!");
					System.exit(0);
					/**
					Alert alert = new Alert(AlertType.CONFIRMATION);
					DialogPane dialogPane = alert.getDialogPane();
								
					dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
					//dialogPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID ,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
					
					dialogPane.setStyle("-fx-border-color: red;");
					alert.setTitle("Close Program");
					alert.setHeaderText(" Are you sure you want to close the program");
					alert.setContentText("Press \"ok\" to close the program or \"cancel\" to return to program");
				
					Optional<ButtonType> choice =  alert.showAndWait();
					
					if (choice.get() == ButtonType.OK){
						
						save();
						System.out.println("Session ended, see you soon!");
						System.exit(0);
					}
					else {
						event.consume();
					}*/
					
					
				}
			} );
			
			GuiBasedApp.users = UserCollection.loadUsers("users.txt");
			TextBasedApp.setUsers(users);
			
			window = primaryStage;
			
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			
			Scene loginScene = new Scene(root);
			
			
			//this.newUserScene = newUserScene;
			
			loginScene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			
			//String url = "https://github.com/TamaraWallace/Taskilla-05-22/blob/master/233GroupProject/images/taskilla_logo.jpg";
			
			//String path = GuiBasedApp.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			
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
		System.out.println("Saving...");
		if (!(tasks == null)) {
			tasks.saveTasks("tasks.txt"); 
		}
		users.saveUsers("users.txt");
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
