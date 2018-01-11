package bookstore.book.dao;

import bookstore.book.domain.Book;
import bookstore.category.domain.Category;
import bookstore.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDao {

    public Book queryByBid(String bid) throws SQLException {
        Book book = new QueryRunner().query(
                JdbcUtil.getConnection(),
                "select * from book where bid=?",
                new BeanHandler<Book>(Book.class),
                bid);

        return book;
    }

    public List queryByCategory(String cname) throws SQLException {
        List<Book> list = new QueryRunner().query(
                JdbcUtil.getConnection(),
                "SELECT * FROM book b INNER JOIN category c ON b.cid = c.cid WHERE cname=?",
                new BeanListHandler<Book>(Book.class),
                cname);

        return list;
    }




}
