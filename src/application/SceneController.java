package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToConfig(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Config.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToCallBack(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/application/CallBack.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void unexpectedCrash() {
		
	}
	public static void unexpectedCrash(Exception e) {
		
	}
	
}
