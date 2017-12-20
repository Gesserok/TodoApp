package ua.artcode.todo.service;

import ua.artcode.todo.dao.TodoDao;
import ua.artcode.todo.model.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by serhii on 22.10.17.
 */
public class MainServiceImpl implements MainService {

    private TodoDao todoDao;

    public MainServiceImpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public Todo create(Todo todo) throws IOException {

        if(todo == null){
            return null;
        }

        return todoDao.create(todo);
    }

    @Override
    public void initData() throws IOException {
        todoDao.create(new Todo(1, "", "", false));
        todoDao.create(new Todo(2, "", "", false));
        todoDao.create(new Todo(3, "", "", false));
    }

    @Override
    public List<Todo> getAll() throws FileNotFoundException {
        return todoDao.all();
    }

    @Override
    public Todo find(int id) {
        return todoDao.find(id);
    }

    @Override
    public Todo delete(Integer id) throws IOException {
        return todoDao.delete(id);
    }
}
