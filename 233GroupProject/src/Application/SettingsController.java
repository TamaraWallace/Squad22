package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Email;
import main.User;
import javafx.fxml.Initializable;

public class SettingsController implements Initializable {

	@FXML 
	private TextField userName,email; //associated with text object for task name & notes in FXML
	
	@FXML
	private Button backBtn ,updateBtn; //associated with the back button object in FXML
	
	@FXML
	private Label validUserNameLbl, validEmailLbl;
	
	@FXML
	private ImageView taskilla_logoImg, BackImg, userIconImg, MailIconImage, updateBtnImg;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("\nSettings Scene");
		
		userName.setText(GuiBasedApp.getUser().getUsrName());
		email.setText(GuiBasedApp.getUser().getUsrEmail());
		
		
		// Initialize all the images on scene
		taskilla_logoImg.setImage(new Image("taskilla_logo.jpg"));
		BackImg.setImage(new Image("Back.png"));
		userIconImg.setImage(new Image("User Icon.png"));
		MailIconImage.setImage(new Image("Mail Icon.png"));
		updateBtnImg.setImage(new Image("updateBTN.jpg"));
		
		
	}
	
	// method for returning to previous scene
	@FXML
	public void back(ActionEvent event) {
		GuiBasedApp.launchHomeScreenScene();
	}
	
	// method for returning to previous scene
	@FXML
	public void update(ActionEvent event) throws IOException {
		
		boolean validUsrName = false;
		boolean validEmail = false;
		User user = GuiBasedApp.getUser();
		
		String newUsrName =userName.getText();
		String newUsrEmail = email.getText();
		
		
		if( !(GuiBasedApp.getUsers().doesUsernameExist(newUsrName)) && newUsrName.length() != 0) {
			validUsrName = true;
			
		}else {
			validUserNameLbl.setText("Invalid Username ");
		}
		
		validEmail =  Email.validateEmaill(newUsrEmail);
		
		if 	(!validEmail) {
			validEmailLbl.setText("Invalid Email: "+newUsrEmail);
		}
		
		if (validEmail && validUsrName) {
			user.setUsrName(newUsrName);
			user.setUsrEmail(newUsrEmail);
			
			GuiBasedApp.launchHomeScreenScene();
		}
	}
}
