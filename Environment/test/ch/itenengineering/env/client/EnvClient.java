package ch.itenengineering.env.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.env.ejb.EnvRemote;

public class EnvClient {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public static void main(String[] args) {

		try {
			// init
			Context ctx = getInitialContext();
			EnvRemote env = (EnvRemote) ctx
					.lookup("ejb:/Environment/EnvBean!ch.itenengineering.env.ejb.EnvRemote");

			// get environment entries (messages)
			System.out.println(env.getMessages());

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

} // end of class
