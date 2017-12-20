package ua.artcode.todo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.artcode.todo.model.Todo;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by serhii on 22.10.17.
 */
public class TodoDaoImp implements TodoDao {
    private Gson gson;
    private File todosFile;
    private Map<Integer, Todo> map;
    private Type itemsMapType = new TypeToken<Map<Integer, Todo>>() {}.getType();

    public TodoDaoImp(Gson gson) throws IOException {

        this.gson = gson;
        this.todosFile = new File("\\Projects\\Java\\Gesserok\\TodoApp\\src\\main\\resources\\json\\todos.json");
        FileWriter fw = null;
        if (!this.todosFile.exists()) {
            this.todosFile.createNewFile();
            fw = new FileWriter(this.todosFile);
            fw.write("{}");
        fw.flush();
        }
        if (fw != null) fw.close();
        this.map = /*new HashMap<>();*/
                gson.fromJson(new FileReader(this.todosFile), itemsMapType);

        System.out.println(this.map.size());
    }

    @Override
    public Todo create(Todo todo) throws IOException {
        // todo replace
        todo.setId(new Random().nextInt(5000));
        map.put(todo.getId(), todo);
        FileWriter fw = new FileWriter(this.todosFile);
        fw.write(gson.toJson(map));
        fw.flush();
        return todo;
    }

    @Override
    public List<Todo> all() throws FileNotFoundException {
        this.map = gson.fromJson(new FileReader(this.todosFile), itemsMapType);
        System.out.println(map.size());
        return new ArrayList<>(map.values());
    }

    @Override
    public Todo find(int id) {
        return map.get(id);
    }

    @Override
    public Todo delete(Integer id) throws IOException {
        Todo todo = map.remove(id);
        FileWriter fileWriter = new FileWriter(todosFile);
        fileWriter.write(gson.toJson(map));
        fileWriter.flush();
        return todo;
    }
}
