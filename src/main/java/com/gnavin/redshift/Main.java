package com.gnavin.redshift;

import com.gnavin.prop.PropertiesConfig;

import java.sql.SQLException;
import java.util.Properties;

public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final PropertiesConfig propertiesConfig = PropertiesConfig.getInstance();
        final Properties prop = propertiesConfig.getProp();
        
        final Redshift redshift = new Redshift(prop);
        redshift.printTables();
        redshift.insertIntoTables();
    }
}
