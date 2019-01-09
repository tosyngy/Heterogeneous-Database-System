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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akisoft
 */
public class AllTableName {

    
    public void GetTable(String Datasource, String dbname,String user, String pass,String dbtype) {
        
        System.out.println("Listing all table name in Database!");
        Connection con = null;
        String url = Datasource;
        String db = dbname;
        String driver=null;
        if(dbtype.equals("mysql")){
            driver = "com.mysql.jdbc.Driver";
            try{
              Class.forName(driver);
            con = DriverManager.getConnection(url + db, user, pass);
         
            } catch (Exception ex) {
                Logger.getLogger(AllTableName.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(dbtype.equals("psql")){
            try {
                driver = "org.postgresql.Driver";
                  Class.forName(driver);
                System.out.println(url+db);
                  con = DriverManager.getConnection(url +db, user, pass);
        //con.close();
                       System.out.println("All tables connectd");
            } catch ( Exception ex) {
                Logger.getLogger(AllTableName.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                con.close();
                
            } catch (SQLException s) {
                System.out.println("No any table in the database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
       //new AllTableName().GetTable("localhost", null, null, null, null);
    }
}
