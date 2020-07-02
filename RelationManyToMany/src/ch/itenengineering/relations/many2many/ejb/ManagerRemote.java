package ch.itenengineering.relations.many2many.ejb;

import javax.ejb.Remote;

@Remote
public interface ManagerRemote {

	/**
	 * L�scht alle Eintr�ge in den DB Tabellen.
	 */
	public void clear();

	/**
	 * Bucht f�r den Studenten mit der angegebenen Id den gew�nschten Kurs
	 * anhand der Kurs Id.
	 * 
	 * @param studentId
	 *            Prim�rschl�ssel des Studenten der den Kurs buchen will.
	 * @param courseId
	 *            Prim�rschl�ssel des zu buchenden Kurses.
	 */
	public void book(int studentId, int courseId);

	/**
	 * Annuliert einen gebuchten Kurs.
	 * 
	 * @param studentId
	 *            Prim�rschl�ssel des Studenten der den Kurs annulieren will.
	 * @param courseId
	 *            Prim�rschl�ssel des zu annulierenden Kurses.
	 */
	public void cancel(int studentId, int courseId);

	public Object persist(Object entity);

	public Object merge(Object entity);

	@SuppressWarnings("unchecked")
	public Object find(Class clazz, Object primaryKey);

	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Object primaryKey);

} // end
