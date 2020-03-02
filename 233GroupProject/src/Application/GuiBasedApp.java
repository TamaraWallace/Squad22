package Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
			
			loginScene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			
			//String url = "https://github.com/TamaraWallace/Taskilla-05-22/blob/master/233GroupProject/images/taskilla_logo.jpg";
			
			Image takilla_icon = new Image("/images/taskilla_icon.png");
			
			
			
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("Taskilla2");
			primaryStage.getIcons().add(takilla_icon);
			
			
			primaryStage.show();
			
				
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
