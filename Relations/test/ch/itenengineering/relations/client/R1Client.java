package ch.itenengineering.relations.client;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.r1.domain.R1Address;
import ch.itenengineering.relations.r1.domain.R1Company;
import ch.itenengineering.relations.r1.ejb.R1ManagerRemote;

/**
 * Relationship Beispiel R1 - OneToOne unidirectional:
 * <ul>
 * <li>Eine Firma kann 0..1 Adressen beinhalten.</li>
 * <li>Die Firma ist Besitzer der Beziehung.</li>
 * <li>Die Addressen wissen nicht, dass Sie zu einer Firma gehoeren koennen.</li>
 * <li> Auf der Datenbank gibt es je eine Tabelle fuer die Firmen und eine fuer
 * die Adressen.</li>
 * <li> Die Bezeihung wird mit einem Foreign Key von der Firmen auf die Adressen
 * Tabelle definiert.</li>
 * </ul>
 */
public class R1Client {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}
	
	public void test() throws Exception {

		int companyId1 = 1;
		int companyId2 = 2;

		//
		// init
		//
		Context ctx = getInitialContext();
		R1ManagerRemote manager = (R1ManagerRemote) ctx.lookup("ejb:/Relations/R1ManagerBean!ch.itenengineering.relations.r1.ejb.R1ManagerRemote");

		// delete existing data (if available)
		manager.remove(R1Company.class, companyId1);
		manager.remove(R1Company.class, companyId2);

		//
		// add company with an address
		//
		R1Address a1 = new R1Address("Ryf", "89", "3280", "Murten", "Schweiz");
		R1Company c1 = new R1Company(companyId1, "BISE NOIRE",
				"Surfcenter Murten", a1);

		c1 = (R1Company) manager.persist(c1);

		System.out.println("add company with an address:");
		System.out.println(c1);

		//
		// modifiy address an save changes
		//
		c1.getAddress().setStreetNo("98a");
		manager.merge(c1);

		System.out.println("\nmodify company's address (street no):");
		System.out.println(manager.find(R1Company.class, companyId1));

		//
		// add company without an address
		//
		R1Company c2 = new R1Company(companyId2, "Intersport",
				"Sportartikel Wiederverkaeufer", null);

		manager.persist(c2);

		System.out.println("\nadd company without an address:");
		System.out.println(manager.find(R1Company.class, companyId2));
	}

	public static void main(String[] args) {

		try {
			// run the application
			new R1Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
