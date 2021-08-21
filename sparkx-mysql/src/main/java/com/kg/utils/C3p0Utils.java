package com.kg.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3p0Utils {
    //构造器会自动检索src下有没有指定文件名称的配置文件，然后会自动赋值给其相应的属性
    private static ComboPooledDataSource pool = new ComboPooledDataSource("c3p0-config");

    public static Connection getConnection()  {
        //从连接池中获取空闲对象
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void closeConnection(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close(); //会将连接对象归还给连接池内
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        System.out.println(conn);
        conn.close();
    }

}
