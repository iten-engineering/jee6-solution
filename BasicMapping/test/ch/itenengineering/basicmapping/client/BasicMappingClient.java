package ch.itenengineering.basicmapping.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.basicmapping.domain.Company;
import ch.itenengineering.basicmapping.domain.Name;
import ch.itenengineering.basicmapping.ejb.ManagerRemote;

public class BasicMappingClient {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}

	public void test() throws Exception {

		int companyId = 1;

		// init
		Context ctx = getInitialContext();
		ManagerRemote manager = (ManagerRemote) ctx
				.lookup("ejb:/BasicMapping/ManagerBean!ch.itenengineering.basicmapping.ejb.ManagerRemote");

		// delete existing data (if available)
		manager.remove(Company.class, companyId);

		// add company
		Name name = new Name("Zoé", "Iten");
		Company company = new Company(companyId, name);
		manager.persist(company);

		// modify company (add notes)
		company.setNotes("Heute ist ein guter Tag.");
		manager.merge(company);

		// find and show company
		System.out.println("find and show company:");
		System.out.println(manager.find(Company.class, companyId));
	}

	public static void main(String[] args) {

		try {
			// run the application
			new BasicMappingClient().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
