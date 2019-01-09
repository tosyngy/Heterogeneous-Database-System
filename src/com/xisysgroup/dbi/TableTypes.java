/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xisysgroup.dbi;

/**
 *
 * @author akisoft
 */
/*
 * Copyright 2003 Sun Microsystems, Inc. ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class TableTypes {

    Connection con;

    public void GetDb(String host, String user, String pass, String status, String dbtype) {
        String url = null;
        String driver = null;
        if (dbtype.equals("mysql")) {
            url = "jdbc:mysql://" + host + ":3306" + "/";
            driver = "com.mysql.jdbc.Driver";


            try {
                Class.forName(driver);
                // change user and password as you need it
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("COnnected++++++++++++++++");
                ResultSet rs = con.getMetaData().getCatalogs();
                ResultSetMetaData md = rs.getMetaData();
                System.out.println("another info "+con.getMetaData().getCatalogTerm());
                System.out.println(md.getCatalogName(1));
                // String db="use database "+dbtype;
                while (rs.next()) {
                    //System.out.println("counting ");
                    if (status.equals("save")) {
                        System.out.println("rs size " + md.getTableName(1));
                        System.out.println("yeah  hrere " + rs.getString(md.getColumnName(1)));
                        com.xisysgroup.dbi.variables.Dababase_variable.dbnames.addElement(rs.getString("TABLE_CAT"));
                    }
                }
                con.close();
            } catch (Exception ex) {
                System.err.print("SQLException: ");
                System.err.println(ex.getMessage());
                ex.printStackTrace();
                if (ex.toString().contains("link failure")) {
                    JOptionPane.showMessageDialog(null, "unknown host");
                }
                if (ex.toString().contains("Access denied for user")) {
                    JOptionPane.showMessageDialog(null, "User name or Pass not valid");
                }

            }

        } else if (dbtype.equals("psql")) {
            url = "jdbc:postgresql://" + host + ":5432" + "/FPI_Student";
            //    url = "jdbc:5432"+"/";
            driver = "org.postgresql.Driver";
            //    com.xisysgroup.dbi.variables.Dababase_variable.dbnames.addElement("FPI_Student");// this where to comment ems

            try {
                Class.forName(driver);
//con.close();
                // change user and password as you need it
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("COnnected++++++++++++++++ to postgres");
                Statement st = con.createStatement();
                ResultSet rst = st.executeQuery("select Version()");
                System.out.println(rst.getFetchSize());
                while (rst.next()) {
                    System.out.println("info " + rst.getString(1));
                }
                ResultSet rs = con.getMetaData().getCatalogs();
                System.out.println(rs.getStatement().toString());
                ResultSetMetaData md = rs.getMetaData();
                System.out.println("size of this server is " + md.getColumnCount());
//                System.out.println("info "+rs.getString(1));
                // String db="use database "+dbtype;
                while (rs.next()) {
                    System.out.println("info " + rs.getString(1));
                    //System.out.println("counting ");
                    if (status.equals("save")) {
                        System.out.println("rs size " + md.getTableName(1));
                        System.out.println("yeah  hrere " + rs.getString(md.getColumnName(1)));
                        com.xisysgroup.dbi.variables.Dababase_variable.dbnames.addElement(rs.getString("TABLE_CAT"));
                    }

                }
                con.close();
            } catch (Exception ex) {
                System.err.print("SQLException: ");
                System.err.println(ex.getMessage());
                ex.printStackTrace();
                if (ex.toString().contains("link failure")) {
                    JOptionPane.showMessageDialog(null, "unknown host");
                }
                if (ex.toString().contains("Access denied for user")) {
                    JOptionPane.showMessageDialog(null, "User name or Pass not valid");
                }

            }

        }
//
//        try {
//            Class.forName(driver);
//
//        } catch (java.lang.ClassNotFoundException e) {
//            System.err.print("ClassNotFoundException: " + e.getMessage());
//            System.err.println(e.getMessage());
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) {
        //new TableTypes().GetDb("localhost", "root", "", "save", "mysql");
        new TableTypes().GetDb("localhost", "postgres", "root", "save", "mydb");
    }
}
