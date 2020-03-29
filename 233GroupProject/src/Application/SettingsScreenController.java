package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.User;
import javafx.fxml.Initializable;

public class SettingsScreenController implements Initializable {

	@FXML 
	private TextField userName,email; //associated with text object for task name & notes in FXML
	
	@FXML
	private Button backBtn ,updateBtn; //associated with the back button object in FXML
	
	@FXML
	private Label validUserNameLbl, validEmailLbl;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userName.setText(GuiBasedApp.getUser().getUsrName());
		email.setText(GuiBasedApp.getUser().getUsrEmail());
		
		
	}
	
	// method for returning to previous scene
	@FXML
	public void back(ActionEvent event) {
				
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(GuiBasedApp.getPrevScene());
		
	}
	
	// method for returning to previous scene
	@FXML
	public void update(ActionEvent event) throws IOException {
		
		boolean validUsrName = false;
		boolean validEmail = false;
		User user = GuiBasedApp.getUser();
		
		String newUsrName =userName.getText();
		String newUsrEmail = email.getText();
		
		
		if( !(GuiBasedApp.getUsers().doesUsernameExist(newUsrName)) && newUsrName.length() != 0) {
			validUsrName = true;
			
		}else {
			validUserNameLbl.setText("Invalid Username ");
		}
		
		validEmail =  Email.validateEmaill(newUsrEmail);
		
		if 	(!validEmail) {
			validEmailLbl.setText("Invalid Email: "+newUsrEmail);
		}
		
		if (validEmail && validUsrName) {
			user.setUsrName(newUsrName);
			user.setUsrEmail(newUsrEmail);
			
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
			
			Scene HomeScreenScene = new Scene(pane);
			
			HomeScreenScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
			
			GuiBasedApp.setPrevScene(window.getScene());
			window.hide();
			window.setScene(HomeScreenScene);
			window.show();
		}
		
		
		
	}
	
	
	
}
