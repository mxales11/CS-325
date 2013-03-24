package assignments.chap3;

public class ReceiptImpl implements Receipt {

	public int value =0;
	
	public ReceiptImpl(int value) {
		
		this.value = value;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int value() {
		// TODO Auto-generated method stub
		return value;
	}

}
