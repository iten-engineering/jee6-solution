package ch.itenengineering.inheritance.i3.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.itenengineering.inheritance.i3.domain.Person;

@Stateless
public class ManagerBeanI3 implements ManagerRemoteI3 {

	@PersistenceContext(unitName = "InheritancePU")
	private EntityManager em;

	public Person add(Person person) {

		em.persist(person);

		return person;
	}

	public void deleteAll() {
		em.createQuery("delete from PersonI3").executeUpdate();
	}

	public List findAll() {
		return em.createQuery("select p from PersonI3 p").getResultList();
	}

} // end of class
