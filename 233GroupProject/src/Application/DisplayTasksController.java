package Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import main.Task;

public class DisplayTasksController implements Initializable{
	
	
	@FXML
	private ListView<String> lstViewTasks;
	
	@FXML
	private Button backBtn;
	
	@FXML
	private Button completeBtn,selectBtn;

	// list of tasks
	private ObservableList<String> lstTasks = FXCollections.observableArrayList();
	
	// initialize the list view in the display tasks scene
	// this is done by iterating through users active tasks and adding each task to list view
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("\nDisplay Tasks Screen");
		
		lstViewTasks.setStyle("-fx-background-color: #000B38;");
		
		for (Task task : GuiBasedApp.getActiveTasks()) {
	
			System.out.println(task.toString()+"\n");
		
			String display;
			String taskId = task.getTaskID().toString();
			
			display = taskId +","+"Task: "+ task.getName()+ "\nNotes: " +task.getNotes() + "\nDue: "+ task.getDueDate().toString();
			
			lstTasks.add(display);
			
		}
		lstViewTasks.getItems().addAll(lstTasks);
		
		
		lstViewTasks.setCellFactory(param -> new TaskCell());
		
		lstViewTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	// Brings user back to the Home Screen Scene
	@FXML
	public void back(ActionEvent event) {
		GuiBasedApp.launchHomeScreenScene();
	}
	
	// method for completing a user's task
	// runs when complete button is pressed and completes all selected tasks
	@FXML
	public void completeTask(ActionEvent event) {
		ObservableList<String> completeTasks = FXCollections.observableArrayList();
		completeTasks = lstViewTasks.getSelectionModel().getSelectedItems();
		
		for (int i = 0; i < completeTasks.size(); i++) {
			String lstViewTask = completeTasks.get(i);
			String taskID = lstViewTask.split(",")[0];
		
			System.out.println("TaskID: "+taskID);
			
			GuiBasedApp.completeTaskByID(taskID);
			
		}
		lstViewTasks.getItems().removeAll(completeTasks);
	}
	
	// method for selecting a task
	// runs when select button pressed
	// takes user to task scene for selected task
	@FXML
	public void selectTask(ActionEvent event) throws IOException {
		String selectTask = lstViewTasks.getSelectionModel().getSelectedItem();
		if (selectTask != null) {
			String taskID = selectTask.split(",")[0];
			
			GuiBasedApp.setSelectedTaskByID(taskID);
			
			GuiBasedApp.launchTaskMenuScene();
		}
	}
	
	
	
}
