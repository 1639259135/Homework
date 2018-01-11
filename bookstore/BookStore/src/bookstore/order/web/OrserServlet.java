package bookstore.order.web;

import bookstore.book.domain.Book;
import bookstore.cart.domain.Cart;
import bookstore.cart.domain.CartItem;
import bookstore.cart.web.CartServlet;
import bookstore.order.dao.OrderDao;
import bookstore.order.dao.OrderItemDao;
import bookstore.order.domain.Order;
import bookstore.order.domain.OrderItem;
import bookstore.order.service.OrderItemService;
import bookstore.order.service.OrderService;
import bookstore.user.domain.User;
import bookstore.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrserServlet extends BaseServlet {

    private Order order = new Order();
    private OrderService orderService = new OrderService();
    private OrderItem orderItem = new OrderItem();
    private OrderItemService oise = new OrderItemService();

    public String addOrder(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        session.setAttribute("time",time);

        order.setOrderTime(time);

        List<Character> chars = new ArrayList<>();
        for (char i = 97; i <= 122; i++) {
            chars.add(i);
        }
        String random = "";
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * chars.size());
            random += String.valueOf(chars.get(index));
        }
        String username = (String) session.getAttribute("username");
        if (username.length() >= 4){
            String strsub = username.substring(username.length() - 4);
            session.setAttribute("oid",random + strsub);
        }else{
            session.setAttribute("oid",random + username);
        }

        String oid = (String) session.getAttribute("oid");
        order.setOid(oid);
        order.setUid((String) session.getAttribute("uid"));
        Double pay = (Double) session.getAttribute("pay");
        order.setPrice(pay);
        order.setState(0);
        session.removeAttribute("subtotal");

        if (order.getPrice() > 0){
            orderService.addOrder(order);

            Cart cart = (Cart) session.getAttribute("cart");
            Map<String, CartItem> cartMap = cart.getCartMap();
            for (String s : cartMap.keySet()) {
                String iid = UUID.randomUUID().toString().replace("-", "");
                CartItem cartItem = cartMap.get(s);
                orderItem.setCount(cartItem.getCount());
                orderItem.setBid(cartItem.getBook().getBid());
                double subtotal = cartItem.getBook().getPrice() * cartItem.getCount();
                orderItem.setSubtotal(subtotal);
                orderItem.setOid(order.getOid());
                orderItem.setIid(iid);

                oise.insertOrderItem(orderItem);
            }

            session.setAttribute("key",order);
            session.setAttribute("value",cartMap);

        }

        Map cartMap = (Map) session.getAttribute("cartMap");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cartMap.size() > 0){
            cartMap.clear();
        }
        cart.setCartMap(cartMap);
        session.setAttribute("cart",cart);


        return "f:/jsps/order/desc.jsp";
    }


    public String queryAllByUid(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        session.removeAttribute("key");
        session.removeAttribute("value");

        String uid = (String) request.getSession().getAttribute("uid");
        Map<Order, Map<OrderItem, Book>> orderListMap = orderService.queryAll(uid);

        session.setAttribute("orderListMap",orderListMap);

        return "f:/jsps/order/list.jsp";
    }


    public String removeOrder(HttpServletRequest request, HttpServletResponse response){
        String oid = request.getParameter("oid");

        oise.removeOrderItemByOid(oid);
        orderService.removeOrder(oid);

        queryAllByUid(request,response);


        return "f:/jsps/order/list.jsp";
    }


    public String payOrder(HttpServletRequest request, HttpServletResponse response){

        String address = request.getParameter("address");
        String oid = request.getParameter("oid");

        orderService.alterOrder(oid,address);

        queryAllByUid(request,response);

        return "f:/jsps/order/list.jsp";
    }


    public String directPayOrder(HttpServletRequest request,HttpServletResponse response){

        addOrder(request, response);

        return "f:/jsps/order/list.jsp";
    }







}
