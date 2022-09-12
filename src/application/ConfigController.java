package application;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import discordrpc.DiscordRP;
import discordrpc.Script;
import discordrpc.Updates;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private ListView<Updates> displayUpdates;
	
	public void switchToCallBack(ActionEvent event) throws IOException{
		String DiscordAppID = appID.getText();
		//System.out.println(DiscordAppID);
		root = FXMLLoader.load(getClass().getResource("/application/CallBack.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
	}
	
	
}
