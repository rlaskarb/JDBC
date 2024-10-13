package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcTemplate {

    /*title. jdbc 커넥션 정보를  하나의 틀로 만들어 필요한 곳에서 호출*/

    //싱글톤
    public static Connection getConnection(){
        Connection con= null;
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url,prop);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }// finally close 하면 안된다.

        return con;
    }

    public static void close(Connection con){

        try {

            // 비트연산자 => 좌항과 우항을 모두 비교
            if (con != null & !con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void close(Statement stmt){

        try {

            // 비트연산자 => 좌항과 우항을 모두 비교
            if (stmt != null & !stmt.isClosed()){
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void close(ResultSet rset){

        try {

            // 비트연산자 => 좌항과 우항을 모두 비교
            if (rset != null & !rset.isClosed()){
                rset.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
