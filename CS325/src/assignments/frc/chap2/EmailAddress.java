package assignments.frc.chap2;

/**
 * The EmailAddress class represents an email address.
 *  <email>      ::= <identifier> @ <identifier> {. <identifier>}*
 *  <identifier> ::= letter { letter | digit }+
 *  
 * A typical usage example is: 
 * EmailAddress ea = new EmailAddress("john@company.com"); boolean isProper = ea.isValid();
 * 
 **/

public class EmailAddress {
	
	private String address;
	private String identifier = "([a-zA-Z])([a-zA-Z]|\\d)+";


	public EmailAddress(String address) {
		this.address = address;
	}

	public boolean isValid() {
		return address.matches(identifier + "@" + identifier + "(\\." + identifier + ")*");
	}

}
