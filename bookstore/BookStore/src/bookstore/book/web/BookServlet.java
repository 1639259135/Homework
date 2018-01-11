package bookstore.book.web;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/book")
public class BookServlet extends BaseServlet {

    private BookService bs = new BookService();

    public String queryByJavaSE(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        List list = bs.queryByCategory("JavaSE");
        session.setAttribute("JavaSE",list);
        session.removeAttribute("JavaEE");
        session.removeAttribute("Javascript");
        session.removeAttribute("allBooks");

        return "f:/jsps/book/list.jsp";
    }

    public String queryByJavaEE(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        List list = bs.queryByCategory("JavaEE");
        session.setAttribute("JavaEE",list);
        session.removeAttribute("JavaSE");
        session.removeAttribute("Javascript");
        session.removeAttribute("allBooks");

        return "f:/jsps/book/list.jsp";
    }

    public String queryByJavascript(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        List list = bs.queryByCategory("Javascript");
        session.setAttribute("Javascript",list);
        session.removeAttribute("JavaEE");
        session.removeAttribute("JavaSE");
        session.removeAttribute("allBooks");

        return "f:/jsps/book/list.jsp";
    }


    public String queryByBid(HttpServletRequest request,HttpServletResponse response){

        HttpSession session = request.getSession();

        String bid = request.getParameter("bid");
        Book book = bs.queryByBid(bid);
        session.setAttribute("book",book);

        return "f:/jsps/book/desc.jsp";
    }












}
