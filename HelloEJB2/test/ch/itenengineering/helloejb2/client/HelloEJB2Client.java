package ch.itenengineering.helloejb2.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ch.itenengineering.helloejb2.ejb.HelloEJB2Home;
import ch.itenengineering.helloejb2.ejb.HelloEJB2Remote;

/**
 * EJB 2.x Beispiel mit Aufruf eines Stateless Session Bean.
 */
public class HelloEJB2Client {

	public static Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public static void main(String[] args) {

		try {
			// get initial context
			Context ctx = getInitialContext();

			// get object reference and cast to home object
			Object objref = ctx
					.lookup("ejb:HelloEJB2/HelloEJB2-ejb//HelloEJB2Bean!ch.itenengineering.helloejb2.ejb.HelloEJB2Home");

			HelloEJB2Home home = (HelloEJB2Home) PortableRemoteObject.narrow(
					objref, HelloEJB2Home.class);

			// get remote object (proxy)
			HelloEJB2Remote helloWorld = home.create();

			// invoke bean method
			String echo = helloWorld.echo("Hello EJB2 World");
			System.out.println("\n" + echo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end class
