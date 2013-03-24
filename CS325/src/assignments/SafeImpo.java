package assignments;

public class SafeImpo implements Safe {

	private int index = 0;
	private char[] display;
	private boolean locked = true;
	private boolean kPressed;

	public SafeImpo() {

		display = Safe.BLANK_DISPLAY.toCharArray();

	}

	@Override
	public boolean isLocked() {
		// TODO Auto-generated method stub
		return locked;
	}

	@Override
	public String readDisplay() {
		// TODO Auto-generated method stub
		return String.valueOf(display);
	}

	@Override
	public void enter(char c) {
		// TODO Auto-generated method stub
	if (!kPressed){
		
		kPressed = (c=='K');
	}
		
		if (Character.isDigit(c) && !kPressed) {
			display= Safe.ERROR_DISPLAY.toCharArray();
			return;
		}
		if (Character.isDigit(c) ) {
			display[index++] = c;
		}
		if (String.valueOf(display).equals("123456")) {
			locked = false;
			kPressed = false;
			display = Safe.OPEN_DISPLAY.toCharArray();

		}
	}

}
