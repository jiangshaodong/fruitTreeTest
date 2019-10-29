package com.part.utils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.util.Map;

/*
 * @author jiangSD
 * @since 2019-10-23
 */
public class FastJsonUtil {
    public static String toJson(Map<String,String[]> map) {
        JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        return result;
    }
}
