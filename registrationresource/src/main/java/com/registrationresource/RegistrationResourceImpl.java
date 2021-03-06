package com.registrationresource;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

public class RegistrationResourceImpl {

	private static HashMap<String, Registration> listOfRegisteredUsers = new HashMap<String, Registration>();
    /*
     * 1- first, we check if username already registered.
     * 2- second, we check the data validity.
     * 3- finally, if 1 and 2 are valid then we contact the Exclusion Service for final validation.
     * 4- we should not forget to consider that the service might not respond or might return wrong data.
     */
	public String validateRegistration(Registration reg) throws Exception {
		String username = reg.getUsername();
		boolean isUserAlreadyRegistered = isUserAlreadyRegistered(username);
		boolean isValidDataFormat = isValidDataFormat(reg);
		if (isValidDataFormat && !isUserAlreadyRegistered) {
			String isBlackListedUser = isBlackListedUser(reg.getDob(), reg.getSsn());
			if (isBlackListedUser.equals("false")) {
				registerUser(username, reg);
				return " Resgistration was successful!";
			} else if (isBlackListedUser.equals("true")) {
				return " Registration failed!;User is blacklisted!";
			} else {
				return " Registration failed!;Exclusion service did not respond correctly!";
			}
		}
		return " Registration failed!";
	}

	private boolean isValidDataFormat(Registration reg) throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Registration>> violations = validator.validate(reg);
		boolean result = violations.size() > 0 ? false : true;
		if (!result) {
			String message = "";
			for (ConstraintViolation<Registration> c : violations) {
				message += c.getMessage() + ";";
			}
			throw new Exception(" Registration failed!;" + message);
		}
		return result;
	}

	private boolean isUserAlreadyRegistered(String username) throws Exception {
		boolean result = listOfRegisteredUsers.containsKey(username) ? true : false;
		if (result) {
			throw new Exception(" Registration failed!;User already exist.");
		}
		return result;
	}

	private void registerUser(String username, Registration reg) {
		listOfRegisteredUsers.put(username, reg);
	}

	private String isBlackListedUser(String dob, String ssn) throws Exception {

		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target("http://localhost:8080/registrationresource/exclusion/exclusionresource/"
				+ URLEncoder.encode(dob, "UTF-8") + "/"
				+ URLEncoder.encode(ssn, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!")
						.replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")")
						.replaceAll("\\%7E", "~"));
		Builder request = resource.request();
		request.accept("application/json");
		Response response = request.get();
		String result = response.readEntity(String.class);
		return result;
	}

}
