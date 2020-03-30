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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Task;

public class HomeScreenController implements Initializable {
	
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
	private MenuItem settingsItem,LogOutItem ,closeItem;
	
	@FXML
	private Button addTaskBtn;
	
	@FXML 
	private Label helloUser, currentTasksTitle,noTasksLbl;
	
	@FXML
	private Button emailBtn;
	
	@FXML
	private ListView<String> lstViewTasks;	
	
	// list of tasks
	private ObservableList<String> lstTasks = FXCollections.observableArrayList();
	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("\nHome Screen Scene");
		
		String usrName = GuiBasedApp.getUser().getUsrName();
		helloUser.setText("Hello, " + usrName+"!");
		
		
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
		
		lstViewTasks.setCellFactory(param -> new Cell());
		
		lstViewTasks.getSelectionModel().selectedItemProperty().addListener( (v,oldV,newV)-> {
			selectedCell(v,oldV,newV);
		});
		
		
		if(count == 0) {
			noTasksLbl.setText("You have no Tasks");
			
			/*Alert alert = new Alert(AlertType.INFORMATION);
			DialogPane dialogPane = alert.getDialogPane();			
			dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
			
			alert.setTitle("No Tasks");
			alert.setHeaderText(" You have no tasks");
			alert.setContentText("Please add a task");
			
			alert.showAndWait();*/
			
			
		}else {
			noTasksLbl.setVisible(false);
		}
		
		
	}
	
	/*
	 * Create a static class for each cell in the lisView
	 * */
	class Cell extends ListCell<String> {
		
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
	
	
	
	
	private void selectedCell(ObservableValue<? extends String> observable,  String oldValue, String newValue) {
		
		System.out.println("SelectedTask: " + newValue);
		String TaskID = newValue.split(",")[0];
		
		TaskMenuController.setSelectedTask((Task) GuiBasedApp.getTasks().getTaskByID(UUID.fromString(TaskID))); 
		
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
	
	@FXML
	public void sendTaskEmail(ActionEvent event) {
		GuiBasedApp.getUser().sendWelcomeEmail();
	}

	
	// method for deleting a users tasks
	//currently only removes task from list view, does not update user's tasks
	public static void deleteTask(String taskId, String taskName) {
		System.out.println("Deleting task ID: "+taskId+" Details: "+taskName);
		GuiBasedApp.getTasks().getTaskByID(UUID.fromString(taskId)).delete();
	}
}

