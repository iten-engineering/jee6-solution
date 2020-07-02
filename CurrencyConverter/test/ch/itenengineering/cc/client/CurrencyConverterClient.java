package ch.itenengineering.cc.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.cc.domain.CurrencyType;
import ch.itenengineering.cc.ejb.CurrencyConverterRemote;

public class CurrencyConverterClient {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public void convert() throws NamingException {

		double value = 12.5;
		double result;

		// get initial context
		Context ctx = getInitialContext();

		// get object reference
		CurrencyConverterRemote currencyConverter = (CurrencyConverterRemote) ctx
				.lookup("ejb:/CurrencyConverter/CurrencyConverterBean!ch.itenengineering.cc.ejb.CurrencyConverterRemote");

		// invoke bean method
		result = currencyConverter.convertCHF(value, CurrencyType.EUR);
		System.out.println("\nCurrencyConverter");
		System.out.println("  CHF " + value + " = EUR " + result);

		result = currencyConverter.convertCHF(12.5, CurrencyType.USD);
		System.out.println("  CHF " + value + " = USD " + result);

	}

	public static void main(String[] args) {

		try {
			// run the application
			new CurrencyConverterClient().convert();

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

} // end of class
