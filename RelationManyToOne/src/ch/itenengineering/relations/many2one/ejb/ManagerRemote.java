package ch.itenengineering.relations.many2one.ejb;

import javax.ejb.Remote;

@Remote
public interface ManagerRemote {

	public Object persist(Object entity);

	public Object merge(Object entity);

	public Object find(Class clazz, Object primaryKey);

	public void remove(Class clazz, Object primaryKey);

} // end
