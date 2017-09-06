package com.gnavin.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertiesConfig {
    
    private static class LazyHolder {
        static final PropertiesConfig INSTANCE = new PropertiesConfig("config.properties");
    }
    
    public static PropertiesConfig getInstance() {
        return LazyHolder.INSTANCE;
    }
    
    private final Properties prop = new Properties();
    
    public PropertiesConfig(final String filename) {
        setProp(filename);
    }
    
    private void setProp(final String filename) {
        InputStream input = null;
        
        try {
            
            input = PropertiesConfig.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
            }
            
            //load a properties file from class path, inside static method
            prop.load(input);
            
            /*Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                System.out.println("Key : " + key + ", Value : " + value);
            }*/
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public Properties getProp() {
        return prop;
    }
    
    public List<String> getAsStringList(final String commaSeperatedListAsStringKey) {
        final String s3ObjectStr = prop.getProperty(commaSeperatedListAsStringKey);
        final String[] s3ObjectUrlArrays = s3ObjectStr.split(",");
        return Arrays.asList(s3ObjectUrlArrays);
    }
}
