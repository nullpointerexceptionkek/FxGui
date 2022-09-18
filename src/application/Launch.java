package application;
	
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Launch extends Application {
		
	@Override
	public void start(Stage primaryStage) {
		try {
			LaunchManager.init();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Gui/config/ready/ReadyConfig.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Custom Discord RPC");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			System.out.println(sStackTrace);
			try {
				Parent exc = FXMLLoader.load(getClass().getResource("/application/Gui/errorScreen/AppErrorScreen.fxml"));
				Scene sceneexc = new Scene(exc);
				primaryStage.setScene(sceneexc);
				primaryStage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		//check if already running
		ServerSocket ss;
		 ss = null;

		    try {
		        ss = new ServerSocket(1044);
		    } catch (IOException e) {
		        System.err.println("Application already running!");
		        System.exit(-1);
		    }
		
		launch(args);
	}
	
	@Override
	public void stop() throws Exception {
		LaunchManager.onClose();
	}
}
