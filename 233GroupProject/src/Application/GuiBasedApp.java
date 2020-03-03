package Application;
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiBasedApp extends Application{

	private static Stage primaryStage;
	
	
	public static void main(String args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			this.primaryStage = primaryStage;
			
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			
			Scene loginScene = new Scene(root);
			
			loginScene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			
			//String url = "https://github.com/TamaraWallace/Taskilla-05-22/blob/master/233GroupProject/images/taskilla_logo.jpg";
			
			//String path = GuiBasedApp.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			
			Image takilla_icon = new Image("file:///C:\\Users\\karim\\git\\Squad22\\233GroupProject\\images\\taskilla_logo.jpg");
			
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("Welcome to Taskilla ");
			primaryStage.getIcons().add(takilla_icon);
			
			
			primaryStage.show();
			
				
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setScene(Scene scene) {
		
		primaryStage.setScene(scene);
	}
	public static Scene getScene() {
		return primaryStage.getScene();
	}

}
