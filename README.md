What is this repository for? PErsonal project 1.0

Technologies used Eclipse JAX-WS Maven Tomcat Jquery

How do I get set up? Summary of set up Download the code and open the project with Eclipse Wait a few seconds so that Maven retrieves the libararies. You can alternatively right click on project and update Maven. clean the project. Add the project to the tomcat server. start the server. open the browser and type the following url (make sure you are aware of your port number):http://localhost:8080/registrationresource/RegistrationForm.html

Configuration: No specific configuration.

Dependencies: Maven takes care of that.

Database configuration: none

How to run tests: 1) you can launch the url mentioned earlier and submit data with the form. the UI will be displaying messages after Submitting the form. 2) under Java resource->src->com.test there are 2 unit test files. 3) the list of blakcListed users is commented under com.test.ExclusionService.java

Deployment instructions: Just need to make sure that Tomcat adds the project. (right click on Tomcat: Add/Remove)
