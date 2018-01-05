package web;

import dao.BookDao;
import dao.UserDao;
import bean.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private User user;
    private HttpSession session;
    private UserDao userDao;
    private BookDao bookDao = new BookDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        userDao = new UserDao();
        String username = request.getParameter("username");
        session.setAttribute("uname",username);
        String password = request.getParameter("password");

        try {
            user = userDao.showUser(username);
            if (user != null){
                if (user.getPassword().equals(password)){
    //                System.out.println("账号: " + u.getUsername() + "\t密码: " + u.getPassword());
//                    getServletContext().setAttribute("username",user.getUsername());
//                    getServletContext().setAttribute("password",user.getPassword());

                    session.setAttribute("username",user.getUsername());
                    session.setAttribute("password",user.getPassword());

                    String uname = (String) session.getAttribute("uname");
                    Cookie username1 = new Cookie("username", uname);
                    username1.setPath("/login");
                    username1.setMaxAge(60*60);
                    response.addCookie(username1);

                    response.sendRedirect("http://localhost:8080/index.jsp");

                    try {
                        List list = bookDao.showAll();
                        System.out.println(list.toString());
                        session.setAttribute("list",list);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return;
                }else {
                    System.out.println("密码错误");
                }
            }else{
                System.out.println("账号不存在");
            }
            response.sendRedirect("http://localhost:8080/index.jsp");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        Object username = getServletContext().getAttribute("username");
//        Object password = getServletContext().getAttribute("password");

//        String username1 = (String) username;
//        String password1 = (String) password;



//        String username = (String) session.getAttribute("username");
//        String password = (String) session.getAttribute("password");
//
//        user.setUsername(username);
//        user.setPassword(password);
//        User user = new User();
//
//        System.out.println(user.toString());
//
//        JSONObject jsonObject = JSONObject.fromObject(user);
//        response.getWriter().write(jsonObject.toString());

    }
}
