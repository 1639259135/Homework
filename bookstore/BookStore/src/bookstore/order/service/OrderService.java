package bookstore.order.service;

import bookstore.book.domain.Book;
import bookstore.order.dao.OrderDao;
import bookstore.order.domain.Order;
import bookstore.order.domain.OrderItem;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private OrderDao od = new OrderDao();
    private OrderItemService oise = new OrderItemService();


    public void addOrder(Order order){

        od.insertOrder(order);
    }

    public Map<Order,Map<OrderItem, Book>> queryAll(String uid){

        try {
            List orders = od.queryAll(uid);


            Map<Order,Map<OrderItem, Book>> orderListMap = new HashMap<>();
            for (Object o : orders) {
                Order order = (Order) o;
                String oid = order.getOid();
                Map<OrderItem, Book> orderItemMap = oise.queryByOid(oid);

                orderListMap.put(order,orderItemMap);
            }



            return orderListMap;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void removeOrder(String oid){

        od.removeOrder(oid);

    }

    public void alterOrder(String oid,String address){

        try {
            od.alterOrder(oid,address);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





}
