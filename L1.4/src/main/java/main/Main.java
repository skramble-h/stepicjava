package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import servlets.MirrorServlet;

/**
 * Created by skramble.h
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MirrorServlet mirrorServlet = new MirrorServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorServlet),"/mirror");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();


    }

}
