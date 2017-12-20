package ua.artcode.todo;

import com.google.gson.Gson;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import ua.artcode.todo.dao.TodoDaoImp;
import ua.artcode.todo.server.AddTodoHandler;
import ua.artcode.todo.server.GetTodosHandler;
import ua.artcode.todo.server.HelloHandler;
import ua.artcode.todo.service.MainService;
import ua.artcode.todo.service.MainServiceImpl;

import java.io.File;

/**
 * Created by serhii on 22.10.17.
 */
public class RunServer {

    public static void main(String[] args) throws Exception {

        Gson gson = new Gson();
        MainService mainService = new MainServiceImpl(new TodoDaoImp(gson));

        String SERVER_PORT = System.getenv("PORT");
        if(SERVER_PORT == null){
            SERVER_PORT = "5000";
        }

        Server server = new Server(Integer.parseInt(SERVER_PORT));
        server.setRequestLog(
                (request, response) ->
                        System.out.println(request.toString() + "\n" + response));


        server.setErrorHandler(new ErrorHandler());

        HandlerList handlers = new HandlerList();

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "/todo.html" });

        ResourceHandler angular_handler = new ResourceHandler();
        angular_handler.setDirectoriesListed(true);
        angular_handler.setWelcomeFiles(new String[]{ "/todoAngular.html" });



        File resourceBase = new File(RunServer.class.getResource("/view").getFile());
        resource_handler.setResourceBase(resourceBase.getAbsolutePath());
        angular_handler.setResourceBase(resourceBase.getAbsolutePath());


        ContextHandler contextHandler1 = new ContextHandler();
        contextHandler1.setContextPath("/add-todo");
        contextHandler1.setHandler(new AddTodoHandler(mainService, gson));
        contextHandler1.setAllowNullPathInfo(true);

        ContextHandler contextHandler2 = new ContextHandler();
        contextHandler2.setContextPath("/hello");
        contextHandler2.setHandler(new HelloHandler());
        contextHandler2.setAllowNullPathInfo(true);

        ContextHandler contextHandler3 = new ContextHandler();
        contextHandler3.setContextPath("/todos");
        contextHandler3.setHandler(new GetTodosHandler(mainService, gson));
        contextHandler3.setAllowNullPathInfo(true);

        handlers.setHandlers(new Handler[]{resource_handler, contextHandler1, contextHandler2, contextHandler3});


        server.setHandler(handlers);

        server.start();
        server.join();
    }

}
