package ch.itenengineering.inheritance.i1.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.itenengineering.inheritance.i1.domain.Person;

@Stateless
public class ManagerBeanI1 implements ManagerRemoteI1 {

	@PersistenceContext(unitName = "InheritancePU")
	private EntityManager em;

	public Person add(Person person) {

		em.persist(person);

		return person;
	}

	public void deleteAll() {
		em.createQuery("delete from PersonI1").executeUpdate();
	}

	public List findAll() {
		return em.createQuery("select p from PersonI1 p").getResultList();
	}

} // end of class
