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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.User;

public class CreateUserController implements Initializable {
	
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
	
	@FXML
	private ImageView backBtnImg,  cerateUserBtnImg,  taskillaLogo;

	private String newUsrNameStyle = "-fx-background-color: #b3c2ff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;

	private String newUsrPwdStyle = "-fx-background-color: #b3c2ff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;

	private String newUsrConfPwdStyle = "-fx-background-color: #b3c2ff; -fx-border-color: #76d0aa; -fx-border-width: 4;" ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("\nCreate User Scene");
		
		// Initialize the images for scene
		backBtnImg.setImage(new Image("Back.png"));
		cerateUserBtnImg.setImage(new Image("PlusIcon.png"));
		taskillaLogo.setImage(new Image("taskilla_logo.jpg"));
	}
	
	@FXML
	public void back(ActionEvent event) throws IOException {
		GuiBasedApp.launchLoginScene();
	}
	
	@FXML
	public void createUsr(ActionEvent event) throws IOException {
		
		String name = newUsrName.getText();
		String email = usrEmail.getText();
		String password1 = newUsrPwd.getText();
		String password2 = newUsrConfPwd.getText();
		
		boolean nameIsUnique = !(GuiBasedApp.getUsers().doesUsernameExist(name));
		
		// more robust user name checking.	
		if (name.isEmpty()){
			newUsrName.setStyle(newUsrNameStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
			newValidUsrLbl.setText("Username can't be empty");
		} else if (!nameIsUnique) {
			
			newUsrName.setStyle(newUsrNameStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
			newValidUsrLbl.setText("Username already exists!");
		} else {
			newUsrName.setStyle(newUsrNameStyle);
		}
		
		// more robust password checking
		if(password1.isEmpty()) {
			System.out.println("running p1 empty ");
		
			newUsrPwd.setStyle(newUsrPwdStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
		}
		if(password2.isEmpty()) {
			System.out.println("running p2 empty ");
	
			newUsrConfPwd.setStyle(newUsrConfPwdStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
		}
		
		if(!password1.equals(password2)) {
			System.out.println("running pass missmatch ");

			newUsrConfPwd.setStyle(newUsrConfPwdStyle + "-fx-border-color: #ff0000; -fx-border-width: 5px; ");
		
			newValidConfPassLbl.setText("Passwords must match");
		}
		
		// creates new user if the password is valid and user name is unique
		if (!password1.isEmpty() && password1.equals(password2) && nameIsUnique){
			newUsrConfPwd.setStyle(newUsrConfPwdStyle);
			newUsrPwd.setStyle(newUsrPwdStyle);
			GuiBasedApp.newUser(new User(name, password1, email));
			
			System.out.println(name + " has created a new account");
			System.out.println(GuiBasedApp.getUser().toString());
			
			GuiBasedApp.launchHomeScreenScene();
		}
	}
	 
	
}
