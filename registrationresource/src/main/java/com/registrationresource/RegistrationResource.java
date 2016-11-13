package com.registrationresource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/registrationresource")
public class RegistrationResource {
	RegistrationResourceImpl regImpl;

	public RegistrationResource() {
		this.regImpl = new RegistrationResourceImpl();
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerCandidate(Registration reg) {
		String result = "";
		try {
			result = regImpl.validateRegistration(reg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = e.getMessage();
		}
		return result;
	}

}
