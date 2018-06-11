package com.imooc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by: xmg
 * 2018/3/30 14:21
 */

public class JsonUtil {


    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(object);
    }
}
