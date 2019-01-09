/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xisysgroup.dbi;

/**
 *
 * @author Hussein
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.xisysgroup.dbi.variables.*;//Dababase_variable;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class getpsltable {
    
    String Datasource;
    String dbnam;
    String user;
    String pass;
    String dbtype;
    public void getdb(){
        System.out.println("Listing all table name in Database!");
        Connection con = null;
        String url = Datasource;
        String db = dbnam;
        String driver=null;
        if(dbtype.equals("mysql")){
            driver = "com.mysql.jdbc.Driver";
        }else if(dbtype.equals("psql")){
            driver = "org.postgresql.Driver";
            
        } 
        
//        String user = "root";
//        String pass = "";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, user, pass);
            try {
                DatabaseMetaData dbm = con.getMetaData();
                String[] types = {"TABLE"};
                ResultSet rs = dbm.getTables(null, null, "%", types);
                System.out.println("Table name:");
               
                while (rs.next()) {
                    //counter=counter+1;
                    String table = rs.getString("TABLE_NAME");
                    Dababase_variable.DbTables.addElement(table);
                    System.out.println(table);
                    con.close();
                }
                
                
            } catch (SQLException s) {
                System.out.println("No any table in the database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        
    }

}
