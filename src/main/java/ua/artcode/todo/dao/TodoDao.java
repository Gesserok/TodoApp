package ua.artcode.todo.dao;

import ua.artcode.todo.model.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by serhii on 22.10.17.
 */
public interface TodoDao {

    Todo create(Todo todo) throws IOException;
    List<Todo> all() throws FileNotFoundException;
    Todo find(int id);
}
