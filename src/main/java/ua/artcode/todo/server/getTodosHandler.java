package ua.artcode.todo.server;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import ua.artcode.todo.model.Todo;
import ua.artcode.todo.service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class getTodosHandler extends AbstractHandler {

    private MainService mainService;
    private Gson gson;

    public getTodosHandler(MainService mainService, Gson gson) {
        this.mainService = mainService;
        this.gson = gson;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

//        response.setContentType("application/json; charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_CREATED);

        List<Todo> todos = mainService.getAll();
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(todos));
        out.flush();
        baseRequest.setHandled(true);

    }
}
