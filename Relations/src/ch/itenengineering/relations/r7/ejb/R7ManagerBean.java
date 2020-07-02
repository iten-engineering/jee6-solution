package ch.itenengineering.relations.r7.ejb;

import ch.itenengineering.relations.r7.domain.R7Course;
import ch.itenengineering.relations.r7.domain.R7Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class R7ManagerBean implements R7ManagerRemote {

	@PersistenceContext(unitName = "RelationsPU")
	private EntityManager em;

	public void clear() {
		em.createNativeQuery("delete from r7_student_course").executeUpdate();
		em.createQuery("delete from R7Student").executeUpdate();
		em.createQuery("delete from R7Course").executeUpdate();
	}

	public void book(int studentId, int courseId) {

		/*
		 * Bemerkung:
		 * 
		 * Das folgende native Query mit Parametern funktioniert mit JBoss 5, Hibernate und HSQL DB:
		 *     Query query = em.createNativeQuery
		 *         ("insert into r7_student_course (student_id, course_id) values (:sid, :cid)");
		 *     query.setParameter("sid", studentId);
		 *     query.setParameter("cid", courseId);
		 *     query.executeUpdate();
		 * 
		 * Mit Netbeans, Eclipse Link und Java DB erhält man folgende Fehlermeldung:
		 *     java.sql.SQLSyntaxErrorException: 
		 *     VALUES clause must contain at least one element. Empty elements are not allowed
		 * 
		 * Als Workaround wird daher ein native Query ohne Parameter verwendet:
		 */
		Query query = em.createNativeQuery
				("insert into r7_student_course (student_id, course_id) values (" + studentId + ", " + courseId +")");
		query.executeUpdate();
		
		
		// Alternative Lösung via JPA:
		//
		//		// get managed course instance
		//		R7Course course = (R7Course) find(R7Course.class, courseId);
		//
		//		// get managed student
		//		R7Student student = (R7Student) find(R7Student.class, studentId);
		//
		//		// book course
		//		course.getStudentList().add(student);
		//		student.getCourses().add(course);
		//		merge(student);

	}

	public void cancel(int studentId, int courseId) {
		Query query = em
				.createNativeQuery("delete from r7_student_course where student_id=:sid and course_id=:cid");
		query.setParameter("sid", studentId);
		query.setParameter("cid", courseId);
		query.executeUpdate();
	}

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
