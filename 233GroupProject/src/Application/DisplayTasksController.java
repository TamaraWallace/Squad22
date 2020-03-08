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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

	private ObservableList<String> lstTasks = FXCollections.observableArrayList();
	
	
	/*
	 * Create a static class for each cell in the lisView
	 * */
	static class Cell extends ListCell<String>{
		
		HBox hb = new HBox();
		
		Button close = new Button(); 
		Label task = new Label("");
		Image delIcon  = new Image(getClass().getResourceAsStream("/deleteIcon.png"));
		Pane pane = new Pane();
		ImageView delete = new ImageView(delIcon);
		
		
		
		public Cell() {
			
			super();
			
			
			
			delete.setFitWidth(30);
			delete.setFitHeight(35);
			
			task.setStyle("-fx-background-color: #000B38; ");
			task.setTextFill(Color.web("#24a78d"));
			close.setStyle("-fx-background-color: #000B38;");
			close.setGraphic(delete);

			hb.setStyle("-fx-background-color: #000B38;");
			// adding all the components in the sequence in which we want them to appear
			hb.getChildren().addAll(task,pane,close);
			HBox.setHgrow(pane, Priority.ALWAYS);
			
			close.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					deleteTask(event);
				}

				
					
				
			});
		}
		
		@Override
		public void updateItem(String name, boolean empty) {
			super.updateItem(name, empty);
			setText(null);
			setGraphic(null);
			
			if (name != null && !empty) {
				
				task.setText(name);
				setGraphic(hb);
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		ArrayList<Task> tasks = GuiBasedApp.getTasks().getActiveTasks();
		
		for (Task task : tasks) {
			
			System.out.println("task: "+task.toString());
		
			String display;
			
			display = "Task: "+ task.getName()+ "\nNotes: " +task.getNotes() + "\nDue: "+ task.getDueDate().toString();
			
			lstTasks.add(display);
			
		}
		lstViewTasks.setItems(lstTasks);
		
		
		
		lstViewTasks.setCellFactory(param -> new Cell());
		
		lstViewTasks.setStyle("-fx-background-color: #000B38;");
	}
	
	public static void deleteTask(ActionEvent event) {
		
	}
	
	
}
