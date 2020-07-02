package ch.itenengineering.relations.r6.ejb;

import javax.ejb.Remote;

@Remote
public interface R6ManagerRemote {

	/**
	 * Löscht alle Einträge in den DB Tabellen.
	 */
	public void clear();

	/**
	 * Bucht für den Studenten mit der angegebenen Id den gewünschten Kurs
	 * anhand der Kurs Id.
	 * 
	 * @param studentId
	 *            PK des Studenten der den Kurs buchen will.
	 * @param courseId
	 *            PK des zu buchenden Kurses.
	 */
	public void book(int studentId, int courseId);

	/**
	 * Annuliert einen gebuchten Kurs.
	 * 
	 * @param studentId
	 *            PK des Studenten der den Kurs annulieren will.
	 * @param courseId
	 *            PK des zu annulierenden Kurses.
	 */
	public void cancel(int studentId, int courseId);

	public Object persist(Object entity);

	public Object merge(Object entity);

	public Object find(Class clazz, Object primaryKey);

	public void remove(Class clazz, Object primaryKey);

} // end
