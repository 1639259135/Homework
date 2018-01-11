package bookstore.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("method");

        Class<? extends BaseServlet> servlet = this.getClass();

        Method method = null;
        try {
            method = servlet.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            System.out.println("??????????"+methodName + method);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("找不到该方法");
        }


        try {
            String result = (String)method.invoke(this, req, resp);
            String[] split = result.split(":");
            String type = split[0];
            String path = split[1];

            if (type.equals("f")){
                req.getRequestDispatcher(path).forward(req,resp);
            }
            if (type.equals("r")){
                resp.sendRedirect(req.getContextPath() + path);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}
