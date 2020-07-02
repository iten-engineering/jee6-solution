package ch.itenengineering.migration.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ch.itenengineering.migration.ejb.EJB2Home;
import ch.itenengineering.migration.ejb.EJB2Remote;

/**
 * Migrationsbeispiel mit EJB 2.x Client und EJB3 Bean. Dem Client wird ein Home
 * und Remote Interface nach EJB 2.x zur Verfuegung gestellt. Das Bean wird
 * gemaess EJB 3 erstellt mit folgenden Anpassungen:
 * 
 * <ol>
 * <li>Die Bean Klasse wird mit @RemoteHome(EJB2Home.class) annotiert, um dem
 * Container mitzuteilen, dass das Bean ueber ein Home Interface verfuegt.</li>
 * <li>Das Bean implementiert das Remote Interface nicht, da es sonst auch alle
 * Callback Methoden gemaess EJB 2.x zur Verfuegung stellen muesste.</li>
 * </ol>
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
				.lookup("ejb:EJB2ClientToEJB3/EJB2ClientToEJB3-ejb/EJB3Bean!ch.itenengineering.migration.ejb.EJB2Home");

		EJB2Home home = (EJB2Home) PortableRemoteObject.narrow(objref,
				EJB2Home.class);

		// get remote object (proxy)
		EJB2Remote remote = home.create();

		// invoke bean method
		System.out.println(remote.echo("Hello EJB3 Bean"));
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
