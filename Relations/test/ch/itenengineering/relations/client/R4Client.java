package ch.itenengineering.relations.client;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.r4.domain.R4Address;
import ch.itenengineering.relations.r4.domain.R4Company;
import ch.itenengineering.relations.r4.ejb.R4ManagerRemote;

/**
 * Relationship Beispiel R4 - ManyToOne unidirectional:
 * <ul>
 * <li>In diesem Beispiel gehoert jede Adresse zu einer Company.</li>
 * <li>Mit der Definiton einer ManyToOne Annotation auf dem company Feld der
 * Addresse wird dies angegeben.</li>
 * <li>Die Adresse ist Besitzer der Beziehung, die Firma hat keine Kenntnisse
 * darueber.</li>
 * <li>Die Bezeihung wird per default als Primaer-/Fremdschluessel Verknuepfung
 * definiert.</li>
 * <li> Auf der Datenbank gibt es je eine Tabelle fuer die Firmen und eine fuer
 * die Adressen.</li>
 *
 *
 * <li>Die Verwendung einer unidirektionalen ManyToOne Beziehung ist eher
 * selten.</li>
 * </ul>
 */
public class R4Client {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}
	
	
	public void test() throws Exception {

		int companyId = 1;
		int addressId1 = 1;
		int addressId2 = 2;

		//
		// init
		//
		Context ctx = getInitialContext();
		R4ManagerRemote manager = (R4ManagerRemote) ctx.lookup("ejb:/Relations/R4ManagerBean!ch.itenengineering.relations.r4.ejb.R4ManagerRemote");

		// delete existing data
		manager.remove(R4Address.class, addressId1);
		manager.remove(R4Address.class, addressId2);
		manager.remove(R4Company.class, companyId);

		//
		// create company
		//
		R4Company company = new R4Company(companyId, "Intersport",
				"Sportartikel Wiederverkaeufer");

		manager.persist(company);

		System.out.println("create company:");
		System.out.println(manager.find(R4Company.class, companyId));

		//
		// add addresses with relationship to the company
		//
		R4Address a1 = new R4Address(addressId1, "Obere Zollgasse", "75b",
				"3072", "Ostermundigen", "Schweiz", company);

		R4Address a2 = new R4Address(addressId2, "Obere Zollgasse", "75",
				"3072", "Ostermundigen", "Schweiz", company);

		a1 = (R4Address) manager.persist(a1);
		a2 = (R4Address) manager.persist(a2);

		System.out.println("\nadd addresses with relationship to the company:");
		System.out.println(a1.toString());
		System.out.println(a2.toString());
	}

	public static void main(String[] args) {

		try {
			// run the application
			new R4Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
