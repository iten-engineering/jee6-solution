package ch.itenengineering.migration.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.migration.ejb.EJB3Remote;

public class EJB3Client {

	public static Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public void sayHello() throws Exception {

		// get initial context
		Context ctx = getInitialContext();

		// get object reference
		EJB3Remote ejb3 = (EJB3Remote) ctx
				.lookup("ejb:EJB3toEJB2/EJB3toEJB2-ejb/EJB3Bean!ch.itenengineering.migration.ejb.EJB3Remote");

		// invoke bean method
		System.out.println(ejb3.echo("Hello EJB3"));
		System.out.println(ejb3.echoFromEJB2("Hello EJB3toEJB2"));
	}

	public static void main(String[] args) {

		try {
			// run the application
			EJB3Client client = new EJB3Client();
			client.sayHello();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
