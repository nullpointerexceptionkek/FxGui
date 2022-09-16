package application.Gui.callbackscreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.LaunchManager;
import application.Gui.LoadingScreen.LoadingController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CallBackController implements Initializable{
	@FXML
	Label Playing;
	
	@FXML
	private StackPane stackPane;
	
	@FXML
	private AnchorPane anchorRoot;
	
	@FXML
	private Button switchtoconfig;
	
	public void displayStatus(String fl) {
		Playing.setText(fl);
	}
	public void switchToConfig(ActionEvent event) throws IOException, InterruptedException {
		switchtoconfig.setDisable(true);
		LaunchManager.closeCallBack();
		/*
		FadeTransition fadeTransition = new FadeTransition();
		fadeTransition.setDuration(Duration.millis(1000));
		fadeTransition.setNode(stackPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Gui/LoadingScreen/LoadingScreen.fxml"));
					Parent root;
					root = loader.load();
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					LoadingController lc = loader.getController();
					lc.toNewScene(0,"readyconfig");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		});
		fadeTransition.play();
		*/

		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Gui/LoadingScreen/LoadingScreen.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = stage.getScene();
		LoadingController lc = loader.getController();
		lc.toNewScene(3000,"readyconfig");
		root.translateYProperty().set(scene.getHeight());
		stackPane.getChildren().add(root);
		
		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(root.translateYProperty(),0,Interpolator.EASE_OUT);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.3), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.setOnFinished(event1 -> {
			stackPane.getChildren().remove(anchorRoot);
		});
		timeline.play();
		
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		switchtoconfig.setDisable(false);
	}
	
}
