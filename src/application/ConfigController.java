package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	TextField appID;
	
	
	public void switchToCallBack(ActionEvent event) throws IOException{
		String DiscordAppID = appID.getText();
		
		//root = FXMLLoader.load(getClass().getResource("/application/CallBack.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
