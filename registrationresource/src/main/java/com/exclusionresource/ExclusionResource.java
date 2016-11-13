package com.exclusionresource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/exclusionresource")
public class ExclusionResource {
	private List<String> blackList = new ArrayList<String>();

	@GET
	@Path("{dob}/{ssn}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean validate(@PathParam("dob") String dob, @PathParam("ssn") String ssn) {
		return isBlackListedUser(dob, ssn);
	}

	private boolean isBlackListedUser(String dob, String ssn) {
		List<String> blackList = getBlackList();
		if (blackList.contains(dob + ssn)) {
			return true;
		}
		return false;
	}

	private List<String> getBlackList() {
		blackList.add("20-05-1990XJohn");
		blackList.add("25-07-1989XRobert%*?");
		blackList.add("13-03-2001XTheresa");
		blackList.add("18-12-1987XClinton");
		blackList.add("31-05-1995XTrump");
		blackList.add("05-03-2003XBush");
		return blackList;
	}

}

