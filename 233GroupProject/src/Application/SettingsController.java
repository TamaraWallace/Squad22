package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.User;
import javafx.fxml.Initializable;

public class SettingsController implements Initializable {

	@FXML 
	private TextField userName,email; //associated with text object for task name & notes in FXML
	
	@FXML
	private Button backBtn ,updateBtn; //associated with the back button object in FXML
	
	@FXML
	private Label validUserNameLbl, validEmailLbl;
	
	// FXML styling 
	private static final String defaultStyle = "-fx-background-color: #ffffff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;

	private static final String warningStyle = "-fx-background-color: #ffffff; -fx-border-color: #ff0000; -fx-border-width: 5px;";

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Settings Scene");
		
		userName.setText(GuiBasedApp.getUsername());
		email.setText(GuiBasedApp.getUserEmail());
	}
	
	
	// ----------------------- EVENT HANDLERS -----------------------
	
	
	//Method Purpose: 	Handler for backBtn. Launches the Home Screen Scene
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	@FXML
	public void back(ActionEvent event) {
		GuiBasedApp.launchHomeScreenScene();
	}
	
	//Method Purpose: 	Handler for updateBtn. Check if new Username and/or email are valid, if they are
	//					updates the User's info and then launches the Home Screen Scene
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	//Functionality:	
	@FXML
	public void update(ActionEvent event) throws IOException {
		
		String currentName = GuiBasedApp.getUsername();
		String currentEmail = GuiBasedApp.getUserEmail();
		
		String newUsrName = userName.getText();
		String newUsrEmail = email.getText();
		
		// Name is valid if:	same as current name OR
		//						does not match any other existing user's name && length is not 0
		boolean validUsername = (((newUsrName.length() != 0) && (!GuiBasedApp.doesUsernameExist(newUsrName))) || (newUsrName.compareToIgnoreCase(currentName) == 0));
		
		if (validUsername) {
			userName.setStyle(defaultStyle);
		} else {
			validUserNameLbl.setText("Invalid Username");
			userName.setStyle(warningStyle);
		}
		
		// Email is valid if:	same as current email OR
		//						is a properly formatted email address OR
		//						the length is 0
		//boolean validEmail =  User.validateEmaill(newUsrEmail) || (newUsrEmail.compareToIgnoreCase(currentEmail) == 0) || (newUsrEmail.length() == 0);
		boolean validEmail = true;
		if (validEmail) {
			email.setStyle(defaultStyle);
		} else {
			validEmailLbl.setText("Invalid Email: "+newUsrEmail);
			email.setStyle(warningStyle);
		}
		
		if (validEmail && validUsername) {
			GuiBasedApp.editUser(newUsrName,newUsrEmail);
			
			GuiBasedApp.launchHomeScreenScene();
		}
	}
}
