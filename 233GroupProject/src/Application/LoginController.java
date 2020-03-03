package Application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
	public void newUser(ActionEvent event) throws IOException{
			
		System.out.println("New user everyone");
		
		
		
		//Parent pane = FXMLLoader.load(new GuiBasedApp().getURL("CreateUser.fxml"));
		
		//Scene newUserScene = new Scene(pane);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
		
		window.setScene(new Scene(pane));
		window.show();

		
		
	}
	
	
	
}
