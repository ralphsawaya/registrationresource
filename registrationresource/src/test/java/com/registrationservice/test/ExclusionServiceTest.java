package com.registrationservice.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.exclusionresource.ExclusionResource;
import com.registrationresource.Registration;

public class ExclusionServiceTest {
	@Rule
	public  ExpectedException expectedEx = ExpectedException.none();
	/*
	 * BLACKLIST
	 *  
	 *  DOB          SSN
	 *  20-05-1990   XJohn
	 *	25-07-1989   XRobert%*?
	 *	13-03-2001   XTheresa
	 *	18-12-1987   XClinton
	 *	31-05-1995   XTrump
	 *	05-03-2003   XBush
	 */

	@Test
	/*
	 * check that user is blacklisted
	 */
	public void ExclusionUserBlacklitedTest() {
		ExclusionResource es = new ExclusionResource();
		Registration reg =  new Registration("John","123A1","20-05-1990","XJohn");
		assertTrue(es.validate(reg.getDob(), reg.getSsn()));
	}
	
	@Test
	/*
	 * check that user is blacklisted
	 */
	public void ExclusionUserBlacklitedTest2() {
		ExclusionResource es = new ExclusionResource();
		Registration reg =  new Registration("Robert","123A2z1","25-07-1989","XRobert%*?");
		assertTrue(es.validate(reg.getDob(), reg.getSsn()));
	}
	
	@Test
	/*
	 * check that user is accepted
	 */
	public void ExclusionUserBlacklitedTest3() {
		ExclusionResource es = new ExclusionResource();
		Registration reg =  new Registration("Ziad","123A2y","25-07-1989","Ziad");
		assertFalse(es.validate(reg.getDob(), reg.getSsn()));
	}

}
