package assignments;

public interface Safe {

	public static final String BLANK_DISPLAY = "      ";
	public static final String OPEN_DISPLAY = " OPEN ";
	public static final int DISPLAY_LENGHT = 6;
	public static final String ERROR_DISPLAY = "ERROR ";

	boolean isLocked();

	public String readDisplay();

	void enter(char c);

}
