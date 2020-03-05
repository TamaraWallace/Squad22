package Application;

import java.net.URL;

import javafx.fxml.FXML;

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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
	}
	
	
	public void mostRecentTask() {
		//setText of radio button to most current task 
		//when clicked, task.setCompleted(true) 
	}
	
	public void secondRecentTask() {
		//setText of radio button to second current task 
		//when clicked, task.setCompleted(true) 
	}
	
	public void thirdRecentTask() {
		//setText of radio button to third current task 
		//when clicked, task.setCompleted(true) 
	}
	
	//do we need another scene for view all or direct to Task Menu? 
	public void viewAll() {
		
	}
	
	//how do we link scenes and get this button to direct it to AddTask scene? 
	public void addTask() {
		//https://www.youtube.com/watch?v=LDVztNtJWOo 
	}
	
	
	//can add later for demo 3 
	public void profileButton() {
		
	}

	
	
	
}

