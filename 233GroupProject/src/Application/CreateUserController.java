package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class CreateUserController implements Initializable {
	
	// FXML objects
	@FXML
	private Button backBtn, createUsrBtn;
	
	@FXML
	private PasswordField newUsrPwd, newUsrConfPwd;
	
	@FXML
	private TextField usrEmail, newUsrName;
	
	@FXML
	private Label newValidEmailLbl, newValidPassLbl, newValidConfPassLbl, newValidUsrLbl;
	
	// FXML styling 
	private static final String defaultStyle = "-fx-background-color: #b3c2ff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;

	private static final String warningStyle = "-fx-background-color: #b3c2ff; -fx-border-color: #ff0000; -fx-border-width: 5px;";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Create User Scene");
	}
	
	// ----------------------- EVENT HANDLERS -----------------------
	
	//Method Purpose: 	Handler for backBtn. Launches the Login Scene
	//Parameters: 		ActionEvent event
	//Return Value: 	Void	
	@FXML
	public void back(ActionEvent event) throws IOException {
		GuiBasedApp.launchLoginScene();
	}
	
	//Method Purpose: 	Handler for createUsrBtn. Attempts to create a new User given the field values, if
	//					successful, logs in new User and launches the Home Screen Scene.
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	//Functionality:	Verifies that the user has input a unique Username and provided matching passwords.
	//					Notifies the user if either of these requirements are not met. If they are, calls
	//					GuiBasedApp.newUser(name, pword, email)	with the values from the newUsrName, newUsrPwd,
	//					and usrEmail text fields as the parameters.
	@FXML
	public void createUsr(ActionEvent event) throws IOException {
		String name = newUsrName.getText();
		String email = usrEmail.getText();
		String password1 = newUsrPwd.getText();
		String password2 = newUsrConfPwd.getText();
		
		// checks if the provided name is unique
		boolean nameIsUnique = !(GuiBasedApp.doesUsernameExist(name));
		
		// checking new Username
		if (name.isEmpty()){
			newUsrName.setStyle(warningStyle);
			newValidUsrLbl.setText("Username can't be empty!");
		} else if (!nameIsUnique) {
			newUsrName.setStyle(warningStyle);
			newValidUsrLbl.setText("Username already exists!");
		} else {
			newUsrName.setStyle(defaultStyle);
		}
		
		// Checking that first password is not empty
		if(password1.isEmpty()) {
			newUsrPwd.setStyle(warningStyle);
			newValidPassLbl.setText("Can't be empty!");
		} else newUsrPwd.setStyle(defaultStyle);
		
		// Checking that second password is not empty
		if(password2.isEmpty()) {
			newUsrConfPwd.setStyle(warningStyle);
			newValidConfPassLbl.setText("Can't be empty!");
		} else newUsrConfPwd.setStyle(defaultStyle);
		
		// Checking that passwords match
		if(!password1.equals(password2)) {
			newUsrConfPwd.setStyle(warningStyle);
			newValidConfPassLbl.setText("Passwords must match");
		}
		
		// creates new user if the password is valid and user name is unique
		if (!password1.isEmpty() && password1.equals(password2) && nameIsUnique){
			GuiBasedApp.newUser(name, password1, email);
			
			System.out.println(name + " has created a new account");
		}
	}
}
