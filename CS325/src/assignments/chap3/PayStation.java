package assignments.chap3;

public interface PayStation {

	public void addPayment(int i) throws IllegalCoinException;

	public int readDisplay();

	public Receipt buy();
	

}
