package application;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import discordrpc.DiscordRP;
import discordrpc.Script;
import discordrpc.Updates;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextField appID;
	@FXML
	protected ListView<Updates> displayUpdates;
	private ArrayList<Updates> u;
	
	@FXML
	private Button callbackButton;
	
	public void switchToCallBack(ActionEvent event) throws IOException{
		//update DiscordRP app id and save it to the file
		String DiscordAppID = appID.getText();
		DiscordRP.apikey = DiscordAppID;
		DiscordRP.saveKeyToFile();
		//System.out.println(DiscordAppID);
		root = FXMLLoader.load(getClass().getResource("/application/CallBack.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		System.out.println(displayUpdates.getItems());
		u = new ArrayList<>(displayUpdates.getItems());
		Script.setTotalupdates(u); 
		LaunchManager.initCallBack();
	}

	
	//this file will init and set the Listview to the total updates that was read from Json
	//it will also set the appid to only accept numbers and if loaded is not null, it will leave it empty
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
		try {
			displayUpdates.getItems().addAll(Script.getTotalupdates());	
		}catch(Exception e) {
			e.printStackTrace();
		}
		//add lister to check if the listview is changed :/
		/*
		displayUpdates.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Updates>() {
			@Override
			public void changed(ObservableValue<? extends Updates> arg0, Updates arg1, Updates arg2) {
				//showListConfig();
				System.out.println(displayUpdates.getSelectionModel().getSelectedItem());
			}
			
		}); */
		displayUpdates.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
            	showListConfig(displayUpdates.getSelectionModel().getSelectedIndex());
            	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            	stage.close();
            }

        });
	}
	//this will open up a new window and edit the arraylist
	private void showListConfig(int numberInList) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditListScript.fxml"));
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
	    }
	}
	
	
	
	
}
