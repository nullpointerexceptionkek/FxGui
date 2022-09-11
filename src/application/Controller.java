package application;

import javafx.event.ActionEvent;

public class Controller {

	public void close(ActionEvent e) {
		System.out.println("close");
		LaunchManager.onClose();
	}
	
}
