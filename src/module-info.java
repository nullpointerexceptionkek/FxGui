module FxGui {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires discord.rpc;
	requires com.google.gson;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.Gui.callbackscreen to javafx.graphics, javafx.fxml;
	opens application.Gui.config to javafx.graphics, javafx.fxml;
	opens application.Gui.config.ready to javafx.graphics, javafx.fxml;
	opens application.Gui.LoadingScreen to javafx.graphics, javafx.fxml;
	
	exports application to javafx.graphics, javafx.fxml;
	exports application.Gui.callbackscreen to javafx.graphics, javafx.fxml;
	exports application.Gui.config to javafx.graphics, javafx.fxml;
	exports application.Gui.config.ready to javafx.graphics, javafx.fxml;
	exports application.Gui.LoadingScreen to javafx.graphics, javafx.fxml;
}
