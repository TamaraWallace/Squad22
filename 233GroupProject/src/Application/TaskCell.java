package Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

class TaskCell extends ListCell<String>{
	
	// Initializing all the widgets for displaying the task
	HBox hb = new HBox();
	
	Button close = new Button(); 
	Label taskLbl = new Label("");
	Image delIcon  = new Image(getClass().getResourceAsStream("/deleteIcon.png"));
	Pane pane = new Pane();
	ImageView delete = new ImageView(delIcon);

	private String taskID;
	
	
	
	public TaskCell() {
		
		super();		
		
		// setting the size of trash bin button
		delete.setFitWidth(30);
		delete.setFitHeight(35);
		hb.getStylesheets().add(getClass().getResource("DisplayTasks.css").toExternalForm());
		
		taskID = "";
		
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
				GuiBasedApp.deleteTaskByID(taskID);
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
			
			taskID = task[0];
			String text = "";
			for (int i = 1; i < task.length; i++) {
				text += task[i];
			}
			taskLbl.setText(text);
			setGraphic(hb);
			
		}
	}
}
