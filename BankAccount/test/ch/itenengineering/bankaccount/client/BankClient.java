package ch.itenengineering.bankaccount.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.bankaccount.ejb.AccountManagerRemote;

public class BankClient {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public void testAccount() throws Exception {

		// init
		Context ctx = getInitialContext();

		//
		// test stateful account
		//
		AccountManagerRemote am = (AccountManagerRemote) ctx
				.lookup("ejb:/BankAccount/AccountManagerBean!ch.itenengineering.bankaccount.ejb.AccountManagerRemote?stateful");

		am.open(7);
		am.setOwner("James Bond");

		// check database:
		// data will be persistet after each end of transaction scope, i.e.
		// after each function call
		// pause();

		am.deposit(100.0);

		System.out.println("\ncreated/updated account: ");
		System.out.println(am.getBalance());

		am.close();

		//
		// some more tests
		//
		am = (AccountManagerRemote) ctx
				.lookup("ejb:/BankAccount/AccountManagerBean!ch.itenengineering.bankaccount.ejb.AccountManagerRemote?stateful");
		am.open(7);
		Double amount = am.getBalance();

		System.out.println("\nexisting account: ");
		System.out.println(amount);

		try {
			Double amount2 = amount * 2;
			System.out.println("\ntry to withdraw " + amount2);
			am.withdraw(amount2);
		} catch (EJBException e) {
			// transaction is rolled back, and bean destroyed
			System.out.println(e.toString());
		}

	}

	@SuppressWarnings("unused")
	private void pause() {

		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.print("Press key to continue...");
			stdin.readLine();

		} catch (Exception ex) {
			// ignore
		}
	} // end method

	public static void main(String[] args) {

		try {
			// run the application
			new BankClient().testAccount();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
