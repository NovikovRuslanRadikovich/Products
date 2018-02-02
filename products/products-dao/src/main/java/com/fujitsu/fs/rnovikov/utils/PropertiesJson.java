package com.fujitsu.fs.rnovikov.utils;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class PropertiesJson {

    private static String DRIVER;
    private static String CONNECTION_URL;
    private static String PASSWORD;
    private static String USERNAME;

  //  private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\database.json";
    private static JsonParser parser = new JsonParser();
    static Object object;
    static JsonObject obj;

    static {

        try {
       //     object = parser.parse(new FileReader(new File(PropertiesJson.class.getClassLoader().getResource("database.json").toURI())) );


            object = parser.parse(new InputStreamReader(PropertiesJson.class.getClassLoader().getResourceAsStream("database.json")));
            obj = (JsonObject) object;

        } catch(Exception ex) {
            ex.printStackTrace();
        }


    }

    public static String getDriver() {
        if(DRIVER == null) {
            DRIVER = obj.get("DRIVER").getAsString();
        }
        return DRIVER;
    }

    public static String getConnection_URL() {

        if(CONNECTION_URL == null) {
            CONNECTION_URL = obj.get("CONNECTION_URL").getAsString();
        }

        return CONNECTION_URL;

    }

    public static String getPassword() {
        if(PASSWORD == null) {
            PASSWORD = obj.get("PASSWORD").getAsString();
        }
        return PASSWORD;
    }

    public static String getUsername() {
        if(USERNAME == null) {
            USERNAME = obj.get("USERNAME").getAsString();
        }
        return USERNAME;
    }

}
