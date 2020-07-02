package ch.itenengineering.relations.r6.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.itenengineering.relations.r6.domain.R6Course;
import ch.itenengineering.relations.r6.domain.R6Student;
import javax.persistence.Query;

@Stateless
public class R6ManagerBean implements R6ManagerRemote {

	public void clear() {
		em.createNativeQuery("delete from r6_student_course").executeUpdate();
		em.createQuery("delete from R6Student").executeUpdate();
		em.createQuery("delete from R6Course").executeUpdate();
	}

	public void book(int studentId, int courseId) {
		/*
		 * Alternative Lösung mit native Query siehe Beispiel R7.
		 */

		// get managed course instance
		R6Course course = (R6Course) find(R6Course.class, courseId);

		// get managed student
		R6Student student = (R6Student) find(R6Student.class, studentId);

		// book course
		student.getCourses().add(course);
		merge(student);
	}

	public void cancel(int studentId, int courseId) {
		// get managed course instance
		R6Course course = (R6Course) find(R6Course.class, courseId);

		// get managed student
		R6Student student = (R6Student) find(R6Student.class, studentId);

		// cancel course
		student.getCourses().remove(course);
		merge(student);
	}

	@PersistenceContext(unitName = "RelationsPU")
	private EntityManager em;

	public Object persist(Object entity) {
		em.persist(entity);
		return entity;
	}

	public Object merge(Object entity) {
		return em.merge(entity);
	}

	@SuppressWarnings("unchecked")
	public Object find(Class clazz, Object primaryKey) {
		return em.find(clazz, primaryKey);
	}

	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Object primaryKey) {
		Object obj = find(clazz, primaryKey);
		if (obj != null) {
			em.remove(obj);
		}
	}

} // end of class
