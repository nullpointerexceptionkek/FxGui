module CDiscordRP.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires discord.rpc;
    requires com.google.gson;
    requires javafx.base;
    requires java.desktop;

    opens lee.qinzhi.dev.application to javafx.graphics, javafx.fxml;
    opens lee.qinzhi.dev.application.Gui.config to javafx.graphics, javafx.fxml;
    opens lee.qinzhi.dev.application.Gui.config.ready to javafx.graphics, javafx.fxml;
    opens lee.qinzhi.dev.application.Gui.Settings to javafx.graphics, javafx.fxml;
    opens lee.qinzhi.dev.application.Gui.callbackscreen to javafx.graphics, javafx.fxml;
    opens lee.qinzhi.dev.application.Gui.LoadingScreen to javafx.graphics, javafx.fxml;

    exports lee.qinzhi.dev.application to javafx.graphics, javafx.fxml;
    exports lee.qinzhi.dev.application.Gui.config to javafx.graphics, javafx.fxml;
    exports lee.qinzhi.dev.application.Gui.config.ready to javafx.graphics, javafx.fxml;
    exports lee.qinzhi.dev.application.Gui.Settings to javafx.graphics, javafx.fxml;
    exports lee.qinzhi.dev.application.Gui.callbackscreen to javafx.graphics, javafx.fxml;
    exports lee.qinzhi.dev.application.Gui.LoadingScreen to javafx.graphics, javafx.fxml;
}