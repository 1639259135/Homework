package bookstore.category.dao;

import bookstore.book.domain.Book;
import bookstore.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    public List queryAll() throws SQLException {
        List<Book> books = new QueryRunner().query(
                JdbcUtil.getConnection(),
                "select * from book",
                new BeanListHandler<>(Book.class));

        return books;
    }


}
