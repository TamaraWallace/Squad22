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
	private Button backBtn,completeBtn,selectBtn;

	// list of tasks
	private ObservableList<String> lstTasks = FXCollections.observableArrayList();
	
	// initialize the list view in the display tasks scene
	// this is done by iterating through users active tasks and adding each task to list view
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Display Tasks Scene");
		
		initializeListView();
	}
	
	//Method Purpose: 	Populates the ListView with all the User's currently active tasks.
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	private void initializeListView() {
		lstViewTasks.setStyle("-fx-background-color: #000B38;");
		
		for (String display : GuiBasedApp.getActiveTasks()) {
			lstTasks.add(display);
		}
		lstViewTasks.getItems().addAll(lstTasks);
		lstViewTasks.setCellFactory(param -> new TaskCell());
		lstViewTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	
	// ----------------------- EVENT HANDLERS -----------------------
	
	
	//Method Purpose: 	Handler for backBtn. Launches the Home Screen Scene
	//Parameters: 		ActionEvent event
	//Return Value: 	Void	
	@FXML
	public void back(ActionEvent event) {
		GuiBasedApp.launchHomeScreenScene();
	}
	
	//Method Purpose: 	Handler for completeBtn. Marks all selected Tasks in the ListView as complete
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	//Functionality:	Completes all tasks that are selected in the ListView, removes them from the ListView.
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
	
	//Method Purpose: 	Handler for selectBtn. Launches the Task Menu Scene with given Task
	//Parameters: 		ActionEvent event
	//Return Value: 	Void
	//Functionality:	If a Task is selected in the ListView, updates GuiBasedApp's Selected Task to the
	//					one selected in the ListView and then launches the Task Menu Scene.
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
