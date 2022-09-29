package lee.qinzhi.dev.application.Gui.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lee.qinzhi.dev.application.Gui.config.ready.ConfigController;
import lee.qinzhi.dev.discordrpc.settings.SettingManager;
import lee.qinzhi.dev.discordrpc.settings.Settings;
import lee.qinzhi.dev.discordrpc.settings.Theme;

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
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Gui/config/ready/ReadyConfig.fxml"));
		Parent root = loader.load();
		root.getStylesheets().add(getClass().getResource(Settings.getTheme().Themepass()).toExternalForm());
		ConfigController ec = loader.getController();
		//loader.setController(ec);
        stage = new Stage();
        stage.setTitle("Custom Discord RP");
        stage.setScene(new Scene(root));
        stage.setX(goBack.getScene().getWindow().getX());stage.setY(goBack.getScene().getWindow().getY());
        stage.setResizable(false);
        stage.show();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		theme.getItems().addAll(EnumSet.allOf(Theme.class));
		theme.setValue((Settings.getTheme()));
		theme.setOnAction((event) -> {
			Settings.setTheme(theme.getValue());
		});
		SettingManager.saveSettingToFile();
		
	}
	
}
