package com.malinka.twitterapi.dto;

import com.malinka.twitterapi.models.Status;

import java.util.List;

/**
 * Created by malinkas on 5/19/16.
 * DTO object for the status list response
 * Contains a list of statuses and the next cursor
 */
public class UserStatusListResponse {
    private List<Status> statuses;
    private String nextCursor;

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}
