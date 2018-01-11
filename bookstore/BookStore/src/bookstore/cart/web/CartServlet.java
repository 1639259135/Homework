package bookstore.cart.web;

import bookstore.book.domain.Book;
import bookstore.cart.domain.Cart;
import bookstore.cart.domain.CartItem;
import bookstore.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet(name = "CartServlet",urlPatterns = "/cart")
public class CartServlet extends BaseServlet {

    private Map<String,CartItem>  map = new HashMap<>();

    public String addToCartItem(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        session = request.getSession();
        CartItem ci = new CartItem();

        Book book = (Book) session.getAttribute("book");
        String count = request.getParameter("count");
        int co = Integer.parseInt(count);

        if (map.size() > 0){
            for (String s : map.keySet()) {
                if (s.equals(book.getBid())){
                    CartItem cartItem = map.get(s);
                    co = cartItem.getCount() + co;
                }
            }
        }
        ci.setBook(book);
        ci.setCount(co);
        map.put(book.getBid(),ci);


        return "f:/jsps/book/list.jsp";
    }

    public String addToCart(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        if (session.getAttribute("login") != null){
            Cart cart = (Cart) session.getAttribute("cart");
            cart.setCartMap(map);
            session.setAttribute("cartMap",map);
            session.setAttribute("cart",cart);

            return "f:/jsps/cart/list.jsp";
        }

        return "r:/jsps/user/login.jsp";
    }


    public String removeCartItem(HttpServletRequest request, HttpServletResponse response){

        String bid = request.getParameter("bid");

        Iterator<String> iter = map.keySet().iterator();

        while(iter.hasNext()) {
             String key = iter.next();
            if (key.equals(bid)) {
                iter.remove();
            }
        }

        return "f:/jsps/cart/list.jsp";
    }


    public String removeCart(HttpServletRequest request, HttpServletResponse response){

        if (map.size() > 0){
            map.clear();
        }

        return "f:/jsps/cart/list.jsp";
    }



}
