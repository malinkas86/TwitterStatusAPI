package com.malinka.twitterapi.services;

import com.malinka.twitterapi.models.User;

import java.util.List;

/**
 * Created by malinkas on 5/17/16.
 */
public interface TwitterService {
    /**
     * Retrieves the user timeline by the screenname
     * @param screenName
     * @return the list of formatted statuses
     */
    public List<com.malinka.twitterapi.models.Status> getUserTimeLine(String screenName);

    /**
     *
     * @param screenName
     * @return  the list of followers
     */
    public List<User> getFollowers(String screenName);
}
