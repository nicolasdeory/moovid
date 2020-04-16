package aiss.luis.model;

public class Intent {

	protected IntentType int_type;
	protected Double punctuation;
	
	protected Intent () {
		super();
	}
	
	public static Intent of(IntentType int_type, Double punctuation) {
		if (int_type.equals(IntentType.MONTAGE_THEME_INTENT))
			throw new IllegalArgumentException("Wrong \"of\" method used: "
					+ "Try ThemeIntent.of(MontageTheme theme, Double punctuation)");
		Intent res = new Intent();
		res.setInt_type(int_type);
		res.setPunctuation(punctuation);
		return res;
	}

	public IntentType getInt_type() {
		return int_type;
	}

	public void setInt_type(IntentType int_type) {
		if (!this.getClass().equals(ThemeIntent.class))
			this.int_type = int_type;
	}

	public Double getPunctuation() {
		return punctuation;
	}

	public void setPunctuation(Double punctuation) {
		this.punctuation = punctuation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((int_type == null) ? 0 : int_type.hashCode());
		result = prime * result + ((punctuation == null) ? 0 : punctuation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intent other = (Intent) obj;
		if (int_type != other.int_type)
			return false;
		if (punctuation == null) {
			if (other.punctuation != null)
				return false;
		} else if (!punctuation.equals(other.punctuation))
			return false;
		return true;
	}
	
	
}
