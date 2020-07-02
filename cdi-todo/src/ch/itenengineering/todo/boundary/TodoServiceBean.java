package ch.itenengineering.todo.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ch.itenengineering.todo.domain.Todo;
import ch.itenengineering.todo.persistence.TodoDAO;

/**
 * Als Facade zur Business Logik dient die Stateless Session Bean TodoService.
 * Hier erfolgt auch die Transaktionssteuerung und das Exception Handling.
 * 
 * Der Persistence Service wird dabei auf einfache Art und Weise per Injection
 * vor dem ersten Business Call zur Verfügung gestellt.
 */
@Stateless
public class TodoServiceBean implements TodoService {

	@Inject
	TodoDAO dao;

	public List<Todo> list() {
		return dao.readAllById();
	}

	public void save(Todo todo) {
		dao.create(todo);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

}
