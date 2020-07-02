package ch.itenengineering.helloejb3.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.helloejb3.ejb.HelloEJB3Bean;
import ch.itenengineering.helloejb3.ejb.HelloEJB3Remote;

/**
 * EJB 3.0 Beispiel mit Aufruf eines Stateless Session Bean via Remote
 * Interface.
 */
public class HelloEJB3Client {

	private static Context getInitialContext() throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();

		// JBoss 7 (+ jboss-ejb-client.properties file in the classpath)
		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		// JBoss 6
		// env.put(Context.INITIAL_CONTEXT_FACTORY,
		// "org.jnp.interfaces.NamingContextFactory");
		// env.put(Context.URL_PKG_PREFIXES,
		// "org.jboss.naming;org.jnp.interfaces");
		// env.put(Context.PROVIDER_URL, "jnp://localhost:1099");

		return new InitialContext(env);
	}

	/**
	 * Aufbau JNDI Name beim JBoss 7.
	 */
	private static String getLookupName() {

		// The app name is the EAR name of the deployed EJB without .ear suffix.
		String appName = "HelloEJB3";

		// The module name is the JAR name of the deployed EJB without the .jar
		// suffix.
		String moduleName = "HelloEJB3-ejb";

		// AS7 allows each deployment to have an (optional) distinct name.
		// This can be an empty string if distinct name is not specified.
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = HelloEJB3Bean.class.getSimpleName();

		// The fully qualified remote interface name
		String interfaceName = HelloEJB3Remote.class.getName();

		// Create a look up string name
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;

		return name;
	}

	public static void main(String[] args) {

		try {

			// get initial context
			Context ctx = getInitialContext();

			// get object reference
			HelloEJB3Remote helloEJB3 = (HelloEJB3Remote) ctx
					.lookup(getLookupName());

			// invoke bean method
			String echo = helloEJB3.helloWorld("Hello EJB3 World");
			System.out.println("\n" + echo);

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

} // end of class
