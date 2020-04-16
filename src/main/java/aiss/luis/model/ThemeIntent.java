package aiss.luis.model;

public class ThemeIntent extends Intent{
	
	protected MontageTheme theme;
	
	protected ThemeIntent() {
		super();
		this.int_type = IntentType.MONTAGE_THEME_INTENT;
	}
	
	public static ThemeIntent of(MontageTheme theme, Double punctuation) {
		ThemeIntent res = new ThemeIntent();
		res.setTheme(theme);
		res.setPunctuation(punctuation);
		return res;
	}

	public MontageTheme getTheme() {
		return theme;
	}

	public void setTheme(MontageTheme theme) {
		this.theme = theme;
	}
	
	
}
