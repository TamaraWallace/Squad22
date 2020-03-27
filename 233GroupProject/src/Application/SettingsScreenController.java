package Application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsScreenController {

	@FXML 
	private TextField userName,email; //associated with text object for task name & notes in FXML
	
	@FXML
	private Button backBtn; //associated with the back button object in FXML
	private Button updateBtn; // associated with the update button object in FXML
}
