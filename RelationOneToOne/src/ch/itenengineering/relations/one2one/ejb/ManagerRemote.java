package ch.itenengineering.relations.one2one.ejb;

import javax.ejb.Remote;

@Remote
public interface ManagerRemote {

	/**
	 * F�gt eine neue Entit�t in die Datenbank und gibt die eingef�gte Entit�t
	 * zur�ck an den Aufrufer. Die R�ckgabe ist vor allem praktisch, falls der
	 * Prim�rschl�ssel automatisch generiert wird. In diesem Fall beinhaltet das
	 * zur�ckgegebene Objekt den Prim�rschl�ssel.
	 * 
	 * @param entity
	 *            Entit�t die eingef�gt werden soll
	 * @return Eingef�gte Entit�t inklusive Prim�rschl�ssel
	 */
	public Object persist(Object entity);

	/**
	 * Aktualisiere den Persistence Kontext mit den Werten der �bergebenen
	 * Entit�t.
	 * 
	 * @param entity
	 *            Enti�t mit aktuellen Werten
	 * @return Instanz mit aktulisierten Werten
	 */
	public Object merge(Object entity);

	/**
	 * Suche eine Entit�t anhand der Prim�rschl�ssel.
	 * 
	 * @param clazz
	 *            Class Objekt der Entit�t
	 * @param primaryKey
	 *            Prim�rschl�ssel der Entit�t
	 * @return Instanz der gefundenen Entit�t oder null falls keine Eintrag
	 *         gefunden wurde
	 */
	@SuppressWarnings("unchecked")
	public Object find(Class clazz, Object primaryKey);

	/**
	 * L�sche den Datenbankeintrag mit dem angegebenen Prim�rschl�ssel.
	 * 
	 * @param clazz
	 *            Class Objekt der Entit�t die auf der DB gel�scht werden soll
	 * @param primaryKey
	 *            Prim�rschl�ssel
	 */
	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Object primaryKey);

} // end
