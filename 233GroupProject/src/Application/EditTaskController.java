package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditTaskController {


	@FXML 
	private TextField name,notes;
	@FXML
	private DatePicker date;
	
	@FXML 
	private Label dateLabel,nameLabel;
	
	@FXML
	private Button backBtn;
	
	
}
