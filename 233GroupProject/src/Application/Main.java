package Application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
	Scene scene1;
	@Override
	public void start(Stage primaryStage) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/Application/EditTask.fxml"));
		
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

