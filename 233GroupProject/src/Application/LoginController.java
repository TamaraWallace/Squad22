package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController implements Initializable{
	
	private int attempts;
	
	@FXML
	private TextField lgnName;
	
	@FXML
	private PasswordField lgnPassword;
	
	@FXML
	private Button newUsr, lgnButton;
	
	@FXML
	private Label lgnValidPassLbl, lgnValidUsrLbl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Login Scene");
		// set the number of password attempts to 0
		attempts = 0;
	}
	
	// ----------------------- EVENT HANDLERS -----------------------
	
	
	//Method Purpose: 	Handler for the Login Button. Attempts to log in a user with the provided credentials.
	//Parameters:		ActionEvent event
	//Return Value: 	Void
	//Functionality:	Calls GuiBasedApp.validateUsernameAndPassword(usrName,usrPassword) with the values of the
	//					lgnName and lgnPassword text fields. If they are valid, logs that user in. Otherwise, notifies
	//					the user that they have entered invalid credentials. After 3 incorrect attempts, warns user that
	//					they have 1 attempt remaining. After a 4th incorrect attempt, closes the program.
	@FXML
	public void login(ActionEvent event) throws IOException {
		String usrName = lgnName.getText();
		String usrPassword = lgnPassword.getText();
		
		attempts++;
		System.out.println("Login Attempt: " + attempts);
		
		// Checks if a User with the provided usrName exists. If so, checks if the provided usrPassword matches.
		boolean valid = GuiBasedApp.validateUsernameAndPassword(usrName,usrPassword);
		
		if (valid) {
			// Logs user in if credentials are valid
			GuiBasedApp.loginUser(usrName);
		} else {
			// Notify user that they have input invalid login credentials
			System.out.println("Invalid login credentials");
			String style = lgnPassword.getStyle();
			lgnPassword.setStyle(style + ("-fx-border-color: #ff0000; -fx-border-width: 5px; "));
			lgnPassword.setText("");
			lgnValidPassLbl.setText("Invalid login credentials");
			
			if (attempts == 3) {
				// Warn user if they have had 3 incorrect attempts
				Alert alert = new Alert(AlertType.WARNING);
				DialogPane dialogPane = alert.getDialogPane();			
				dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
				
				alert.setTitle("Warning");
				alert.setHeaderText(attempts+" Password Attempts");
				alert.setContentText("Last attempt or the program will close");
				alert.showAndWait();
			} else if (attempts > 3) {
				// Close program if user has exceeded 3 incorrect attempts.
				Alert alert = new Alert(AlertType.ERROR);
				DialogPane dialogPane = alert.getDialogPane();	
				dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
				dialogPane.setStyle("-fx-border-color: red;");
				alert.setTitle("Warning");
				alert.setHeaderText(attempts+" Password Attempts!! TOO MANY!");
				alert.setContentText("Program will close !");
				alert.showAndWait();
				System.exit(0);
			}
		}
	}
	
	//Method Purpose: Handler for newUsr button. Brings user to the Create New User Scene.
	//Parameters: ActionEvent event
	//Return Value: Void
	@FXML
	public void newUser(ActionEvent event) throws IOException {
		GuiBasedApp.launchCreateUserScene();
	}
	
	//Method Purpose:	Handler for KeyEvents in the lgnName TextField.
	//Parameters:		KeyEvent event
	//Return Value:		Void
	//Functionality:	If KeyCode is the ENTER key, brings focus to the next TextField, lgnPassword.
	@FXML
	public void lgnNameKeyPressed( KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			lgnPassword.requestFocus();
		}
	}
	
	//Method Purpose:	Handler for KeyEvents in the lgnName TextField.
	//Parameters:		KeyEvent event
	//Return Value:		Void
	//Functionality:	If KeyCode is the ENTER key, fires the lgnButton.
	@FXML
	public void lgnPasswordKeyPressed( KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			lgnButton.fire();		
		}
	}
}