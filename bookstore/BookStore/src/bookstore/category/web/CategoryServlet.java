package bookstore.category.web;

import bookstore.category.service.CategoryService;
import bookstore.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/cate")
public class CategoryServlet extends BaseServlet{

    private CategoryService cs = new CategoryService();

    public String queryAll(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        List list = cs.queryAll();
        session.setAttribute("allBooks",list);

        return "f:/jsps/book/list.jsp";
    }




}
