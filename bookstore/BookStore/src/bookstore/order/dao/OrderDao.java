package bookstore.order.dao;

import bookstore.order.domain.Order;
import bookstore.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {


    public List queryAll(String uid) throws SQLException {

        List<Order> orders = new QueryRunner().query(
                JdbcUtil.getConnection(),
                "select * from orders where uid=?",
                new BeanListHandler<Order>(Order.class),
                uid);

        return orders;
    }


    public Order queryByOid(String oid) throws SQLException {

        Order order = new QueryRunner().query(
                JdbcUtil.getConnection(),
                "select * from orders where oid=?",
                new BeanHandler<Order>(Order.class),
                oid);

        return order;
    }


    public void insertOrder(Order order){

        JdbcUtil.execute(coon -> {
            PreparedStatement patate = coon.prepareStatement(
                    "INSERT INTO orders VALUES (?,?,?,?,?,?)");

            patate.setString(1,order.getOid());
            patate.setString(2,order.getOrderTime());
            patate.setDouble(3,order.getPrice());
            patate.setInt(4,order.getState());
            patate.setString(5,order.getUid());
            patate.setString(6,order.getAddress());

            patate.addBatch();
            patate.executeBatch();

            return patate;
        });
    }


    public void removeOrder(String oid){
        JdbcUtil.execute(coon -> {
            PreparedStatement patate = coon.prepareStatement(
                    "DELETE FROM orders WHERE oid=?");

            patate.setString(1,oid);

            patate.addBatch();
            patate.executeBatch();

            return patate;
        });
    }


    public void alterOrder(String oid,String address) throws SQLException {

        JdbcUtil.execute(coon -> {
            PreparedStatement pstate = coon.prepareStatement(
                    "update orders set state=1 where oid=?");

            pstate.setString(1,oid);

            pstate.addBatch();
            pstate.executeBatch();

            return pstate;
        });

        JdbcUtil.execute(coon -> {
            PreparedStatement pstate = coon.prepareStatement(
                    "update orders set address=? where oid=?");

            pstate.setString(1,address);
            pstate.setString(2,oid);

            pstate.addBatch();
            pstate.executeBatch();

            return pstate;
        });


    }


}
