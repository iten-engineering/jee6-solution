package ch.itenengineering.relations.client;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.itenengineering.relations.r6.domain.R6Course;
import ch.itenengineering.relations.r6.domain.R6Student;
import ch.itenengineering.relations.r6.ejb.R6ManagerRemote;

public class R6Client {

	public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		return new InitialContext(env);
	}
	
	
	public void test() throws Exception {
		R6Course c1 = new R6Course(1, "Mathematik");
		R6Course c2 = new R6Course(2, "Englisch");
		R6Course c3 = new R6Course(3, "Sport");

		R6Student s1 = new R6Student(1, "Michael Reber");
		R6Student s2 = new R6Student(2, "Claudia Nacht");
		R6Student s3 = new R6Student(3, "Jaqueline Müller");

		//
		// init
		//
		Context ctx = getInitialContext();
		R6ManagerRemote manager = (R6ManagerRemote) ctx.lookup("ejb:/Relations/R6ManagerBean!ch.itenengineering.relations.r6.ejb.R6ManagerRemote");

		manager.clear();

		//
		// add courses, students and do bookings
		//
		manager.persist(c1);
		manager.persist(c2);
		manager.persist(c3);

		manager.persist(s1);
		manager.persist(s2);
		manager.persist(s3);

		// book one course for student 1
		manager.book(s1.getId(), c3.getId());

		System.out.println("book one course for student 1:");
		System.out.println(manager.find(R6Student.class, s1.getId()));

		// book two course for student 2
		manager.book(s2.getId(), c1.getId());
		manager.book(s2.getId(), c2.getId());

		System.out.println("\nbook two course for student 2:");
		System.out.println(manager.find(R6Student.class, s2.getId()));

		// book three courses for student 3
		manager.book(s3.getId(), c1.getId());
		manager.book(s3.getId(), c2.getId());
		manager.book(s3.getId(), c3.getId());

		System.out.println("\nbook three courses for student 3:");
		System.out.println(manager.find(R6Student.class, s3.getId()));

		// cancel course two for student 3
		manager.cancel(s3.getId(), c2.getId());

		System.out.println("\ncancel course two for student 3:");
		System.out.println(manager.find(R6Student.class, s3.getId()));

	}

	public static void main(String[] args) {

		try {
			// run the application
			new R6Client().test();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class
