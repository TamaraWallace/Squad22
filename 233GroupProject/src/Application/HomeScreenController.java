package Application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Task;
import main.UserCollection;

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
	private Label helloUser, currentTasksTitle;
	
	@FXML
	private Button emailBtn;
	
	@FXML
	private ListView<String> lstViewTasks;	
	
	// list of tasks
	private ObservableList<String> lstTasks = FXCollections.observableArrayList();
	
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
	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		
		String usrName = GuiBasedApp.getUser().getUsrName();
		helloUser.setText("Hello, " + usrName+"!");
		
		
		lstViewTasks.setStyle("-fx-background-color: #000B38;");
		System.out.println("intialize is runnin now");
		
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
		
		System.out.println(count);
		
		lstViewTasks.getItems().addAll(lstTasks);
		
		
		
		lstViewTasks.setCellFactory(param -> new Cell());
		
		
		lstViewTasks.getSelectionModel().selectedItemProperty().addListener( (v,oldV,newV)-> {
			//
			try {
				selectedCell(v,oldV,newV);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		});
		
		
		
	}
	
	
	private void selectedCell(ObservableValue<? extends String> observable,  String oldValue, String newValue) throws IOException {
		
		System.out.println("SelectedTask: "+newValue);
		System.out.println();
		String TaskID = newValue.split(",")[0];
		System.out.println("TaskID: "+TaskID);
		
		
		TaskMenuController.setSelectedTask((Task) GuiBasedApp.getTasks().getTaskByID( UUID.fromString(TaskID))); 
		
		Stage window = (Stage) lstViewTasks.getScene().getWindow();
		
		
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("TaskMenu.fxml"));
		
		Scene viewAllScene = new Scene(pane);
		
		viewAllScene.getStylesheets().add(getClass().getResource("TaskMenu.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(viewAllScene);
		window.show();
	}


	
	
	@FXML
	public void viewAll(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("DisplayTasks.fxml"));
		
		Scene viewAllScene = new Scene(pane);
		
		viewAllScene.getStylesheets().add(getClass().getResource("DisplayTasks.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(viewAllScene);
		window.show();
		}


	@FXML
	public void addTask(ActionEvent event) throws IOException {
		//https://www.youtube.com/watch?v=LDVztNtJWOo 
		
		//https://stackoverflow.com/questions/34863425/javafx-scene-builder-how-switch-scene 
		//Here's how you get those references:
		//Scene scene = btnSignIn.getScene();
		//Window window = scene.getWindow()
		//Stage stage = (Stage) window;
		
		//change the entire Scene:
		//FXMLLoader loader = ... // create and load() view
		//Stage stage = (Stage) btnSignIn.getScene().getWindow();
		//Scene scene = new Scene(loader.getRoot());
		//stage.setScene(scene);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Pane pane = (Pane) FXMLLoader.load(getClass().getResource("AddTask.fxml"));
		
		Scene addTaskScene = new Scene(pane);
		
		addTaskScene.getStylesheets().add(getClass().getResource("AddTask.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(addTaskScene);
		window.show();
		
	}
	
	/*
	 * Method: Logs user out of app and returns to login screen
	 * */
	@FXML
	public void logOut(ActionEvent event) throws IOException {
		GuiBasedApp.save();
		Stage window = (Stage) profileMenu.getScene().getWindow();
		
		GuiBasedApp.setUsers(UserCollection.loadUsers("users.txt"));		
		
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
		
		Scene loginScene = new Scene(root);
		
		loginScene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		window.setScene(loginScene);
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
	public void settings(ActionEvent event) throws IOException {
		
		Stage window = (Stage) profileMenu.getScene().getWindow();
		
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("SettingsScreen.fxml"));
		
		Scene SettingsScene = new Scene(pane);
		
		SettingsScene.getStylesheets().add(getClass().getResource("SettingsScreen.css").toExternalForm());
		
		GuiBasedApp.setPrevScene(window.getScene());
		window.setScene(SettingsScene);
		window.show();
	}
	
	@FXML
	public void sendTaskEmail(ActionEvent event) {
		
		System.out.println(GuiBasedApp.getUser().getUsrEmail());
		
		Email email = new Email();
		
		email.sendEmail(GuiBasedApp.getUser().getUsrEmail(), "Taskilla Task Update", "Tasks");
	}

	
	// method for deleting a users tasks
	//currently only removes task from list view, does not update user's tasks
	public static void deleteTask(String taskId, String taskName) {
		System.out.println("Deleting task ID: "+taskId+" Details: "+taskName);
		GuiBasedApp.getTasks().getTaskByID(UUID.fromString(taskId)).delete();
	}
	
	
	
}

