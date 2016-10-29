package com.malinka.twitterapi.services;

import com.malinka.twitterapi.models.Status;
import com.malinka.twitterapi.models.User;

import java.util.List;

/**
 * Created by malinkas on 5/19/16.
 * Service class which performs the status related functionality
 */
public interface StatusService {
    /**
     *
     * @param userList
     * @param count
     * @param cursor
     * @return a list of sorted statuses
     */
    public List<Status> getStatusesForUsers(List<User> userList,int count,String cursor);
}
