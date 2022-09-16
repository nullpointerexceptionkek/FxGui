package application.Gui.LoadingScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.LaunchManager;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingController implements Initializable{

	@FXML
	private ProgressIndicator progress;
	
	@FXML
	private StackPane stackpane;
	
	@FXML
	private AnchorPane anchorroot;
	
	private String file;
	
	private Long sleep;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new SplashScreen().start();
	}
	
	class SplashScreen extends Thread{
		
		@Override
		public void run() {
			try {
				Thread.sleep(5000);
				
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						try {
							switch(file) {
							case "callback":
								Parent root = FXMLLoader.load(getClass().getResource("/application/Gui/callbackscreen/CallBack.fxml"));
								Scene scene = anchorroot.getScene();
								
								root.translateYProperty().set(scene.getHeight());
								stackpane.getChildren().add(root);
								
								Timeline timeline = new Timeline();
								KeyValue keyValue = new KeyValue(root.translateYProperty(), 0,Interpolator.EASE_IN);
								KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
								timeline.getKeyFrames().add(keyFrame);
								timeline.setOnFinished(event1 -> {
									stackpane.getChildren().remove(anchorroot);
								});
								timeline.play();
								break;
							case "readyconfig":
								Parent root1 = FXMLLoader.load(getClass().getResource("/application/Gui/config/ready/ReadyConfig.fxml"));
								Scene scene1 = anchorroot.getScene();
								
								root1.translateYProperty().set(scene1.getHeight());
								stackpane.getChildren().add(root1);
								
								Timeline timeline1 = new Timeline();
								KeyValue keyValue1 = new KeyValue(root1.translateYProperty(), 0,Interpolator.EASE_OUT);
								KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), keyValue1);
								timeline1.getKeyFrames().add(keyFrame1);
								timeline1.setOnFinished(event1 -> {
									stackpane.getChildren().remove(anchorroot);
								});
								timeline1.play();
								break;
								
							}
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void toNewScene(long sleep, String file)  {
		this.sleep = sleep;
		this.file = file;
		
	}
	
	
	
	
}
