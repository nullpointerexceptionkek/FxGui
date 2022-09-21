package application.Gui.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SettingController implements Initializable{

	@FXML
	private Button goBack;
	
	@FXML
	private ChoiceBox<String> theme;
	
	@FXML
	private AnchorPane anchorroot;
	
	@FXML
	private StackPane stackpane;
	
	public void switchBack(ActionEvent event) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/Gui/config/ready/ReadyConfig.fxml"));
		Scene scene2 = anchorroot.getScene();
		
		root2.translateXProperty().set(scene2.getWidth());
		stackpane.getChildren().add(root2);
		
		Timeline timeline2 = new Timeline();
		KeyValue keyValue2 = new KeyValue(root2.translateXProperty(), 0,Interpolator.EASE_OUT);
		KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.3), keyValue2);
		timeline2.getKeyFrames().add(keyFrame2);
		timeline2.setOnFinished(event1 -> {
			stackpane.getChildren().remove(anchorroot);
		});
		timeline2.play();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
