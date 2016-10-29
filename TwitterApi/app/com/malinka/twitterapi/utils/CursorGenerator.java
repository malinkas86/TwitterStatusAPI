package com.malinka.twitterapi.utils;

import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by malinkas on 5/18/16.
 */
@Singleton
public class CursorGenerator {
    private static HashMap<String,Integer> cursorMap=new HashMap<>();



    public static void initCursorMap(){

        for(int i=1;i<=100;i++){
            String randomNumber=Function.generate9DigitRandomNumber();
            cursorMap.put(randomNumber,i);
        }
    }

    public static String getNextCursor(String currentCursor){
        if(currentCursor==null){
            Iterator it = cursorMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if((int)pair.getValue()==1){
                    return pair.getKey().toString();
                }
            }
        }else{
           Integer currentIndex= cursorMap.get(currentCursor);
            currentIndex++;
            Iterator it = cursorMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if((int)pair.getValue()==currentIndex){
                    return pair.getKey().toString();
                }
            }

        }
        return null;
    }

    public static Integer getCurrentIndex(String cursor){
        return (cursorMap.get(cursor)==null)?1:cursorMap.get(cursor)+1;
    }


}
