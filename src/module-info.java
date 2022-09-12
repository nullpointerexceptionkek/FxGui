module FxGui {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires discord.rpc;
	requires com.google.gson;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
