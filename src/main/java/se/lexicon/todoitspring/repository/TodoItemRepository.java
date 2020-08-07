package se.lexicon.todoitspring.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.todoitspring.entity.TodoItem;



public interface TodoItemRepository extends CrudRepository<TodoItem, String> {
}
