package main;

import dao.UserDao;
import domain.User;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private List list;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            list = UserDao.showUser();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            User u = (User) list.get(i);
            if (u.getUsername().equals(username) && u.getPassword().equals(password)){
                response.sendRedirect("http://localhost:8080/index.jsp");
                System.out.println("账号: " + u.getUsername() + "\t密码: " + u.getPassword());
                getServletContext().setAttribute("username",u.getUsername());
                getServletContext().setAttribute("password",u.getPassword());

                break;
            }else {
                response.sendRedirect("http://localhost:8080/Register.html");
                break;
            }
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Object username = getServletContext().getAttribute("username");
        Object password = getServletContext().getAttribute("password");

        String username1 = (String) username;
        String password1 = (String) password;

        User user = new User();
        user.setUsername(username1);
        user.setPassword(password1);

        JSONArray jsonArray = JSONArray.fromObject(user);
        response.getWriter().write(jsonArray.toString());

    }
}
