package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import discordrpc.Script;
import discordrpc.Updates;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditListController extends ConfigController implements Initializable{
	
	
	@FXML
	private TextField firstline;
	
	@FXML
	private TextField secondline;
	
	@FXML
	private Spinner<Integer> Wait;
	
	@FXML
	private Button CancelButton;
	
	@FXML
	private Button SaveButton;
	
	@FXML
	private AnchorPane scenePane;
	
	private int numberInList = -1;
	
	Stage stage;
	
	public void cancelSaves(ActionEvent event) throws IOException {
		stage = (Stage) scenePane.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ReadyConfig.fxml"));
		Parent root = loader.load();
		ConfigController cc = loader.getController();
		//cc.setDisable(false);
		stage = new Stage();
		stage.setTitle("Custom Discord RP" );
		stage.setScene(new Scene(root));
		stage.show(); 
		numberInList = -1;
	}
	
	public void saveChanges(ActionEvent event) throws IOException {
		stage = (Stage) scenePane.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ReadyConfig.fxml"));
		Parent root = loader.load();
		ConfigController cc = loader.getController();
		//cc.setDisable(false);
		stage = new Stage();
		stage.setTitle("Custom Discord RP" );
		stage.setScene(new Scene(root));
		stage.show(); 
		Script.setUpdates(new Updates((long)(Wait.getValue()*1000),firstline.getText(),secondline.getText()), numberInList);
		numberInList = -1;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}
	
	public void setnumberInList(int numberInList) {
		this.numberInList = numberInList;
		try {
			firstline.setText(Script.getTotalupdates().get(numberInList).getFl());
			secondline.setText(Script.getTotalupdates().get(numberInList).getSl());
			SpinnerValueFactory<Integer> valueFactory = 
					new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 300);
			valueFactory.setValue((int)(Script.getTotalupdates().get(numberInList).getWait())/1000);
			Wait.setValueFactory(valueFactory);
		} catch (Exception e) {
			
		}
	}


	
	
}