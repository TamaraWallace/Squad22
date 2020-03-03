package Application;

import java.io.IOException;
import java.util.UUID;

import main.*;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	
	private int attempts =0;
	
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
		
		System.out.println("Login::");
		if (attempts <3) {

			String usrName = lgnName.getText();
			
			String usrPassword = lgnPassword.getText();
			
			System.out.println("login text based");
			UUID userID = TextBasedApp.login(usrName,usrPassword);
			
			if (userID == null) {
				attempts ++;
			} else {
				attempts = 0;
				GuiBasedApp.setUserID(userID);
			}
		}else if (attempts == 3) {
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("You have attempted to enter the password "+attempts+" times");
			alert.setContentText("you have one last attempt or the program will close");
			alert.showAndWait();
			attempts++;
		}else {
			System.exit(0);
		}
		
		
	}
	
	@FXML
	public void newUser(ActionEvent event) throws IOException{
			
		//System.out.println("New user everyone");
		
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
		
		Scene createUserScene = new Scene(pane);
		
		createUserScene.getStylesheets().add(getClass().getResource("CreateUser.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(createUserScene);
		window.show();

		
		
	}
	
	
	
}
