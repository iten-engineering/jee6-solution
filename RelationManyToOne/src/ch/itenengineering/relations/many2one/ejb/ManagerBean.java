package ch.itenengineering.relations.many2one.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ManagerBean implements ManagerRemote {

	@PersistenceContext(unitName = "RelationMany2OnePU")
	private EntityManager em;

	@Override
	public Object persist(Object entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public Object merge(Object entity) {
		return em.merge(entity);
	}

	@Override
	public Object find(Class clazz, Object primaryKey) {
		return em.find(clazz, primaryKey);
	}

	@Override
	public void remove(Class clazz, Object primaryKey) {
		Object obj = find(clazz, primaryKey);
		if (obj != null) {
			em.remove(obj);
		}
	}

} // end of class
