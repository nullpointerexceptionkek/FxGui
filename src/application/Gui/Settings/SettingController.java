package application.Gui.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import discordrpc.settings.SettingManager;
import discordrpc.settings.Settings;
import discordrpc.settings.Theme;
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
	private ChoiceBox<Theme> theme;
	
	@FXML
	private AnchorPane anchorRoot;
	
	@FXML
	private StackPane stackPane;
	
	public void switchBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Gui/config/ready/ReadyConfig.fxml"));
		Scene scene = anchorRoot.getScene();
		
		root.translateYProperty().set(scene.getHeight());
		stackPane.getChildren().add(root);
		
		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(root.translateYProperty(), 0,Interpolator.EASE_OUT);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.setOnFinished(event1 -> {
			stackPane.getChildren().remove(anchorRoot);
		});
		timeline.play();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		theme.getItems().addAll(Theme.dark,Theme.light);
		theme.setValue((Settings.getTheme()));
		theme.setOnAction((event) -> {
			Settings.setTheme(theme.getValue());
		});
		SettingManager.saveSettingToFile();
		
	}
	
}
