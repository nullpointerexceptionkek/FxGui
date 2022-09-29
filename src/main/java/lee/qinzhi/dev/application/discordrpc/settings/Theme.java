package lee.qinzhi.dev.application.discordrpc.settings;

public enum Theme {

	light("/lee/qinzhi/dev/application/Gui/ApplicationLight.css"),
	dark("/lee/qinzhi/dev/application/Gui/Application.css"),
	lightgreen("/lee/qinzhi/dev/application/Gui/ApplicationLightGreen.css");
	
	private final String themepass;

	Theme(String string) {
		this.themepass = string;
	}
	public String Themepass() {
		return themepass;
	}
}
