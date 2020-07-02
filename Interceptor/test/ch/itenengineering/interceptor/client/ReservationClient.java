package ch.itenengineering.interceptor.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.interceptor.ejb.TicketAgencyRemote;

public class ReservationClient {

	TicketAgencyRemote agency;

	public ReservationClient() throws NamingException {

		Context ctx = getInitialContext();

		this.agency = (TicketAgencyRemote) ctx
				.lookup("ejb:/Interceptor/TicketAgencyBean!ch.itenengineering.interceptor.ejb.TicketAgencyRemote");

	}

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public void reserve() throws Exception {

		// init test database
		agency.clear();

		// reserve some events
		book("Wild Markus", "Rolling Stones, Zuerich", 2);
		book("Rouvinez Jean Claude", "Hochzeitsnacht im Paradies, Leipzig", 2);
		book("Bolzli Patrick", "YB - Sion, Bern", 4);

		// maximal allowed quantity exceeded
		try {
			book("Nydegger Stefan", "Deep Purple, Lausanne", 10);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// double booking
		try {
			book("Rouvinez Jean Claude", "Hochzeitsnacht im Paradies, Leipzig",
					2);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	private void book(String customer, String event, int quantity)
			throws Exception {

		int reservationId = agency.book(customer, event, quantity);

		System.out.println(quantity + " Tickets for " + event + " reserved by "
				+ customer + " with id #" + reservationId);

	}

	public static void main(String[] args) {

		try {
			// run the application
			new ReservationClient().reserve();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
