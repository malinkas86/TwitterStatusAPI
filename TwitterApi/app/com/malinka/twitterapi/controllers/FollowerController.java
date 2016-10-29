package com.malinka.twitterapi.controllers;

import com.google.inject.Inject;
import com.malinka.twitterapi.models.User;
import com.malinka.twitterapi.services.TwitterService;
import com.malinka.twitterapi.utils.JsonResponse;
import com.malinka.twitterapi.utils.Message;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class FollowerController extends Controller {

    @Inject
    private TwitterService twitterService;

    /**
     * An action that returns a json response with the list of followers of a particular account.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result getFollowers() {

        String screenName=request().getQueryString("screen_name");
        if(screenName==null || screenName.length()==0 || screenName.equals("")){
            return JsonResponse.error(Message.INVALID_SCREEN_NAME_COUNT);
        }

        List<User> users=null;
        try {
            users=twitterService.getFollowers(screenName);

        } catch (Exception te) {
            te.printStackTrace();
            return JsonResponse.error(Message.GENEREL_ERROR+" "+te.getMessage());

        }


        return JsonResponse.success(users);
    }

}
