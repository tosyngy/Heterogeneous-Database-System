/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xisysgroup.dbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author akisoft
 */
public class connect_mysql {
    static Statement st;
    static PreparedStatement ps;
    static ResultSet rs;
    static Connection conn;
    static String  url = "jdbc:mysql://localhost/";
    static String dbname = "mysql_postgres";
    static String driver = "com.mysql.jdbc.Driver";
    static String username = "root";
    static String password = "";

    static String  url2 = "jdbc:postgresql://localhost:5432/";
    static String dbname2 = "mysql_postgres";
    static String driver2 = "org.postgresql.Driver";
    static String username2 = "postgres";
    static String password2 = "root";


    public static void getConnection() throws Exception {
        try {
          Class.forName(driver);
        conn = DriverManager.getConnection(url+dbname, username, password);
        st=conn.createStatement();
        System.out.println("connect");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static void postConnection() throws Exception {
        try {
           Class.forName(driver2);
     conn = DriverManager.getConnection(url2+dbname2, username2, password2);
        st=conn.createStatement();
        System.out.println("connect");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void getConnection(String dbname) throws Exception {
        try {
          Class.forName(driver);
        conn = DriverManager.getConnection(url+dbname, username, password);
        st=conn.createStatement();
        System.out.println("connect");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static void postConnection(String dbname2) throws Exception {
        try {
           Class.forName(driver2);
     conn = DriverManager.getConnection(url2+dbname2, username2, password2);
        st=conn.createStatement();
        System.out.println("connect");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
//            postConnection();
        } catch (Exception e) {
            System.out.println("" + e);
            e.printStackTrace();
        }

    }
}
