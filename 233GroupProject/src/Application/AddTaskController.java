package Application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Task;

public class AddTaskController implements Initializable{
	
		@FXML 
		private TextField name,notes;
		@FXML
		private DatePicker date;
		
		@FXML 
		private Label dateLabel,nameLabel;
		
		@FXML
		private Button backBtn;
		
		@FXML
		private ImageView logo,backBtnImg,nameNotesIcon,penIcon,calenderIcon,addBtnImg;
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			System.out.println("Add Task Scene");
			
			// Initialize the images for scene
			backBtnImg.setImage(new Image("Back.png"));
			logo.setImage(new Image("logo.jpeg"));
			nameNotesIcon.setImage(new Image("NameNotesIcon.png"));
			penIcon.setImage(new Image("Pen Icon.png"));
			calenderIcon.setImage(new Image("CalenderIcon.png"));
			addBtnImg.setImage(new Image("addBTN.jpg"));
			
			
			
			
			
		}
		
		@FXML
		public void addButton(ActionEvent event) {
			// get name & notes as strings
			String taskName = name.getText();
			String taskNotes = notes.getText();
			// validating taskName is not empty
			if (taskName.isEmpty()) {
				nameLabel.setText("Must have a name!");
				taskName = name.getText();
			} else {
				nameLabel.setText("");
			}			
			// validating the taskDate is not empty 
			LocalDate taskDate = date.getValue();
			if (taskDate == null) {
				dateLabel.setText("Must provide a due date!");
			} else {
				dateLabel.setText("");
			}
			// if everything is good, make a task:
			if (taskDate != null && !taskName.isEmpty()) {
				Task task = new Task(GuiBasedApp.getUser().getUsrID(), taskName, taskNotes, false, taskDate);
	
				GuiBasedApp.addTask(task);
				GuiBasedApp.getTasks().sortTasks();

				System.out.println("New task created:\n" + task.toString());
				
				// launch the Home Screen Scene
				GuiBasedApp.launchHomeScreenScene();
			}
		}
		
		// back button 
		@FXML
		public void back(ActionEvent event) {
			GuiBasedApp.launchHomeScreenScene();
		}
}