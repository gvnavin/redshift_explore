package com.gnavin.redshift;

import java.sql.*;
import java.util.Properties;

//http://docs.aws.amazon.com/redshift/latest/mgmt/connecting-in-code.html#connecting-in-code-java
public class Redshift {
    
    private final Properties inputProp;
    private final Properties props = new Properties();
    
    public Redshift(Properties inputProp) throws ClassNotFoundException {
        this.inputProp = inputProp;
        //Uncomment the following line if using a keystore.
        //props.setProperty("ssl", "true");
        props.setProperty("user", inputProp.getProperty("user"));
        props.setProperty("password", inputProp.getProperty("pass"));
    
        //Dynamically load driver at runtime.
        //Redshift JDBC 4.1 driver: com.amazon.redshift.jdbc41.Driver
        //Redshift JDBC 4 driver: com.amazon.redshift.jdbc4.Driver
        Class.forName("com.amazon.redshift.jdbc42.Driver");
    }
    
    public void printTables() {
        Connection conn = null;
        Statement stmt = null;
        try {
            
            //Open a connection and define properties.
            System.out.println("Connecting to database...");
            
            conn = DriverManager.getConnection(inputProp.getProperty("dbURL"), props);
            
            //Try a simple query.
            System.out.println("Listing system tables...");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from information_schema.tables;";
            try (ResultSet rs = stmt.executeQuery(sql)) {
        
                //Get the data from the result set.
                while (rs.next()) {
                    //Retrieve two columns.
                    String catalog = rs.getString("table_catalog");
                    String name = rs.getString("table_name");
    
                    //Display values.
                    System.out.print("Catalog: " + catalog);
                    System.out.println(", Name: " + name);
                }
                rs.close();
            }
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            //For convenience, handle all errors here.
            ex.printStackTrace();
        } finally {
            //Finally block to close resources.
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception ex) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Finished connectivity test.");
    }
}
