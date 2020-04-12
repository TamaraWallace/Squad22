package Application;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Task;

public class HomeScreenController implements Initializable {
	// variables 
	@FXML
	private RadioButton mostRecentTask;
	
	@FXML
	private RadioButton secondRecentTask;
	
	@FXML
	private RadioButton thirdRecentTask;
	
	@FXML
	private Button viewAllbtn;
	
	@FXML
	private MenuButton profileMenu;
	
	@FXML
	private MenuItem settingsItem,logoutItem,closeItem;
	
	@FXML
	private Button addTaskBtn;
	
	@FXML 
	private Label helloUser, currentTasksTitle,noTasksLbl, numTotal, percentageComplete;
	
	@FXML
	private ListView<String> lstViewTasks;	
	
	@FXML
	private ProgressBar pb = new ProgressBar();
	
	// list of tasks
	private ObservableList<String> lstTasks = FXCollections.observableArrayList();
	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("\nHome Screen Scene");
		
		String usrName = GuiBasedApp.getUser().getUsrName();
		helloUser.setText("Hello, " + usrName+"!");
		
		int total = GuiBasedApp.getTotalTasks();
		numTotal.setText(""+total);
		percentageComplete.setText((int)(GuiBasedApp.getPercentageComplete()*100)+"%");
		
		pb.setStyle("-fx-accent: #6ac4ab;"); 
		pb.setProgress(GuiBasedApp.getPercentageComplete());
		
		lstViewTasks.setStyle("-fx-background-color: #000B38;");
		
		int count = 0;
		for (Task task : GuiBasedApp.getActiveTasks()) {
	
			System.out.println(task.toString()+"\n");
		
			String display;
			String taskId = task.getTaskID().toString();
			
			display = taskId +","+"Task: "+ task.getName()+ "\nNotes: " +task.getNotes() + "\nDue: "+ task.getDueDate().toString();
			
			if (count < 3) {
				lstTasks.add(display);
				count++;
			}else {
				break;
			}
		}
		lstViewTasks.getItems().addAll(lstTasks);
		
		lstViewTasks.setCellFactory(param -> new TaskCell());
		
		lstViewTasks.getSelectionModel().selectedItemProperty().addListener( (v,oldV,newV)-> {
			selectedCell(v,oldV,newV);
		});
		
		
		if(count == 0) {
			noTasksLbl.setText("You have no tasks.");
		}else {
			noTasksLbl.setVisible(false);
		}
		
		
	}	
	
	private void selectedCell(ObservableValue<? extends String> observable,  String oldValue, String newValue) {
		
		System.out.println("SelectedTask: " + newValue);
		String taskID = newValue.split(",")[0];
		
		GuiBasedApp.setSelectedTaskByID(taskID);
		
		GuiBasedApp.launchTaskMenuScene();
	}


	
	
	@FXML
	public void viewAll(ActionEvent event) {
		GuiBasedApp.launchDisplayTasksScene();
	}


	@FXML
	public void addTask(ActionEvent event) {
		GuiBasedApp.launchAddTaskScene();
	}
	
	/*
	 * Method: Logs user out of app and returns to login screen
	 * */
	@FXML
	public void logout(ActionEvent event) {
		GuiBasedApp.save();
		GuiBasedApp.launchLoginScene();
	}
	
	/*
	 * Method: Logs user out of app and closes the app
	 * */
	@FXML
	public void close(ActionEvent event) {
		GuiBasedApp.save();	
		Stage window = (Stage) profileMenu.getScene().getWindow();
		window.close();
	}
	
	/*
	 * Method: opens new scene where user can change settings
	 * */
	@FXML
	public void settings(ActionEvent event) {
		GuiBasedApp.launchSettingsScene();
	}
}

