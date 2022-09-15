package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditListController implements Initializable{
	
	@FXML
	private Button CancelButton;
	
	@FXML
	private Button SaveButton;
	
	@FXML
	private AnchorPane scenePane;
	
	Stage stage;
	
	public void cancelSaves(ActionEvent event) {
		stage = (Stage) scenePane.getScene().getWindow();
		stage.close();
		
	}
	
	public void saveChanges(ActionEvent event) {
		
		stage = (Stage) scenePane.getScene().getWindow();
		stage.close();
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}


	
	
}
