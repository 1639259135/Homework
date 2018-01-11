package bookstore.book.service;

import bookstore.book.dao.BookDao;
import bookstore.book.domain.Book;
import bookstore.category.domain.Category;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    public List queryByCategory(String cname){

        try {
            List list = bookDao.queryByCategory(cname);
//            System.out.println(list.toString());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Book queryByBid(String bid){

        try {
            Book book = bookDao.queryByBid(bid);

            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }





}
