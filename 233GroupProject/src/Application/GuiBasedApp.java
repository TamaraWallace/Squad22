package Application;
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiBasedApp extends Application{

	private static Stage window;
	
	
	public static void main(String args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			window = primaryStage;
			
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			Parent pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			
			Scene loginScene = new Scene(root);
			
			Scene newUserScene = new Scene(pane);
			
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
	
	public  URL getURL(String fxml) {
		return getClass().getResource(fxml);
		 
	}
	public static Scene getScene() {
		return window.getScene();
	}

}
