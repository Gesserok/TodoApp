package ua.artcode.todo.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import ua.artcode.todo.model.Todo;
import ua.artcode.todo.service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GetTodosHandler extends AbstractHandler {

    private MainService mainService;
    private Gson gson;

    public GetTodosHandler(MainService mainService, Gson gson) {
        this.mainService = mainService;
        this.gson = gson;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

//        response.setContentType("application/json; charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_CREATED);
        if (request.getMethod().equals("GET") && baseRequest.getOriginalURI().equals("/todos")) {
            List<Todo> todos = mainService.getAll();
            PrintWriter out = response.getWriter();
            out.println(gson.toJson(todos));
            out.flush();
            baseRequest.setHandled(true);
        }
        if (request.getMethod().equals("GET") && baseRequest.getOriginalURI().startsWith("/todos?id=")) {
            List<Todo> todos = mainService.getAll();
            Integer id = Integer.parseInt(baseRequest.getOriginalURI().split("=")[1]);

            Todo deleted = mainService.delete(todos.get(id).getId());
            System.out.println(todos.get(0));
            System.out.println(deleted.getId() + " " + deleted.getTitle() + " " + deleted.getBody());

            //            Integer integer = gson.fromJson("1", int.class);
//            System.out.println(gson.fromJson(new InputStreamReader(request.getInputStream()), itemsMapType));
//            mainService.delete(request.)


        }
    }
    private class Pair {
        private String str;
        private int id;
    }
}
