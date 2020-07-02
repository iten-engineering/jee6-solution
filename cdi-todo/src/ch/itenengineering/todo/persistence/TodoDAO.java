package ch.itenengineering.todo.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.itenengineering.todo.domain.Todo;

/**
 * Das Todo Data Access Objekt bildet die Schnittstelle zur Persistenz. Es
 * stellt alle erforderlichen CRUD Methoden zur Verfügung und kappselt die
 * Verwendung des Entity Managers.
 */
public class TodoDAO {

	@PersistenceContext(unitName = "TodoPU")
	private EntityManager em;

	public void create(Todo todo) {
		em.persist(todo);
	}

	public Todo read(Long id) {
		return em.find(Todo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Todo> readAllById() {
		Query query = em.createQuery("select t from Todo t order by t.id");
		return query.getResultList();
	}

	public void update(Todo todo) {
		em.merge(todo);
	}

	public void delete(Long id) {
		Todo todo = em.find(Todo.class, id);
		em.remove(todo);
	}

} // end
