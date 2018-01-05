package utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    private static String url;
    private static String user;
    private static String password;
    private static String database;

    static{
        try {
            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("jdbc.properties");
            Properties info = new Properties();
            info.load(is);

            String driverName = info.getProperty("driverName");
            url = info.getProperty("url");
            user = info.getProperty("user");
            password = info.getProperty("password");
            database = info.getProperty("database");

            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + "\n\t\t" + "请将配置文件jdbc.properties放到src目录下");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        try {
            Connection coon = DriverManager.getConnection(
                    url+database,
                    user,
                    password);
            return coon;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void execute(ExecuteInter e){
        try {
            Connection coon = DriverManager.getConnection(
                    url+database,
                    user,
                    password);
            e.execute(coon).close();
            coon.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
