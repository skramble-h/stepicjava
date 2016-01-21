package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by skramble.h
 */
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);

        if(profile == null || !profile.getPass().equals(pass)){
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            response.getWriter().print("Unauthorized");
        }else {
            accountService.addSession(request.getSession().getId(), profile);
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            //response.getWriter().println(json);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(200);
            response.getWriter().print("Authorized");
        }


    }
}
