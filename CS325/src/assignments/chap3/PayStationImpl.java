package assignments.chap3;

public class PayStationImpl implements PayStation {

	private int insertedSoFar = 0;
	private int timeBought;

	@Override
	public void addPayment(int coinValue) throws IllegalCoinException {
		
		insertedSoFar = insertedSoFar + coinValue;
		switch(coinValue) {
		case 5: break;
		case 10: break;
		case 25: break;
		default: 
			throw new IllegalCoinException("Invalid coin: "+ coinValue);
		}
		timeBought=insertedSoFar / 5*2;
		
	
		// TODO Auto-generated method stub
		
	}

	@Override
	public int readDisplay() {
		// TODO Auto-generated method stub
		return timeBought;
		
	}

	@Override
	public Receipt buy() {
		// TODO Auto-generated method stub
		Receipt receipt = new ReceiptImpl(timeBought); 
		timeBought =0;
		return receipt;
	}

}
