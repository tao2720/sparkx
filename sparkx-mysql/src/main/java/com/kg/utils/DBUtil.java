package com.kg.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author 千锋大数据教学团队
 * @Company 千锋好程序员大数据
 * @Description
 * 定义一个封装了对数据库进行交互的一个工具类型
 * - 可以提供一个获取连接的方法
 * - 可以提供一个关闭连接的方法
 *
 * 具体步骤：
 * 1. 将属性参数封装到一个配置文件db.properties中
 * 2. 添加静态属性
 * 3. 添加静态块，
 * 4. 提供一个链接数据库的方法
 * 5. 提供一个关闭数据库的方法
 *
 *
 */
public class DBUtil {
    //添加静态属性
    private static String driverclass;
    private static String url;
    private static String username;
    private static String password;
    //添加静态块, 静态代码块一般用于加载静态资源（图片，文件，音频，视频，html,css,js,第三方jar包），因为只会在加载.class文件时，执行一次
    static{
        try {
            //此静态块的目的是加载配置文件和注册驱动
            //第一步：获取IO流对象，读取配置文件里的连接数据库的参数信息
            /*
             *  类加载器里的getResourceAsStream(String path);
             *  此方法的参数是一个文件的路径，此路径是相对于项目的src来说的，
             *  因此写相对路径时，如果直接写文件名，那么此文件必须在src下
             *
             */
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //第二步：获取一个Properties对象，用于封装连接数据库的参数信息
            Properties prop = new Properties();
            prop.load(is);  //load方法的逻辑就是将流里的KV对，解析到prop对象里

            //给静态属性赋值, 调用getProperty方法，参数为文件里=前面的字符串
            driverclass = prop.getProperty("driverclass");
            url = prop.getProperty("url");
            username = prop.getProperty("user");
            password = prop.getProperty("password");

            //注册驱动
            Class.forName(driverclass);


            //关闭流对象
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 封装一个获取连接对象的方法
     * @return  连接对象
     */
    public static Connection getConnection(){
          try{
              Connection conn = DriverManager.getConnection(url,username,password);
              return  conn;
          }catch (Exception e){
              e.printStackTrace();

          }
        return null;
    }
    /**
     * 封装一个关闭连接的方法，关闭 ResultSet对象，Statement对象，Connection对象
     */
    public static void closeConnection(Connection conn, Statement stat, ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
            if(stat!=null){
                stat.close();
            }
            if(conn!=null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //提供一个main方法进行测试
    public static void main(String[] args) {
        Connection conn = DBUtil.getConnection();
        System.out.println(conn);
        closeConnection(conn,null,null);
    }
}
