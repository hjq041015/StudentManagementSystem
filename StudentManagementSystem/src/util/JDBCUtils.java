package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    // //静态代码块，静态代码块在类文件加载阶段一定执行！！！并且有且只执行一次，用于程序初始化，预处理操作
    // 通过静态代码块,来预先读取配置文件的配置项,做预处理
    static {
        try {
        //1.获取properties文件
        //JdbcUtils.class   反射知识，获得Class实例，即JdbcUtils类
        //getClassLoader()    获得类加载器
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");


           Class.forName(driver);

        } catch (IOException | ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    public static void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet res) throws SQLException {
        if (res != null) {
            res.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
