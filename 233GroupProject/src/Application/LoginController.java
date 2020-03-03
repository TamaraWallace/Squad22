package Application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
	private Button newUsr;
	
	@FXML
	private Button lgnButton;
	
		
	@FXML
	public void login(ActionEvent event) {
			
		String usrName = lgnName.getText();
		
		String usrPassword = lgnPassword.getText();
		
		System.out.println("login ");	
		
		
		
	}
	@FXML
	public void newUser(ActionEvent event) {
			
		System.out.println("New user everyone");	
		
		
	}
	
	
	
}
