package ch.itenengineering.migration.ejb;

import javax.ejb.Stateless;

@Stateless
public class EJB3Bean implements EJB3Local {

	public String echo(String message) {

		return "echo from EJB3Bean - message <" + message + "> received!";
	}

}
