package Application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController {

	public void launchAddTaskScene(Stage window) throws IOException {
		Pane pane = (Pane) FXMLLoader.load(getClass().getResource("AddTask.fxml"));
		
		Scene addTaskScene = new Scene(pane);
		
		addTaskScene.getStylesheets().add(getClass().getResource("AddTask.css").toExternalForm());
		window.setScene(addTaskScene);
		window.show();
	}
	
	public void launchCreateUserScene(Stage window) throws IOException {
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
		
		Scene createUserScene = new Scene(pane);
		
		createUserScene.getStylesheets().add(getClass().getResource("CreateUser.css").toExternalForm());
		window.setScene(createUserScene);
		window.show();
	}	
	
	public void launchDisplayTasksScene(Stage window) throws IOException {
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("DisplayTasks.fxml"));
		
		Scene displayTasksScene = new Scene(pane);
		
		displayTasksScene.getStylesheets().add(getClass().getResource("DisplayTasks.css").toExternalForm());
		window.setScene(displayTasksScene);
		window.show();
	}
	
	public void launchEditTaskScene(Stage window) throws IOException {
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("EditTask.fxml"));
		
		Scene editTaskScene = new Scene(pane);
		
		editTaskScene.getStylesheets().add(getClass().getResource("AddTask.css").toExternalForm());
		window.setScene(editTaskScene);
		window.show();
	}
	
	public void launchHomeScreenScene(Stage window) throws IOException {
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
			
		Scene homeScreenScene = new Scene(pane);
			
		homeScreenScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());

		window.setScene(homeScreenScene);
		window.show();
	}
	
	public void launchLoginScene(Stage window) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
		
		Scene loginScene = new Scene(root);
			
		loginScene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		window.setScene(loginScene);
		window.show();
	}
	
	public void launchSettingsScene(Stage window) throws IOException {
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Settings.fxml"));
		
		Scene settingsScene = new Scene(pane);
		
		settingsScene.getStylesheets().add(getClass().getResource("Settings.css").toExternalForm());
		window.setScene(settingsScene);
		window.show();
	}
	
	public void launchTaskMenuScene(Stage window) throws IOException {
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("TaskMenu.fxml"));
		
		Scene taskMenuScene = new Scene(pane);
		
		taskMenuScene.getStylesheets().add(getClass().getResource("TaskMenu.css").toExternalForm());
		window.setScene(taskMenuScene);
		window.show();
	}

}
