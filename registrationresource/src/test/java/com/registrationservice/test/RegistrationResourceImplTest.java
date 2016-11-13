package com.registrationservice.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.registrationresource.Registration;
import com.registrationresource.RegistrationResourceImpl;

public class RegistrationResourceImplTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	/*
	 * Test that series of registrations are successful
	 */
	public void RegImplSuccessful() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		Registration reg0 = new Registration("ziad", "123A1", "25-11-1987", "ssn1gegg");
		assertEquals(regResImpl.validateRegistration(reg0), " Resgistration was successful!");
		Registration reg1 = new Registration("tania", "123A587", "14-11-1988", "s%sn$1gegg%?$");
		assertEquals(regResImpl.validateRegistration(reg1), " Resgistration was successful!");
		Registration reg2 = new Registration("zeina", "123Atre", "07-10-2001", "s124538485kl");
		assertEquals(regResImpl.validateRegistration(reg2), " Resgistration was successful!");
		Registration reg3 = new Registration("Theresa", "123Atre", "13-03-2001", "XTheresa");
		assertEquals(regResImpl.validateRegistration(reg3), " Registration failed!;User is blacklisted!");
	}

	@Test
	/*
	 * test that exception is thrown when duplicate username is used
	 */
	public void RegImplUserExistTest() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;User already exist.");
		Registration reg0 = new Registration("john", "123A1", "12-11-2000", "ssn1");
		Registration reg1 = new Registration("john", "125A1", "12-11-1990", "ssn2");
		regResImpl.validateRegistration(reg0);
		regResImpl.validateRegistration(reg1);
	}

	@Test
	/*
	 * Test that the entered username returns exception if space found
	 */
	public void RegImplUsernameFormatWrong1() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Invalid username. Must be alphanumeric and no space!;");
		Registration reg = new Registration("ralph sawaya", "123A1", "11-12-2000", "ssn1");
		regResImpl.validateRegistration(reg);
	}

	@Test
	/*
	 * Test that the entered username returns exception if not strictly alphanumeric
	 * 
	 */
	public void RegImplUsernameFormatWrong2() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Invalid username. Must be alphanumeric and no space!;");
		Registration reg = new Registration("ab?*rlds%78", "123A1", "11-12-2000", "ssn1");
		regResImpl.validateRegistration(reg);
	}

	@Test
	/*
	 * Test that the entered password throws exception if shorter than 4
	 * 
	 */
	public void RegImplPasswordFormatWrong1() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Password must contain at least one upper case and one number and be longer or equal to 4!");
		Registration reg = new Registration("ralph", "1Fz", "11-12-2000", "ssn1");
		regResImpl.validateRegistration(reg);
	}
	
	@Test
	/*
	 * Test that the entered password throws exception if does not contain capital letter
	 * 
	 */
	public void RegImplPasswordFormatWrong2() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Password must contain at least one upper case and one number and be longer or equal to 4!");
		Registration reg = new Registration("ralph", "1xzgh", "11-12-2000", "ssn1");
		regResImpl.validateRegistration(reg);
	}
	
	@Test
	/*
	 * Test that the entered password throws exception if does not contain a number
	 * 
	 */
	public void RegImplPasswordFormatWrong3() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Password must contain at least one upper case and one number and be longer or equal to 4!");
		Registration reg = new Registration("ralph", "axzgH", "11-12-2000", "ssn1");
		regResImpl.validateRegistration(reg);
	}

	@Test
	/*
	 * Test that the entered date of birth throws exception if wrong format
	 */
	public void RegImplDateFormatWrong1() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Invalid Date Of Birth!;");
		Registration reg = new Registration("ralph", "123A1", "1051987", "ssn1");
		regResImpl.validateRegistration(reg);
	}

	@Test
	/*
	 * Test that the entered date of birth throws exception if wrong format
	 */
	public void RegImplDateFormatWrong2() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Invalid Date Of Birth!;");
		Registration reg = new Registration("ralph", "123A1", "10/05/1987", "ssn1");
		regResImpl.validateRegistration(reg);
	}

	@Test
	/*
	 * Test that the entered date of birth throws exception if wrong format
	 */
	public void RegImplDateFormatWrong3() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Invalid Date Of Birth!;");
		Registration reg = new Registration("ralph", "123A1", "1987/05/10", "ssn1");
		regResImpl.validateRegistration(reg);
	}

	@Test
	/*
	 * Test that the entered date of birth throws exception if too old ( less than 1900 )
	 * 
	 */
	public void RegImplDateFormatWrong4() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;Invalid Date Of Birth!;");
		Registration reg = new Registration("ralph", "123A1", "14-08-1800", "ssn1");
		regResImpl.validateRegistration(reg);
	}
	
	@Test
	/*
	 * Test that the entered ssn is not empty
	 * 
	 */
	public void RegImplssnFormatWrong() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		expectedEx.expectMessage(" Registration failed!;SSN should at least be 1 character!;");
		Registration reg = new Registration("ralph", "123A1", "14-08-1800", "");
		regResImpl.validateRegistration(reg);
	}
	
	@Test
	/*
	 * Test blacklist error message
	 */
	public void RegImplfailedBlackList() throws Exception {
		RegistrationResourceImpl regResImpl = new RegistrationResourceImpl();
		Registration reg3 = new Registration("Theresa", "123Atre", "13-03-2001", "XTheresa");
		assertEquals(regResImpl.validateRegistration(reg3), " Registration failed!;User is blacklisted!");
	}
}
