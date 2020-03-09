package Application;

import java.io.IOException;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.TaskCollection;
import main.TextBasedApp;
import main.User;

public class CreateUserController {
	
	@FXML
	private Button backBtn;
	
	@FXML
	private Button createUsrBtn;
	
	@FXML
	private PasswordField newUsrPwd;
	
	@FXML
	private PasswordField newUsrConfPwd;
	
	@FXML
	private TextField usrEmail;
	
	@FXML
	private TextField newUsrName;
	
	@FXML
	private Label newValidEmailLbl;
	
	@FXML
	private Label newValidPassLbl;
	
	@FXML
	private Label newValidConfPassLbl;
	
	@FXML
	private Label newValidUsrLbl;

	private String newUsrNameStyle = "-fx-background-color: #b3c2ff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;

	private String newUsrPwdStyle = "-fx-background-color: #b3c2ff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;

	private String newUsrConfPwdStyle = "-fx-background-color: #b3c2ff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;
	
	
	@FXML
	public void back(ActionEvent event) {
				
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(GuiBasedApp.getPrevScene());
		
	}
	
	
	
	@FXML
	public void createUsr(ActionEvent event) throws IOException {
		
		String name = newUsrName.getText();
		String email = usrEmail.getText();
		String password1 = newUsrPwd.getText();
		String password2 = newUsrConfPwd.getText();
		
		System.out.println(name +" "+ email +" "+ password1+" "+ password2);
		
		System.out.println(password1.isEmpty() +" "+ password1.isBlank());
		
		boolean validName;
		boolean validPass;
		boolean strngPass;
		

		if (name.isEmpty()){
			newUsrName.setStyle(newUsrNameStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
			newValidUsrLbl.setText("Invalid Username");
			
		}else if (!(GuiBasedApp.getUsers().findUser(name) == null)) {
			
			//newUsrNameStyle = newUsrName.getStyle();
			newUsrName.setStyle(newUsrNameStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
			
			Alert alert = new Alert(AlertType.WARNING);
			DialogPane dialogPane = alert.getDialogPane();
						
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			//dialogPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID ,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
			alert.setTitle("Warning");
			alert.setHeaderText("Username already exists");
			alert.setContentText("Please enter a different username");
			alert.showAndWait();
			
		}else {
			newUsrName.setStyle(newUsrNameStyle);
		}
		
		if(password1.isEmpty()) {
			System.out.println("running p1 empty ");
			//newUsrPwdStyle = newUsrPwd.getStyle();
			newUsrPwd.setStyle(newUsrPwdStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
			
		}
		if(password2.isEmpty()) {
			System.out.println("running p2 empty ");
			//newUsrConfPwdStyle = newUsrConfPwd.getStyle();
			newUsrConfPwd.setStyle(newUsrConfPwdStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
			
		}
		
		if(!password1.equals(password2)) {
			System.out.println("running pass missmatch ");
			//newUsrPwd.setStyle(newUsrPwdStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
			newUsrConfPwd.setStyle(newUsrConfPwdStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");

			Alert alert = new Alert(AlertType.ERROR);
			DialogPane dialogPane = alert.getDialogPane();
						
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			//dialogPane.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID ,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
			alert.setTitle("Error");
			alert.setHeaderText("Password mismatch !!");
			alert.setContentText("The passwords entered do not match eachother");
			alert.showAndWait();
		}
		
		
		if (!password1.isEmpty() && !password2.isEmpty() && password1.equals(password2)){
			newUsrConfPwd.setStyle(newUsrConfPwdStyle);
			newUsrPwd.setStyle(newUsrPwdStyle);
			
			String userID = TextBasedApp.createNewUser(name, email, password1, password2);
			
			User user = GuiBasedApp.getUsers().getUser(userID);
			
			GuiBasedApp.setUser(user);				
			GuiBasedApp.setUserID(userID);
			TaskCollection tasks = TaskCollection.loadUsrTasks("tasks.txt", userID);
			
			GuiBasedApp.setTasks(tasks);
			
			GuiBasedApp.setLgnUserName(name);
			
			System.out.println("Created");
			
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
		
					
		//-fx-background-color: #b3c2ff;
		
		
		
		
		
		
	}
	 
	
}
