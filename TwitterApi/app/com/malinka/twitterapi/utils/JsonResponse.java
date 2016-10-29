package com.malinka.twitterapi.utils;

/**
 * Created by malinkas on 1/6/15.
 */
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

import static play.libs.Json.toJson;
import static play.mvc.Results.ok;

public class JsonResponse {
    public static Result success(Object data){
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("STATUS","SUCCESS");
        resultMap.put("DATA", data);
        return ok(toJson(resultMap));
    }

    public static Result error(String message){
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("STATUS","ERROR");
        resultMap.put("MESSAGE", message);
        return ok(toJson(resultMap));
    }
}
