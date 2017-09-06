package com.gnavin.redshift;

import com.gnavin.prop.PropertiesConfig;

import java.util.Properties;

public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException {
        final PropertiesConfig propertiesConfig = PropertiesConfig.getInstance();
        final Properties prop = propertiesConfig.getProp();
    
        final Redshift redshift = new Redshift(prop);
        redshift.printTables();
    }
}
