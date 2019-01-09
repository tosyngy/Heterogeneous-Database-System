/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xisysgroup.dbi;

/**
 *
 * @author akisoft
 */
import java.sql.*;
import java.util.Vector;
public class testmeta {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
     public static String user;
    Vector<String> data = new Vector<String>();
    public static String[] rowData;
    public  void test() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection("jdbc:mysql://localhost/akintunde","root","pcgreen123");
            st = con.createStatement();

            String SQLCommand="select * from akin";
            rs = st.executeQuery(SQLCommand);
            ResultSetMetaData md = rs.getMetaData();
            int nColumns = md.getColumnCount();
            System.out.println(""+nColumns);
            System.out.println(nColumns);
            String test="";
            int counter=0;
            while(rs.next())
            {
                rowData = new String[nColumns];
                for(int i=0;i<nColumns;i++) {
                    counter=counter+1;
                    
                    if(counter!=nColumns){
                        test=test+rs.getObject(i+1).toString()+" ";
                        
                    }else{
                        test=test+rs.getObject(i+1).toString()+" ";
                        System.out.println("test  "+test);
                        test="";
                        counter=0;
                    }
                    
                    rowData[i] = rs.getObject(i+1).toString();
                    data.addElement(rowData[i]);
                    //System.out.println("card "+rowData[i]);
                    
                    
                    
                }
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
    }
    public static void main(String[] args) {
        new testmeta().test();
    }
}
