package application.Gui.callbackscreen;

import java.io.IOException;

import application.LaunchManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CallBackController {
	@FXML
	Label Playing;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void displayStatus(String fl) {
		Playing.setText(fl);
	}
	public void switchToConfig(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Gui/config/ready/ReadyConfig.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		LaunchManager.closeCallBack();
	}
	
}
