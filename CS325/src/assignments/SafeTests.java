package assignments;

import static org.junit.Assert.*;

import org.junit.Test;

public class SafeTests {

	@Test
	public void shouldBeLockedAndDisplayBlank() {

		Safe s = new SafeImpo();
		assertTrue("Should be locked", s.isLocked());
		assertEquals("Display should be blank", Safe.BLANK_DISPLAY,
				s.readDisplay());
		;

	}

	@Test
	public void shouldUnlockAndDisplayOpen() {

		Safe s = new SafeImpo();
		s.enter('K');
		s.enter('1');
		s.enter('2');
		s.enter('3');
		s.enter('4');
		s.enter('5');
		s.enter('6');
		assertFalse("Safe should be unlocked", s.isLocked());
		assertEquals("Display should be open", Safe.OPEN_DISPLAY,
				s.readDisplay());
		

	}
	
	@Test
	public void shouldDisplayErrorIfKeyButtonOmmitted() {

		Safe s = new SafeImpo();
		s.enter('1');
		s.enter('2');
		s.enter('3');
		s.enter('4');
		s.enter('5');
		s.enter('6');
		assertTrue("Safe should be locked", s.isLocked());
		assertEquals("Display should be open", Safe.ERROR_DISPLAY,
				s.readDisplay());

	}

}
