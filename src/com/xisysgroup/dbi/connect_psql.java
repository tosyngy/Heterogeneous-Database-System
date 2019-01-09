/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xisysgroup.dbi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author akisoft
 */
public class connect_psql {

    private static final String TABLE_NAME = "tablenamegoeshere";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/postgres:5432/";//this is where to comment ems
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
     // url = "jdbc:postgresql://" + host+":5432"+"/";
    private static Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("connect");
        return conn;
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (Exception e) {
            System.out.println("" + e);
            e.printStackTrace();
        }

    }
}
