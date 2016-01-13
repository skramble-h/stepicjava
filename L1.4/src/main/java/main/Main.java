package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.MirrorServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

/**
 * Created by skramble.h
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MirrorServlet mirrorServlet = new MirrorServlet();
        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorServlet),"/mirror");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)),"/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)),"/signin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();



    }

}
