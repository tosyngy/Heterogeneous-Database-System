/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xisysgroup.dbi;

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

/**
 *
 * @author akisoft
 */
public class AllTabpsql {

    
    public void GetTable(String Datasource, String dbname,String user, String pass) {
        
        System.out.println("Listing all table name in Database!");
        Connection con = null;
        String url = Datasource;
        String db = dbname;
        
        String driver = "org.postgresql.Driver";
  //      driver="com.mysql.jdbc.Driver";
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
     //  new AllTabpsql().GetTable("jdbc:mysql://localhost/","mapoly","root","");
        new AllTabpsql().GetTable("jdbc:postgresql://localhost//","FPI_Student","postgres","root");
    }
}
