package Application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("CreateUser.css").toExternalForm());
		GuiBasedApp.setScene(scene);
		
	}
	
	
	
}
