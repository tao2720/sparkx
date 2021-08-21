package com.kg.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidUtils {
    //创建连接池对象
    private static DataSource pool = null;
    static {
        try {
            //使用类加载器提供的方法读取db.properties,返回一个字节流对象
            InputStream is = DruidUtils.class.getClassLoader(). getResourceAsStream("druid.properties");
            //创建Properties对象，用于加载流内部的数据
            Properties prop = new Properties();
            prop.load(is);
            //加载流内部的信息，以key-value的形式进行加载
            // 调用静态方法，会自动给自己的属性赋值

            pool = DruidDataSourceFactory.createDataSource(prop);

        } catch (Exception e) {
            System.out.println("注册驱动失败");
            e.printStackTrace();
        }
    }

    /**
     *
     * 获取连接对象
     * @return 连接对象
     */
    public static Connection getConnection(){
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     */
    public static void closeConnection(ResultSet rs, Statement stat, Connection conn){
        try{
            if (rs != null){
                rs.close();
            }
            if (stat != null){
                stat.close();
            }
            if (conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        System.out.println(conn);
        conn.close();
    }
}
