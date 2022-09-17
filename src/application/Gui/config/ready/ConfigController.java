package application.Gui.config.ready;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.LaunchManager;
import application.Gui.LoadingScreen.LoadingController;
import application.Gui.config.EditListController;
import discordrpc.DiscordRP;
import discordrpc.Script;
import discordrpc.Updates;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConfigController implements Initializable{
	
	@FXML
	private TextField appID;
	@FXML
	protected ListView<Updates> displayUpdates;
	
	@FXML
	private Button callbackButton;
	
	@FXML
	private RadioButton applaunch, none, local, custom;
	
	@FXML
	private AnchorPane Anchorroot;
	
	@FXML
	private StackPane stackPane;
	
	private int currentCount;
	
	public void getTimeStampMode(ActionEvent event){
		if(applaunch.isSelected()) {
			Script.setTimestampmode("Default");
		} 
		else if(none.isSelected()){
			Script.setTimestampmode("None");
		}
		else if(local.isSelected()){
			Script.setTimestampmode("Local time");
		}
		else if(custom.isSelected()){
			Script.setTimestampmode("Custom");
		}
	}
	
	public void switchToCallBack(ActionEvent event) throws IOException, InterruptedException{
		//update DiscordRP app id and save it to the file
		String DiscordAppID = appID.getText();
		DiscordRP.apikey = DiscordAppID;
		DiscordRP.saveKeyToFile();
		
		System.out.println(displayUpdates.getItems());
		ArrayList<Updates> u = new ArrayList<>(displayUpdates.getItems());
		Script.setTotalupdates(u); 
		LaunchManager.saveScripToFile();
		LaunchManager.initCallBack();
		//Parent root = FXMLLoader.load(getClass().getResource("/application/Gui/callbackscreen/CallBack.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Gui/LoadingScreen/LoadingScreen.fxml"));
		Parent root = loader.load();
		LoadingController lc = loader.getController();
		lc.toNewScene(100,"callback");
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = callbackButton.getScene();
		
		
		root.translateYProperty().set(scene.getHeight());
		stackPane.getChildren().add(root);
		
		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(root.translateYProperty(), 0,Interpolator.EASE_IN);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.3), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.setOnFinished(event1 -> {
			stackPane.getChildren().remove(Anchorroot);
		});
		timeline.play();
		
		
		

	}
	
	public void addnewitem() {
		currentCount++;
		Script.addUpdates(new Updates(50, String.valueOf(currentCount), "Playing ", "Bad Apple"));
		displayUpdates.getItems().clear();		
		displayUpdates.getItems().addAll(Script.getTotalupdates());	
	}

	
	//this file will init and set the Listview to the total updates that was read from Json
	//it will also set the appid to only accept numbers and if loaded is not null, it will leave it empty
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		currentCount = Script.getTotalupdates().size();
		appID.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            appID.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		    
		});
		appID.setText(DiscordRP.apikey);
		
		//set the timestamp mode
		switch(Script.getTimestampmode()) {
			case "Default":
				applaunch.setSelected(true);
				break;
			
			case "None":
				none.setSelected(true);
				break;
				
			case "Local time":
				local.setSelected(true);
				break;
			
			case "Custom":
				custom.setSelected(true);
				break;
			default:
				applaunch.setSelected(true);
				Script.setTimestampmode("Default");
		
		}
		
		
		
		try {
			displayUpdates.getItems().addAll(Script.getTotalupdates());	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//check if the list is double clicked
		displayUpdates.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2){
                    	if(!((displayUpdates.getSelectionModel().getSelectedIndex()) == -1)) {
                    		showListConfig(displayUpdates.getSelectionModel().getSelectedIndex());
                    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    		stage.close();
                    	}
                    }
                }
				
			}

        });
	}
	//this will open up a new window and edit the arraylist
	private void showListConfig(int numberInList) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Gui/config/EditListScript.fxml"));
			Parent root = loader.load();
			EditListController ec = loader.getController();
			//loader.setController(ec);
			ec.setnumberInList(numberInList);
	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setTitle("Config Editor");
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	
	
	
	
	
}
