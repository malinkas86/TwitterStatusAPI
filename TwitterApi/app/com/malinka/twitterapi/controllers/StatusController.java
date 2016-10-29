package com.malinka.twitterapi.controllers;

import com.google.inject.Inject;
import com.malinka.twitterapi.dto.UserStatusListResponse;
import com.malinka.twitterapi.models.User;
import com.malinka.twitterapi.services.StatusService;
import com.malinka.twitterapi.utils.CursorGenerator;
import com.malinka.twitterapi.utils.JsonResponse;
import com.malinka.twitterapi.utils.Message;
import play.Configuration;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class StatusController extends Controller {
    private Configuration configuration;

    @Inject
    public StatusController(Configuration configuration) {
        this.configuration = configuration;
    }

    @Inject
    private StatusService statusService;

    /**
     * An action that produces the list of statuses in a json response.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result getUserStatuses() {

        //extracting the query parameters
        List<com.malinka.twitterapi.models.Status> statuses=null;
        String usersStrParam=request().getQueryString("screen_names");
        String cursor=request().getQueryString("cursor");
        String count=request().getQueryString("count");
        String[] screenNames=usersStrParam.split(",");
        if(screenNames.length==0){
            return JsonResponse.error(Message.INVALID_SCREEN_NAME_COUNT);
        }
        if(cursor!=null && CursorGenerator.getNextCursor(cursor)==null){
            return JsonResponse.error(Message.INVALID_CURSOR_INDEX);
        }
        //assigning a default page count if the parameter is not received
        if(count==null){
            count=configuration.getString("app.defaultPageCount");
        }
        List<User> userList=new ArrayList<>();
        //making list of user objects to be processed for statuses
        for(String screenName:screenNames){
            userList.add(new User(screenName));
        }
        try {
            //retrieving the sorted user statuses
            statuses = statusService.getStatusesForUsers(userList,Integer.parseInt(count),cursor);

        } catch (Exception te) {
            te.printStackTrace();
            return JsonResponse.error(Message.GENEREL_ERROR+te.getMessage());
        }

        String nextCursor= CursorGenerator.getNextCursor(cursor);

        UserStatusListResponse userStatusListResponse=new UserStatusListResponse();
        userStatusListResponse.setStatuses(statuses);
        userStatusListResponse.setNextCursor(nextCursor);


        return JsonResponse.success(userStatusListResponse);
    }

}
