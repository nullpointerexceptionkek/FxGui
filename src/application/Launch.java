package application;
	
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import discordrpc.settings.Settings;
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
			scene.getStylesheets().add(getClass().getResource(Settings.getTheme().Themepass()).toExternalForm());
			primaryStage.setTitle("Custom Discord RPC");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			System.out.println(sStackTrace);
		    String message = "\"Custom Discord RP\"\n"
		            + "it looks like we cannot connect to javaFX\n"
		            + "Please check if javaFX is installed";
		        JOptionPane.showMessageDialog(new JFrame(), message, "Error",
		            JOptionPane.ERROR_MESSAGE);
		    System.exit(1);
		       
		}
		
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(args));
		//check if already running
		ServerSocket ss;
		 ss = null;

		    try {
		        ss = new ServerSocket(1044);
		        launch(args);
		    } catch (IOException e) {
		        System.err.println("Application already running!");
			    String message = "\"Custom Discord RP\"\n"
			            + "It looks like you are trying to create\n"
			            + "mutiple instance of the program";
			        JOptionPane.showMessageDialog(new JFrame(), message, "Application Running",
			            JOptionPane.ERROR_MESSAGE);
			    System.exit(1);
		    }
		
	}
	
	@Override
	public void stop() throws Exception {
		LaunchManager.onClose();
	}
}
