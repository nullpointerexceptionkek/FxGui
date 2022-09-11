module FxGui {
	requires discord.rpc;
	requires com.google.gson;
	
	opens application to javafx.graphics, javafx.fxml;
}
