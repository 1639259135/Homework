package bookstore.category.service;

import bookstore.category.dao.CategoryDao;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

    private CategoryDao cd = new CategoryDao();

    public List queryAll(){

        try {
            List list = cd.queryAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
