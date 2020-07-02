package ch.itenengineering.inheritance.client;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.inheritance.i1.domain.Customer;
import ch.itenengineering.inheritance.i1.domain.Employee;
import ch.itenengineering.inheritance.i1.domain.Person;
import ch.itenengineering.inheritance.i1.domain.Employee.EmployeeType;
import ch.itenengineering.inheritance.i1.ejb.ManagerRemoteI1;

/**
 * JPA Sample - Inheritance
 *
 * Samples:
 * I1 - single table per class hierarchy
 * I2 - single table per concrete entity class
 * I3 - single table per subclass / joined strategy
 *
 * Entity names:
 * All entities have a unique name within the inheritance project.
 * The name is defined by the @Entity annotation.
 *   Samples:
 *
 *   Entity Name		Entity Class
 *     PersonI1		...inheritance.i1.domain.Person
 *     PersonI2		...inheritance.i2.domain.Person
 *
 * Table names:
 * Also all tables have a unique name, starting with the relationship
 * sample number (i.e. I1_PERSON, I3_PERSON, etc).
 *
 * Copyright (c) 2007-2012, iten-engineering.ch
 */
public class InheritanceClientI1 {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}
	
	
	public void i1Hierarchy() throws Exception {

		// init
		Context ctx = getInitialContext();
		ManagerRemoteI1 manager = (ManagerRemoteI1) ctx.lookup("ejb:/Inheritance/ManagerBeanI1!ch.itenengineering.inheritance.i1.ejb.ManagerRemoteI1");

		// delete existing data
		manager.deleteAll();

		// insert test data
		manager.add(new Person(10, "Thomas", "Iten"));
		manager.add(new Customer(11, "Daniel", "Schmutz", "ZFI"));
		manager.add(new Employee(12, "Willy", "Vollenweider", 1,
				EmployeeType.MANAGER));

		List list = manager.findAll();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Person element = (Person) iter.next();
			System.out.println(element);
		}
	}

	public static void main(String[] args) {

		try {
			// run the application
			new InheritanceClientI1().i1Hierarchy();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
