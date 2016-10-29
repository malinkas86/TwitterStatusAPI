package com.malinka.twitterapi.services;

import com.google.inject.Inject;
import com.malinka.twitterapi.models.Status;
import com.malinka.twitterapi.models.User;
import com.malinka.twitterapi.utils.CursorGenerator;


import java.util.*;

/**
 * Created by malinkas on 5/18/16.
 */
public class StatusServiceImpl implements StatusService{
    @Inject
    private TwitterService twitterService;

    /**
     *
     * @param userList
     * @param count
     * @param cursor
     * @return a list of sorted statuses
     */
    @Override
    public List<Status> getStatusesForUsers(List<User> userList,int count,String cursor){
        //too have the sorted statuses
        TreeMap<Long,Status> statusTreeMap=new TreeMap<>();
        //the sorted statuses will be added to the sortedStatusList
        List<Status> sortedStatusList=new ArrayList<>();
        //processing statuses for each user
        for(User user:userList){
            List<com.malinka.twitterapi.models.Status> statusList=twitterService.getUserTimeLine(user.getName());
            //User statuses are added to the tree map to maintain the sorting order
            for(com.malinka.twitterapi.models.Status status:statusList){
                statusTreeMap.put(status.getCreatedAt().getTime(), status);
            }
        }

        Integer currentIndex=CursorGenerator.getCurrentIndex(cursor)!=null?CursorGenerator.getCurrentIndex(cursor):1;

        System.out.println("currentIndex "+currentIndex);

        int startIndex=currentIndex*count-(count-1);
        int counter=1;

        ArrayList<Long> keys = new ArrayList(statusTreeMap.keySet());
        //Adds to the arraylist in the reverse order, hence the final output will be in correct order
        for(int i=keys.size()-1; i>=0;i--){
            Status status = statusTreeMap.get(keys.get(i));
            if(counter>=startIndex && counter<=(currentIndex*count)){
                sortedStatusList.add(status);
            }
            if(counter==(currentIndex*count)){
                break;
            }
            counter++;
        }
        return sortedStatusList;
    }

}
