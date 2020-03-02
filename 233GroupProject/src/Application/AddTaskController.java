package Application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

//import application.Task;
//import application.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddTaskController  implements Initializable{
	
		@FXML 
		private TextField name,notes;
		@FXML
		private DatePicker date;
		
		@FXML 
		private Label dateLabel,nameLabel;
		
		private boolean allGood;
		public void addButton() {
			
			try {
				// get strings
				String taskName = name.getText();
				String taskNotes = notes.getText();
				
				// validating taskName is not empty
				if (taskName.isBlank()) {
					nameLabel.setText("TASK NAME CAN'T BE EMPTY");
					taskName = name.getText();
					
				} else {
					nameLabel.setText("");
				}
				
				// validating the taskDate is not empty 
				LocalDate taskDate = date.getValue();
				if (taskDate == null) {
					dateLabel.setText("TASK DATE CAN'T BE EMPTY");
				} else {
					dateLabel.setText("");
					System.out.println(taskDate);
				}
				
				if (taskDate != null && !taskName.isBlank()){
					allGood = true;
				}
				// if everything is good, make a task: 
				// UNCOMMENT WHEN IN THE SAME FOLDER AS TASKILLA CLASSES !! 
				if (allGood) {
//				UUID userID = User.getUsrID();
//				 Task task = new Task(userID, taskName, taskNotes, false, taskDate);
//				 System.out.println(task.toString());
				// add to some array list??? 
		
				System.out.println(taskName);
				System.out.println(taskNotes);
				
				
				}
				
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

			// TODO Auto-generated method stub
			
	
		}
		public void backButton() {
			// to do when main menu is made 
		}
}
