package ch.itenengineering.helloworld.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.helloworld.ejb.HelloWorldRemote;

public class HelloEJB31Client {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public static void main(String[] args) {
		try {
			// get initial context
			Context ctx = getInitialContext();

			// get object reference
			HelloWorldRemote helloWorld = (HelloWorldRemote) ctx
					.lookup("ejb:/HelloEJB31//HelloWorldBean!ch.itenengineering.helloworld.ejb.HelloWorldRemote");

			// invoke bean method
			String echo = helloWorld
					.helloWorld("Hello EJB 3.1 World from standalone Client");
			System.out.println("\n" + echo);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
