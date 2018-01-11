package bookstore.user.web;

import bookstore.cart.domain.Cart;
import bookstore.user.domain.User;
import bookstore.utils.BaseServlet;
import bookstore.utils.SendMail;
import org.apache.commons.beanutils.BeanUtils;
import bookstore.user.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    private UserService us = new UserService();
    private Map<String, String[]> parameterMap;
    private User user = new User();
    private HttpSession session;

    public String login(HttpServletRequest request,HttpServletResponse response){

        session = request.getSession();
        parameterMap = request.getParameterMap();

        try {
            BeanUtils.populate(user, parameterMap);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

       User u = us.login(user);
       if (u != null){
           if (u.getState() == 1){

               Cookie cookie = new Cookie("username", u.getUsername());
               cookie.setMaxAge(60 * 60);
               response.addCookie(cookie);

               session.setAttribute("login","SUCCESS LOGIN");
               session.setAttribute("username",u.getUsername());
               session.setAttribute("uid",u.getUid());
               session.setAttribute("cart",new Cart());

               return "f:/jsps/msg.jsp";
           }
       }

       return "r:/jsps/user/login.jsp";

   }


   public String register(HttpServletRequest request,HttpServletResponse response) throws MessagingException {

       parameterMap = request.getParameterMap();

       try {
           BeanUtils.populate(user,parameterMap);
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }
//        if (user != null){
            String uid = UUID.randomUUID().toString().replace("-", "");
            String uuid = UUID.randomUUID().toString().replace("-", "") + uid;
            user.setCode(uuid);
            user.setUid(uid);

            us.register(user);

            SendMail sm = new SendMail();
//       System.out.println(user.getCode() + "========");
            sm.sendMail(user.getEmail(),user.getCode());
//       System.out.println(user.getEmail()+user.getCode());

            session.setAttribute("register","SUCCESS REGISTER");
            return "r:/jsps/msg.jsp";
//        }


//        return "f:/jsps/user/register.jsp";

   }

    public String active(HttpServletRequest request,HttpServletResponse response) {

        String code = request.getParameter("code");
//        System.out.println("==================="+code);
        String result = us.alterUser(code);
        System.out.println(result);

        if (result != null){
            return "r:/jsps/user/login.jsp";
        }
        return "f:/jsps/user/register.jsp";
    }


    public String exit(HttpServletRequest request,HttpServletResponse response){

        session.removeAttribute("login");
        session.removeAttribute("register");

        return "r:/jsps/main.jsp";
    }

}
