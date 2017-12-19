package ua.artcode.todo.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.artcode.todo.dao.TodoDao;
import ua.artcode.todo.model.Todo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by serhii on 22.10.17.
 */
public class MainServiceTest {

    private MainService mainService;
    private TodoDao mockTodo;

    @Before
    public void setUp() throws Exception {

        mockTodo = mock(TodoDao.class);
        when(mockTodo.create(any(Todo.class))).thenReturn(new Todo());
        when(mockTodo.all()).thenReturn(Arrays.asList(new Todo(1, "t", "b", false)));
        when(mockTodo.find(anyInt())).thenReturn(new Todo());


        mainService = new MainServiceImpl(mockTodo);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() throws Exception {
        Todo todo = mainService.create(new Todo());
        assertNotNull(todo);
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void find() throws Exception {
    }

    @Test
    public void initData() throws IOException {

        mainService.initData();

        // assert
        verify(mockTodo, times(3)).create(any());
    }

}