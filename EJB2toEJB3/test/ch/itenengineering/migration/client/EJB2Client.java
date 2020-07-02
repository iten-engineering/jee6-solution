package ch.itenengineering.migration.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ch.itenengineering.migration.ejb.EJB2Home;
import ch.itenengineering.migration.ejb.EJB2Remote;

/**
 * Das Beispiel zeigt wie ein Stateless Session Bean V2.x ein Stateless Session
 * Bean V3.0/3.1 (via Local Interface) aufruft. Beide Bean's werden in der
 * gleiche EAR Datei auf den Java EE Server deployed.
 * 
 * In der ejb-jar.xml Datei wird dem Java EE Server mit der Angabe "version=3.0"
 * oder "version=3.1" mitgeteilt, dass sich nicht nur Bean's der Version 2.x in
 * der EAR Datei befinden.
 */
public class EJB2Client {

	public static Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public void sayHello() throws Exception {

		// get initial context
		Context ctx = getInitialContext();

		// get object refrence and cast to home object
		Object objref = ctx
				.lookup("ejb:EJB2toEJB3/EJB2toEJB3-ejb/EJB2Bean!ch.itenengineering.migration.ejb.EJB2Home");

		EJB2Home home = (EJB2Home) PortableRemoteObject.narrow(objref,
				EJB2Home.class);

		// get remote object (proxy)
		EJB2Remote ejb2 = home.create();

		// invoke bean method
		System.out.println(ejb2.echo("Hello EJB2"));
		System.out.println(ejb2.echoFromEJB3("Hello EJB2toEJB3"));
	}

	public static void main(String[] args) {

		try {
			// run the application
			EJB2Client client = new EJB2Client();
			client.sayHello();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end class
