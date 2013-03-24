package assignments.frc.chap2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEmail {
	
	 /**
	   * Verify that a string is an email address.
	   * The string must obey the following (somewhat strict format)
	   * <email>      ::= <identifier> @ <identifier> {. <identifier>}*
	   * <identifier> ::= letter { letter | digit }+
	**/

	@Test
	public void testIdentifierStartsWithLetter() {
		
		assertTrue(new EmailAddress("student12@smumn.edu").isValid());
		assertFalse(new EmailAddress("3tudent12@smumn.edu").isValid());
		assertFalse(new EmailAddress("student12@_mumn.edu").isValid());
		assertFalse(new EmailAddress("student12@smumn.?du").isValid());
	}
	
	@Test
	public void testIdentifierHasAtLeast2Characters() {
		
		assertTrue(new EmailAddress("s1@smu.e3u").isValid());
		assertFalse(new EmailAddress("s@smu.edu").isValid());
		assertFalse(new EmailAddress("stu@smu.u").isValid());
		assertFalse(new EmailAddress("stu@s.ue").isValid());
		
	}

	@Test
	public void testContainsAtCharacter() {
	
		assertFalse(new EmailAddress("student12smumn.edu").isValid());
		
	}
	
	@Test
	public void testContainsIllegalCharacter() {
		
		assertFalse(new EmailAddress("student12@smumn.edu?").isValid());
		assertFalse(new EmailAddress("student12@sm!umn.edu").isValid());
		assertFalse(new EmailAddress("student12@smumn..edu").isValid());
		assertFalse(new EmailAddress("student12@sm@umn.edu").isValid());
		
	}
	
	@Test
	public void testContainsAtLeastOneDomain() {
		
		assertTrue(new EmailAddress("student12@smumn").isValid());
		assertTrue(new EmailAddress("student12@smumn.edu").isValid());
		assertTrue(new EmailAddress("student12@smumn.edu.com").isValid());
		assertFalse(new EmailAddress("student12").isValid());	
	}
	
	@Test
	public void testEnablesBigLetters() {
		
		assertTrue(new EmailAddress("Atudent12@smumn.edu").isValid());
		assertTrue(new EmailAddress("student12@smumn.edU").isValid());
		
	}
	
	@Test
	public void testEmptyEmail() {
		
		assertFalse(new EmailAddress("").isValid());	
	}
	
	
}
