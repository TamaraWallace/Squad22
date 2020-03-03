package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateUserController {
	
	@FXML
	private Button backBtn;
	
	@FXML
	private Button createUsrBtn;
	
	
	@FXML
	public void back(ActionEvent event) {
				
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		window.setScene(GuiBasedApp.getPrevScene());
		
	}
	 
	
}
