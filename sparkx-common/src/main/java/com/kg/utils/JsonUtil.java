package com.kg.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json的工具类
 */
public class JsonUtil implements Serializable {
    public static final String DEF_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Gson gson = null;
    static {
        GsonBuilder gb = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss");
        gb.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        gson = gb.create();
    }


    /**
     * json -> list
     * @param json
     * @return
     */
    public static List<String> gJson2List(String json) {
        List<String> result = new ArrayList<String>();
        JSONArray jarray = JSON.parseArray(json);
        if(!jarray.isEmpty()){
            result = jarray.toJavaList(String.class);
        }
        return result;
    }

    /**
     * obj -> json
     * @param obj
     * @return
     */
    public static String gObject2Json(Object obj) {
        String json = gson.toJson(obj, obj.getClass());
        return json;
    }


    /**
     * obj -> map
     * @param obj
     * @return
     */
    public static Map<String,Object> gObject2Map(Object obj) {
        String json = gson.toJson(obj, obj.getClass());
        Map<String,Object> result = gson.fromJson(json,Map.class);
        return result;
    }

    /**
     * json -> map
     * @param str
     * @return
     */
    public static Map<String,Object> gJson2Map(String str) {
        Map<String,Object> result = gson.fromJson(str,Map.class);
        return result;
    }


    //将obg---json
    public static String object2json(Object obj) {
        //JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd"; 全局日期格式化
        return JSON.toJSONString(obj);
    }

    //将json---obj
    public static <T> T json2object(String json,Class<T> cls) {
        JSON.DEFFAULT_DATE_FORMAT = DEF_DATEFORMAT;
        return JSON.parseObject(json, cls);
    }

    //测试
    public static void main(String[] args){
        String json = "[\"P40\",\"P46\",\"P28\",\"P62\"]";
        List<String> list = gJson2List(json);
        System.out.println(list);

        String str = "{\"targetID\":\"P25\"}";
        Map<String,Object> datas = gJson2Map(str);
        System.out.println(datas.toString());
    }
}
