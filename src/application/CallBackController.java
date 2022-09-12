package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CallBackController {
	@FXML
	Label Playing;
	
	public void displayStatus(String fl) {
		Playing.setText(fl);
	}
	
}
