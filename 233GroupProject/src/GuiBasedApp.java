import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiBasedApp extends Application{

	
	public static void main(String args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			Scene loginScene = new Scene(root);
			
			loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			
			
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("Taskilla");
			
			primaryStage.show();
			
				
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
