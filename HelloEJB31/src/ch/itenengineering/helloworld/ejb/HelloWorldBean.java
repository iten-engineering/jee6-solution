package ch.itenengineering.helloworld.ejb;

import javax.ejb.Stateless;

@Stateless
public class HelloWorldBean implements HelloWorldRemote {

	@Override
	public String helloWorld(String greeting) {

		String result = "Echo from EJB: Greeting <" + greeting
				+ "> successfully received!";

		return result;
	}

} // end
