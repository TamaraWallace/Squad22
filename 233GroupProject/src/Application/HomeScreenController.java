package Application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class HomeScreenController {
	
	@FXML
	private RadioButton mostRecentTask;
	
	@FXML
	private RadioButton secondRecentTask;
	
	@FXML
	private RadioButton thirdRecentTask;
	
	@FXML
	private Button viewAll;
	
	@FXML
	private Button profileButton;
	
	@FXML
	private Button addTask;
	
	@FXML 
	private Label helloUser, currentTasksTitle, viewAllLabel;
	
	public HomeScreenController() {
		
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
	}
	
	
	public void mostRecentTask() {
		
	}
	
	public void secondRecentTask() {
		
	}
	
	public void thirdRecentTask() {
		
	}
	
	//do we need another scene for view all or direct to Task Menu? 
	public void viewAll() {
		
	}

	
	public void addTask() {
		//https://www.youtube.com/watch?v=LDVztNtJWOo 
	}
	
	
	//can add later for demo 3 
	public void profileButton() {
		
	}

	
	
	
}

