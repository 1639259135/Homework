package bookstore.user.dao;

import bookstore.user.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import bookstore.utils.JdbcUtil;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public  User queryByUsername(String username) throws SQLException {
        User user = new QueryRunner().query(
                JdbcUtil.getConnection(),
                "select * from user where username =?",
                new BeanHandler<>(User.class),
                username);

        return user;
    }

    public  void insertUser(User user){

        JdbcUtil.execute(coon -> {
            PreparedStatement pstate = coon.prepareStatement(
                    "INSERT INTO user VALUES (?,?,?,?,?,?)");
            pstate.setCharacterStream(1,new StringReader(user.getUid()));
            pstate.setString(2,user.getUsername());
            pstate.setString(3,user.getPassword());
            pstate.setString(4,user.getEmail());
            pstate.setCharacterStream(5,new StringReader(user.getCode()));
            pstate.setInt(6,0);

            pstate.addBatch();
            pstate.executeBatch();

            return pstate;
        });

    }

    public User queryByCode(String code) throws SQLException {
        User user = new QueryRunner().query(
                JdbcUtil.getConnection(),
                "select * from user where code =?",
                new BeanHandler<>(User.class),
                code);
        return user;
    }


    public void alterUser(String code) throws SQLException {
        JdbcUtil.execute(coon -> {
            PreparedStatement pstate = coon.prepareStatement(
                    "UPDATE user SET state=? WHERE code=?");
            pstate.setInt(1,1);
            pstate.setCharacterStream(2,new StringReader(code));

            pstate.addBatch();
            pstate.executeBatch();
            return pstate;
        });

    }




}
