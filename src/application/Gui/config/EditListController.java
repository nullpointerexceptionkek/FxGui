package application.Gui.config;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Gui.config.ready.ConfigController;
import discordrpc.Script;
import discordrpc.Updates;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditListController extends ConfigController implements Initializable{
	
	@FXML
	private TextArea warning;
	
	@FXML
	private TextField image;
	
	@FXML
	private TextField firstline;
	
	@FXML
	private TextField secondline;
	
	@FXML
	private TextField Wait;
	
	@FXML
	private Button CancelButton;
	
	@FXML
	private Button SaveButton;
	
	@FXML
	private Button DeleteButton;
	
	@FXML
	private AnchorPane scenePane;
	
	private int numberInList = -1;
	
	Stage stage;
	
	public void cancelSaves(ActionEvent event) throws IOException {
		stage = (Stage) scenePane.getScene().getWindow();
		gobacktoConfig();
	}
	
	public void saveChanges(ActionEvent event) throws IOException {
		Script.setUpdates(new Updates(Long.parseLong(Wait.getText()),image.getText(),firstline.getText(),secondline.getText()), numberInList);
		gobacktoConfig();
	}
	
	public void deleteThisItem(ActionEvent event) throws IOException {
		Script.getTotalupdates().remove(numberInList);
		gobacktoConfig();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Wait.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            Wait.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        if(!Wait.getText().isEmpty())
		        	warning.setVisible(Long.valueOf(Wait.getText()) <= 16000);
		    }
		    
		});
	}
	
	public void setnumberInList(int numberInList) {
		this.numberInList = numberInList;
		Wait.setText(String.valueOf(Script.getTotalupdates().get(numberInList).getWait()));
		image.setText(Script.getTotalupdates().get(numberInList).getImage());
		firstline.setText(Script.getTotalupdates().get(numberInList).getFl());
		secondline.setText(Script.getTotalupdates().get(numberInList).getSl());
		warning.setVisible(Script.getTotalupdates().get(numberInList).getWait() <= 16000);
			
	}
	
	private void gobacktoConfig() throws IOException {
		stage = (Stage) scenePane.getScene().getWindow();
		double x = stage.getX();
		double y = stage.getY();
		stage.close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Gui/config/ready/ReadyConfig.fxml"));
		Parent root = loader.load();
		ConfigController cc = loader.getController();
		stage = new Stage();
		stage.setResizable(false);
		stage.setX(x);stage.setY(y);
		stage.setTitle("Custom Discord RP" );
		stage.setScene(new Scene(root));
		stage.show();
		numberInList = -1;
	}


	
	
}
