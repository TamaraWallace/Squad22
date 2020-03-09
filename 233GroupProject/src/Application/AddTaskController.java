package Application;

import java.net.URL;
import main.User;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Task;
import main.TaskCollection;

public class AddTaskController  implements Initializable{
	
		@FXML 
		private TextField name,notes;
		@FXML
		private DatePicker date;
		
		@FXML 
		private Label dateLabel,nameLabel;
		
		@FXML
		private Button backBtn;
		
		TaskCollection tasks = new TaskCollection();
		
		@FXML
		public void addButton() {
			
			try {
				// get name & notes as strings
				String taskName = name.getText();
				String taskNotes = notes.getText();
				System.out.println(taskName+taskNotes);
				// validating taskName is not empty
				if (taskName.isEmpty()) {
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
				
				
				// if everything is good, make a task:
				if ( taskDate != null && !taskName.isEmpty()) {
				UUID userID = GuiBasedApp.getUser().getUsrID();
				Task task = new Task(userID, taskName, taskNotes, false, taskDate);
				System.out.println("New task created:\n" + task.toString());
				
				GuiBasedApp.addTask(task);
				// clearing out textfield boxes for name & notes 
				if (task != null) {
					name.clear();
					notes.clear();
				}
		
				// clear out the date picker as it doesn't do this automatically 
				date.setValue(null);
				
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
		
		@FXML
		public void back(ActionEvent event) {
			
			GuiBasedApp.save();
			
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			window.setScene(GuiBasedApp.getPrevScene());
			
		}
}