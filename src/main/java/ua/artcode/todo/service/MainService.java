package ua.artcode.todo.service;

import ua.artcode.todo.model.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by serhii on 22.10.17.
 */
public interface MainService {

    Todo create(Todo todo) throws IOException;
    void initData() throws IOException;
    List<Todo> getAll() throws FileNotFoundException;
    Todo find(int id);
    Todo delete(Integer id) throws IOException;

}
