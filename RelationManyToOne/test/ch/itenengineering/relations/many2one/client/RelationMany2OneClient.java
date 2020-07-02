package ch.itenengineering.relations.many2one.client;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.many2one.domain.Address;
import ch.itenengineering.relations.many2one.domain.Company;
import ch.itenengineering.relations.many2one.ejb.ManagerRemote;

public class RelationMany2OneClient {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}
	
	public void test() throws Exception {
		
		int companyId = 1;

		//
		// init
		//
		Context ctx = getInitialContext();
		ManagerRemote manager = (ManagerRemote) ctx
				.lookup("ejb:/RelationManyToOne/ManagerBean!ch.itenengineering.relations.many2one.ejb.ManagerRemote");

		// 
		// delete existing data
		//
		manager.remove(Company.class, companyId);


		//
		// create company with two addresses
		//
		List<Address> addressList = new ArrayList<Address>();
		Company company = new Company(companyId, "Intersport",
				"Sportartikel Wiederverkaeufer", addressList);
		addressList.add(new Address("Obere Zollgasse", "75", "3072",
				"Ostermundigen", "Schweiz", company));
		addressList.add(new Address("Postfach", null, "3072", "Ostermundigen",
				"Schweiz", company));

		manager.persist(company);

		System.out.println("create company with two addresses:");
		System.out.println(manager.find(Company.class, companyId));


		// 
		// add third address with relationship to the company
		//
		company = (Company) manager.find(Company.class, companyId);

		Address address = new Address("Obere Zollgasse", "75b", "3072",
				"Ostermundigen", "Schweiz", company);

		/* Bemerkung:
		 * Das folgende Statement : manager.persist(address)
		 * - hat mit JBoss 5, Hibernate und HSQL DB funktioniert.
		 * - Mit NetBeans 6, EclipseLink und Java DB wird die Adresse zwar auch korrekt (inkl. Foreign
		 *   Key auf Company) eingefuegt, die Beziehung zu Company wird aber nicht aktualisiert.
		 * Daher wird die neue Adresse via manager.merge(company) dazugefuegt.
		 */
		company.getAddressList().add(address);
		manager.merge(company);

		System.out.println("\nadd third address with relationship to the company:");
		System.out.println(manager.find(Company.class, companyId));
	}

	public static void main(String[] args) {

		try {
			// run the application
      new RelationMany2OneClient().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
