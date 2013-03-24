package assignments.chap3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPayStation {

	PayStation ps;
	
	@Before
	public void SetUp() {
		ps = new PayStationImpl();
	}
	
	
	@Test
	public void shouldDisplay2MinFor5Cents() throws IllegalCoinException{
		
		ps.addPayment(5);
		assertEquals("Should display 2 min for 5 cents", 2, ps.readDisplay());
		
	}

	@Test
	public void shouldDisplay10MinFor25Cents() throws IllegalCoinException{
		
		ps.addPayment(25);
		assertEquals("Should display 2 min for 5 cents", 10, ps.readDisplay());
		
	}
	
	@Test(expected=IllegalCoinException.class)
	public void shouldRejectIllegalCoin() throws IllegalCoinException {
		ps.addPayment(17);
	}
	@Test
	public void shouldDisplay14MinFor25Centsand10Cents() throws IllegalCoinException{
		
		ps.addPayment(25);
		ps.addPayment(10);
		assertEquals("Should display 2 min for 5 cents", 14, ps.readDisplay());
		
	}
	@Test
	public void shouldReturnCorrectReceiptWhenBuy() throws IllegalCoinException {
	
		ps.addPayment(5);
		ps.addPayment(25);
		ps.addPayment(10);
		Receipt receipt;
		receipt = ps.buy();
		assertNotNull("Receipt reference cannot be null", receipt);
		assertEquals("Receipt value must be 16 min. ", (5+10+25)/5*2, receipt.value());
		
	}
	
	@Test
	public void shouldStoreTimeinReceipt() {
		
		Receipt receipt = new ReceiptImpl(30);
		assertEquals("Receipt can store 30 minute value", 30, receipt.value());
		
	}
	
	@Test
	public void shouldReturnReceiptWhenBuy100c() throws IllegalCoinException {
	
		ps.addPayment(10);
		ps.addPayment(10);
		ps.addPayment(10);
		ps.addPayment(10);
		ps.addPayment(10);
		ps.addPayment(25);
		ps.addPayment(25);
		
		Receipt receipt;
		receipt = ps.buy();
		assertEquals( (5*10+2*25)/5*2, receipt.value());
		
		//start from here
		
	}
	
	@Test public void shouldClearAfterBuy(){
		
		ps.buy();
		assertTrue("Displays 0", ps.readDisplay()==0);
		
	}
}
