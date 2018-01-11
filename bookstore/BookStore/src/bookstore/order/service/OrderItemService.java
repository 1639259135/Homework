package bookstore.order.service;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.order.dao.OrderItemDao;
import bookstore.order.domain.OrderItem;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItemService {

    private OrderItemDao orderItemDao = new OrderItemDao();
    private BookService bs = new BookService();

    public void insertOrderItem(OrderItem orderItem){

        try {
            orderItemDao.insertOrderItem(orderItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Map<OrderItem,Book> queryByOid(String oid){

        try {
            List list = orderItemDao.queryByOid(oid);

            Map<OrderItem,Book> bMap = new HashMap<>();
            for (Object o : list) {
                OrderItem orderItem = (OrderItem) o;
                Book book = bs.queryByBid(orderItem.getBid());
                bMap.put(orderItem,book);
            }

            return bMap;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void removeOrderItemByOid(String oid){

        try {
            orderItemDao.deleteOrderItemByOid(oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
