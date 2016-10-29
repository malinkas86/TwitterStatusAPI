package com.malinka.twitterapi.utils;

/**
 * Created by malinkas on 5/18/16.
 */
public class HtmlBuilder {

    public static String createAnchor(String content,String url){
        return "<a href=\""+url+"\">"+content+"</a>";
    }
}
