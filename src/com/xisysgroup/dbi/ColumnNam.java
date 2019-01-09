/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xisysgroup.dbi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import com.xisysgroup.dbi.variables.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akisoft
 */
public class ColumnNam {

    Object value;
    Statement st;
    ResultSet rs;
    DefaultTableModel tablemodel;
    String url;
    String db;
    String driver = null;//= "com.mysql.jdbc.Driver";
    Connection con = null;
    String user;
    String pass;

    public void getColumnName(String Datasource, String dbname, String user, String pass, String table, JTable jtab, String dbtype) {
        tablemodel = new DefaultTableModel();
        System.out.println("Getting Column Names Example!");
        this.user = user;
        this.pass = pass;
        this.url = Datasource;
        this.db = dbname;

        

//        String user = "root";
//        String pass = "";
        try {
//            switch (dbtype) {
//                case "mysql":
//                    driver = "com.mysql.jdbc.Driver";
//                    break;
//                case "psql":
//                    driver = "org.postgresql.Driver";
//                    break;
//            }
              String sql="";
            if(dbtype.equals("mysql")){
                driver = "com.mysql.jdbc.Driver";
                 sql = "SELECT * FROM " +table;
            }else{
                 driver = "org.postgresql.Driver";
                  sql = "SELECT * FROM \"" +table+"\"";
            }
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, user, pass);
          
            System.out.println("sql " + sql);
            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);
                ResultSetMetaData md = rs.getMetaData();
                int col = md.getColumnCount();
                System.out.println("Number of Column : " + col);
                System.out.println("Columns Name: ");
                int counter = 0;
                for (int i = 1; i <= col; i++) {
                    counter = counter + 1;
                    String col_name = md.getColumnName(i);
                    Dababase_variable.DbTablesCol.addElement(col_name);
                    String col_data_type = md.getColumnTypeName(i);
                    Dababase_variable.DbTablesColDes.addElement(col_data_type);
                    System.out.println(col_name + " " + col_data_type);
                }
                for (int l = 0; l < Dababase_variable.DbTablesCol.size(); l++) {
                    System.out.println("enter addcolumn");
                    tablemodel.addColumn(Dababase_variable.DbTablesCol.elementAt(l));
                }


                addobject(sql);

                jtab.setModel(tablemodel);
                con.close();
            } catch (SQLException s) {
                s.printStackTrace();
                System.out.println("SQL statement is not executed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object addobject(String sql) {
        try {
            loadDriver();
            //String SQLCommand = "select * from akin";
            rs = st.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int nColumns = md.getColumnCount();
            System.out.println("" + nColumns);
            System.out.println(nColumns);
            String test = "";
            int counter = 0;
            Object datas[] = new Object[Dababase_variable.DbTablesCol.size()];
            while (rs.next()) {
                //rowData = new String[nColumns];
                for (int i = 0; i < nColumns; i++) {
                    counter = counter + 1;

                    if (counter != nColumns) {
                        datas[counter - 1] = rs.getObject(i + 1).toString();
                        test = test + rs.getObject(i + 1).toString() + " ";

                    } else {
                        datas[counter - 1] = rs.getObject(i + 1).toString();
                        tablemodel.addRow(datas);
                        test = test + rs.getObject(i + 1).toString() + " ";
                        System.out.println("test  " + test);
                        test = "";
                        //datas=null;
                        counter = 0;
                    }

                    //rowData[i] = rs.getObject(i+1).toString();
                    //data.addElement(rowData[i]);
                    //System.out.println("card "+rowData[i]);



                }
            }
        } catch (Exception e) {
            System.out.println("addobject " + e);
        }
        return value;

    }

    public int columncount(String fieldname, String table) {
        System.out.println("column count");
        loadDriver();
        int count = 0;
        try {

            String SQLCommand = "select " + fieldname + " from " + table;
            rs = st.executeQuery(SQLCommand);
            ResultSetMetaData md = rs.getMetaData();
            count = md.getColumnCount();

            while (rs.next()) {
                count = count + 1;
            }
            System.out.println("column count = " + count);
        } catch (Exception e) {
            System.out.println("column count error " + e);
        }
        return count;

    }

    void loadDriver() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, user, pass);
            st = con.createStatement();
            //con.close();
        } catch (Exception e) {
            System.out.println("load driver " + e);
        }
    }
}
