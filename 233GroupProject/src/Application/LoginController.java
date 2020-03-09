package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import main.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	private int attempts =0;
	
	@FXML
	private TextField lgnName;
	private String lgnNameStyle = null; 
	
	@FXML
	private PasswordField lgnPassword;
	
	@FXML
	private Button newUsr;
	
	@FXML
	private Button lgnButton;
	
	@FXML
	private Label lgnValidPassLbl;
	
	@FXML
	private Label lgnValidUsrLbl;
	
	private String lgnUsrName = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lgnNameStyle = lgnName.getStyle();
		
	}
		
	@FXML
	public void login(ActionEvent event) throws IOException {
		
		
		
		String usrName = lgnName.getText();
		
		String usrPassword = lgnPassword.getText();
		
		boolean loggedIn = false;
		
		//System.out.println("Trying to Log you in:");
		
		if (GuiBasedApp.getUsers().findUser(usrName) == null) {
			
			lgnName.setStyle(lgnNameStyle + ("-fx-border-color: #ff0000; -fx-border-width: 5px; "));
			attempts = 0;
			
			lgnValidUsrLbl.setText("Not a valid Username");
			
		}else {
			lgnName.setStyle(lgnNameStyle);
			lgnValidUsrLbl.setText("");
		}
		
		if (attempts <3) {
			
			//System.out.println("login text based");
			String userID = TextBasedApp.login(usrName,usrPassword);
			System.out.println("userID: "+userID);
			if (userID == null) {
				attempts ++;
				
				String style = lgnPassword.getStyle();
				lgnPassword.setStyle(style + ("-fx-border-color: #ff0000; -fx-border-width: 5px; "));
				
				lgnValidPassLbl.setText("Not a valid Password");
				
			} else {
				attempts = 0;
				User user = GuiBasedApp.getUsers().getUser(userID);
				
				GuiBasedApp.setUser(user);				
				GuiBasedApp.setUserID(userID);
				TaskCollection tasks = TaskCollection.loadUsrTasks("tasks.txt", userID);
				
				GuiBasedApp.setTasks(tasks);
				
				GuiBasedApp.setLgnUserName(usrName);
				
				loggedIn = true;
			}
		}else if (attempts == 3) {
			
			Alert alert = new Alert(AlertType.WARNING);
			DialogPane dialogPane = alert.getDialogPane();
						
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			//dialogPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID ,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
			alert.setTitle("Warning");
			alert.setHeaderText(attempts+" Password Attempts");
			alert.setContentText("Last attempt or the program will close");
		
			alert.showAndWait();
			attempts++;
			
		}else {
			
			String userID = TextBasedApp.login(usrName,usrPassword);
			System.out.println("userID: "+userID);
			
			if (userID == null) {
			Alert alert = new Alert(AlertType.ERROR);
			DialogPane dialogPane = alert.getDialogPane();
						
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			//dialogPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID ,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
			dialogPane.setStyle("-fx-border-color: red;");
			alert.setTitle("Warning");
			alert.setHeaderText(attempts+" Password Attempts!! TOO MANY!");
			alert.setContentText("Program will close !");
			
			//Stage window = (Stage) (dialogPane.getScene().getWindow());
			
			alert.showAndWait();
			System.exit(0);
			}else {
				attempts = 0;
				User user = GuiBasedApp.getUsers().getUser(userID);
				
				GuiBasedApp.setUser(user);				
				GuiBasedApp.setUserID(userID);
				TaskCollection tasks = TaskCollection.loadUsrTasks("tasks.txt", userID);
				
				GuiBasedApp.setTasks(tasks);
				
				GuiBasedApp.setLgnUserName(usrName);
				
				loggedIn = true;
			}
		}
		
		
		
		if (loggedIn) {
		
			
			System.out.println("Logged In: ");
			
			GuiBasedApp.getTasks().display();
			System.out.println("Displayed user tasks above ^^^^");
			System.out.println();
			
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
			
			Scene HomeScreenScene = new Scene(pane);
			
			HomeScreenScene.getStylesheets().add(getClass().getResource("HomeScreen.css").toExternalForm());
			
			GuiBasedApp.setPrevScene(window.getScene());
			window.hide();
			window.setScene(HomeScreenScene);
			System.out.println("changed scenes");
			window.show();
			System.out.println("showed");
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
	
	@FXML
	public void lgnNameKeyPressed( KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			lgnPassword.requestFocus();
			
		}else {
			//System.out.println(event.getCode());
		}
	}
	@FXML
	public void lgnPasswordKeyPressed( KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			lgnButton.fire();
			
		}
	}
	@FXML
	public void anchorPane( KeyEvent event) {
		// nothing yet
	}
	
	@FXML
	public void mouseEv(MouseEvent event) {
		
		//System.out.println("track mouse: "+ event.getEventType());
	}

	
	
	
}
