package com.fujitsu.tests.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;

/**
 * Created by User on 21.01.2018.
 */
public class CredentialsRetriever {

    private static String baseUrl;
    private static String password;
    private static String name;

    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\credentials.json";



    private static JsonParser parser = new JsonParser();
    static Object object;
    static JsonObject obj;
    static {

        System.out.println(FILE_PATH);
        try {
            object = parser.parse(new FileReader(new File(CredentialsRetriever.class.getClassLoader().getResource("/credentials.json").toURI())));

            obj = (JsonObject) object;

        } catch(Exception ex) {
            ex.printStackTrace();
        }


    }

    public static String getBaseUrl() {

        if(baseUrl == null) {
            baseUrl =  obj.get("baseUrl").getAsString();
        }
        return baseUrl;
    }

    public static String getAdminPassword() {
        if(password == null) {
            password = obj.get("admin_password").getAsString();
        }
        return password;
    }

    public static String getAdminName() {
        if(name == null) {
            name = obj.get("admin_name").getAsString();
        }
        return name;
    }

    public static String getUserName() {
        if(name == null) {
            name = obj.get("user_name").getAsString();
        }
        return name;
    }

    public static String getUserPassword() {
        if(password == null) {
            password = obj.get("user_password").getAsString();
        }
        return password;
    }

}
