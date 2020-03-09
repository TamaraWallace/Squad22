package Application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.stage.Stage;
import main.Task;
import main.TaskCollection;

public class DisplayTasksController implements Initializable{
	
	@FXML
	private ListView<String> lstViewTasks;
	
	@FXML
	private Button backBtn;
	
	@FXML
	private Button completeBtn;

	private ObservableList<String> lstTasks = FXCollections.observableArrayList();
	
	
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	/*
	 * Create a static class for each cell in the lisView
	 * */
	static class Cell extends ListCell<String>{
		
		HBox hb = new HBox();
		
		Button close = new Button(); 
		Label taskLbl = new Label("");
		Image delIcon  = new Image(getClass().getResourceAsStream("/deleteIcon.png"));
		Pane pane = new Pane();
		ImageView delete = new ImageView(delIcon);

		private String taskId;
		
		
		
		public Cell() {
			
			super();		
			
			
			delete.setFitWidth(30);
			delete.setFitHeight(35);
			hb.getStylesheets().add(getClass().getResource("DisplayTasks.css").toExternalForm());
			
			taskId = "";
			
			taskLbl.setStyle("-fx-background-color: #000B38; -fx-font-weight:bold; ");
			taskLbl.setTextFill(Color.web("#24a78d"));
			close.setStyle("-fx-background-color: #000B38;");
			close.setGraphic(delete);

			hb.setStyle("-fx-background-color: #000B38;");
			// adding all the components in the sequence in which we want them to appear
			hb.getChildren().addAll(taskLbl,pane,close);
			HBox.setHgrow(pane, Priority.ALWAYS);
			
			close.setOnAction(new EventHandler<ActionEvent>() {
				
				
				
				@Override
				public void handle(ActionEvent event) {
					
					getListView().getItems().remove(getItem());
					deleteTask(taskId,taskLbl.getText());
				}

				
					
				
			});
			
			
		}
		
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lstViewTasks.setStyle("-fx-background-color: #000B38;");
		System.out.println("intialize is runnin now");
		
		
		TaskCollection tasks = TaskCollection.loadUsrTasks("tasks.txt", GuiBasedApp.getUser().getUsrID());
		
		
		for (Task task : tasks.getActiveTasks()) {
			
			System.out.print("initialising the lstview: ");
			System.out.println("task: "+task.toString());
		
			String display;
			String taskId = task.getTaskID();
			
			display = taskId +","+"Task: "+ task.getName()+ "\nNotes: " +task.getNotes() + "\nDue: "+ task.getDueDate().toString();
			
			lstTasks.add(display);
			
		}
		lstViewTasks.getItems().addAll(lstTasks);
		
		
		
		lstViewTasks.setCellFactory(param -> new Cell());
		
		lstViewTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
	}
	
	public static void deleteTask(String taskId, String taskName) {
		
		
		System.out.println("Deleting task ID: "+taskId+" Details: "+taskName);
		
	}
	
	@FXML
	public void back(ActionEvent event) {
				
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(GuiBasedApp.getPrevScene());
		
	}
	
	
	
}
