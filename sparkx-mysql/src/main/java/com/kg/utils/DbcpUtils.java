package com.kg.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbcpUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static int maxTotal;
    private static int maxIdle;
    private static int minIdle;
    private static int initialsize;
    private static long maxWaitMillis;

    private static BasicDataSource pool;
    static {

        try {
            pool=new BasicDataSource();//连接池对象

            InputStream is= DbcpUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");

            Properties prop=new Properties();

            prop.load(is);//将配置文件里的内容封装到prop对象里

            driver=prop.getProperty("driver");
            url=prop.getProperty("url");
            username=prop.getProperty("username");
            password=prop.getProperty("pwd");
            maxTotal=Integer.parseInt(prop.getProperty("maxTotal"));
            maxIdle=Integer.parseInt(prop.getProperty("maxIdle"));
            minIdle=Integer.parseInt(prop.getProperty("minIdle"));
            initialsize=Integer.parseInt(prop.getProperty("initialSize"));
            maxWaitMillis=Long.parseLong(prop.getProperty("maxWaitMillis"));

            pool.setDriverClassName(driver);
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setMaxTotal(maxTotal);
            pool.setMaxIdle(maxIdle);
            pool.setMinIdle(minIdle);
            pool.setInitialSize(initialsize);
            pool.setMaxWaitMillis(maxWaitMillis);
            //Class.forName(driver);



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    public static void closeConnection(Connection conn, Statement stat, ResultSet rs){
        try {
            if (rs != null){
                rs.close();

            }

            if (stat!=null){
                stat.close();
            }
            if (conn != null){
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        System.out.println(conn);
        conn.close();
    }
}
