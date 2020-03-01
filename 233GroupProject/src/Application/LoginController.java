package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField lgnName;
	
	@FXML
	private PasswordField lgnPassword;
	
	@FXML
	private Label newUsr;
	
	@FXML
	private Button lgnButton;
	
	@SuppressWarnings("unused")
	public void login(ActionEvent event) {
			
		System.out.println(lgnName.getText());
		System.out.println(lgnPassword.getText());
		
		
		if(newUsr.getText() == "Logged in") {
			newUsr.setText("Already pressed");
		}else {
			newUsr.setText("Logged in");
			
		}
		
	}
	
	
}
