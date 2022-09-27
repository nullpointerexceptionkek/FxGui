package discordrpc.settings;

public enum Theme {

	light("/application/Gui/ApplicationLight.css"),
	dark("/application/Gui/Application.css");
	
	private final String themepass;

	Theme(String string) {
		this.themepass = string;
	}
	public String Themepass() {
		return themepass;
	}
}
