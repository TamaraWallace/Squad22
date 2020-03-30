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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
		
		
		
		lstViewTasks.setCellFactory(param -> new Cell());
		
		lstViewTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
	}
	
	/*
	 * Create a static class for each cell in the lisView
	 * */
	class Cell extends ListCell<String>{
		
		// Initializing all the widgets for displaying the task
		HBox hb = new HBox();
		
		Button close = new Button(); 
		Label taskLbl = new Label("");
		Image delIcon  = new Image(getClass().getResourceAsStream("/deleteIcon.png"));
		Pane pane = new Pane();
		ImageView delete = new ImageView(delIcon);

		private String taskId;
		
		
		
		public Cell() {
			
			super();		
			
			// setting the size of trash bin button
			delete.setFitWidth(30);
			delete.setFitHeight(35);
			hb.getStylesheets().add(getClass().getResource("DisplayTasks.css").toExternalForm());
			
			taskId = "";
			
			//setting the widgets to the proper color
			taskLbl.setStyle("-fx-background-color: #000B38; -fx-font-weight:bold; ");
			taskLbl.setTextFill(Color.web("#24a78d"));
			close.setStyle("-fx-background-color: #000B38;");
			close.setGraphic(delete);

			hb.setStyle("-fx-background-color: #000B38;");
			
			// adding all the components in the sequence in which we want them to appear
			hb.getChildren().addAll(taskLbl,pane,close);
			HBox.setHgrow(pane, Priority.ALWAYS);
			
			//removing the task from list view when trash bin pressed
			close.setOnAction(new EventHandler<ActionEvent>() {		
				
				@Override
				public void handle(ActionEvent event) {
					
					getListView().getItems().remove(getItem());
					System.out.println("TaskID: "+taskId);
					deleteTask(taskId,taskLbl.getText());
				}

				
					
				
			});
			
			
		}
		
		// Updates the list item in the list view with the Task details
		// this is done by setting the text in label to the task details
		@Override
		public void updateItem(String name, boolean empty) {
			super.updateItem(name, empty);
			setText(null);
			setGraphic(null);
			
			if (name != null && !empty) {
				//System.out.println(name);
				String[] task = name.split(",");
				
				taskId = task[0];
				String text = "";
				for (int i = 1; i < task.length; i++) {
					text += task[i];
				}
				taskLbl.setText(text);
				setGraphic(hb);
				
			}
		}
	}
	

	
	// method for deleting a users tasks
	//currently only removes task from list view, does not update user's tasks
	public static void deleteTask(String taskId, String taskName) {
		System.out.println("Deleting task ID: "+taskId+" Details: "+taskName);
		GuiBasedApp.getTasks().getTaskByID(UUID.fromString(taskId)).delete();
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
		lstViewTasks.getItems().removeAll(completeTasks);
		
		for (int i = 0; i < completeTasks.size(); i++) {
			String lstViewTask = completeTasks.get(i);
			String taskId = lstViewTask.split(",")[0];
		
			System.out.println("TaskID: "+taskId);
			
			for (Task task : GuiBasedApp.getActiveTasks()) {
				if (task.getTaskID().toString() == taskId) {
					task.complete();
				}
			}
			
			
		}
	}
	
	// method for selecting a task
	// runs when select button pressed
	// takes user to task scene for selected task
	@FXML
	public void selectTask(ActionEvent event) throws IOException {
		
		String selectTask = lstViewTasks.getSelectionModel().getSelectedItem();
		String taskId = selectTask.split(",")[0];
		
		Task selectedTask = GuiBasedApp.getTasks().getTaskByID(UUID.fromString(taskId));
		TaskMenuController.setSelectedTask(selectedTask);
		
		System.out.println(selectedTask.toString());
		
		GuiBasedApp.launchTaskMenuScene();
	}
	
	
	
}
