package Application;
	


import javafx.scene.control.TextField;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;



public class Main extends Application implements EventHandler<ActionEvent>{
	Scene scene1;
	@Override
	public void start(Stage primaryStage) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/application/AddTask.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Taskilla");
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent event) {
		
		
	}
	
	private void checkName(String name) {
		if (name.isEmpty()) {
			System.out.println("Name cant be empty !");
		}
	}
}
