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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Gui/config/ready/ReadyConfig.fxml"));
		Pane pane = loader.load();
		anchorroot.getScene().setRoot(pane);
		Circle circle = new Circle(50);
		pane.setShape(circle);
		circle.radiusProperty().addListener((observable, oldvalue, newvalue) -> {
			double size = newvalue.doubleValue()*2;
			pane.setMinSize(size, size);
			pane.setPrefSize(size, size);
			pane.setMaxSize(size, size);
		});
		
		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(circle.radiusProperty(), 200, Interpolator.EASE_IN);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		theme.getItems().addAll("Dark", "Light");
		
	}
	
}
