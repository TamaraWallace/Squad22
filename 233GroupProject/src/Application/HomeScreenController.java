package Application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HomeScreenController implements Initializable {
	
	@FXML
	private MenuButton profileMenu;
	
	@FXML
	private MenuItem settingsItem,logoutItem,closeItem;
	
	@FXML 
	private Label helloUser,noTasksLbl, numTotal, percentageComplete;
	
	@FXML
	private ListView<String> lstViewTasks;
	
	@FXML
	private Button viewAllbtn, addTaskBtn;
	
	@FXML
	private ProgressBar progressBar = new ProgressBar();
	
	// Up to three of a user's tasks as String representations
	private ObservableList<String> lstTasks = FXCollections.observableArrayList();
	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Home Screen Scene");
		String usrName = GuiBasedApp.getUsername();
		helloUser.setText("Hello, " + usrName+"!");
		
		initializeListView();
		
		initializeProgressArea();
	}
	
	//Method Purpose: 	Populates the ListView with all the User's currently active tasks. If there are no active tasks,
	//					Displays message that user has no active tasks
	//Parameters: 		
	//Return Value: 	Void
	private void initializeListView() {
		lstViewTasks.setStyle("-fx-background-color: #000B38;");
		int count = 0;
		for (String display: GuiBasedApp.getActiveTasks()) {
			if (count < 3) {
				lstTasks.add(display);
				count++;
			}else {
				break;
			}
		}
		lstViewTasks.getItems().addAll(lstTasks);
		lstViewTasks.setCellFactory(param -> new TaskCell());
		// Makes the selectCell (handler method) fire whenever a cell is selected.
		lstViewTasks.getSelectionModel().selectedItemProperty().addListener( (v,oldV,newV)-> {
			selectCell(v,oldV,newV);
		});
		
		if(count == 0) {
			noTasksLbl.setText("You have no tasks.");
		} else {
			noTasksLbl.setVisible(false);
		}
	}
	
	//Method Purpose:	Updates the progress bar and feedback area for a user.
	//Parameters:		
	//Return Value:		Void
	//Functionality:	Indicates the total number of tasks a user has, including all complete ones. Displays
	//					the percentage of their total tasks that a user has completed. Generates the progressBar
	//					to reflect the percentage of tasks completed.
	public void initializeProgressArea() {
		int total = GuiBasedApp.getTotalNumOfTasks();
		numTotal.setText(""+total);
		percentageComplete.setText((int)(GuiBasedApp.getPercentageComplete()*100)+"%");
		
		progressBar.setStyle("-fx-accent: #6ac4ab;");
		
		progressBar.setProgress(GuiBasedApp.getPercentageComplete());
	}
	
	
	// ----------------------- EVENT HANDLERS -----------------------
	

	//Method Purpose:	Sets the Selected Task to the one selected from the List View, launches the Task Menu Scene
	//Parameters:		ObservableValue<> observable, String oldvalue, String newValue
	//Return Value:		Void
	//Functionality:	Identifies the selected task based on its UUID. Calls GuiBasedApp.setSelectedTaskByID(taskID)
	//					to update the Selected Task instance variable. Then launches the Task Menu
	private void selectCell(ObservableValue<? extends String> observable,  String oldValue, String newValue) {
		String taskID = newValue.split(",")[0];
		
		GuiBasedApp.setSelectedTaskByID(taskID);
		
		GuiBasedApp.launchTaskMenuScene();
	}

	//Method Purpose: 	Handler for viewAllbtn. Launches the Display Tasks Scene
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	@FXML
	public void viewAll(ActionEvent event) {
		GuiBasedApp.launchDisplayTasksScene();
	}

	//Method Purpose: 	Handler for addTaskBtn. Launches the Add Task Scene
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	@FXML
	public void addTask(ActionEvent event) {
		GuiBasedApp.launchAddTaskScene();
	}
	
	//Method Purpose: 	Handler for logoutItem. Logs the user out.
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	@FXML
	public void logout(ActionEvent event) {
		GuiBasedApp.logout();
	}
	
	//Method Purpose: 	Handler for closeItem. Closes the program.
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	@FXML
	public void close(ActionEvent event) {
		Stage window = (Stage) profileMenu.getScene().getWindow();
		window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
	
	//Method Purpose: 	Handler for settingsItem. Launches the Settings Scene
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	@FXML
	public void settings(ActionEvent event) {
		GuiBasedApp.launchSettingsScene();
	}
}

