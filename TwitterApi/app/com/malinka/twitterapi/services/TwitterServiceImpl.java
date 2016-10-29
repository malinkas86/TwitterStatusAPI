package com.malinka.twitterapi.services;

import com.google.inject.Inject;
import com.malinka.twitterapi.models.User;
import com.malinka.twitterapi.utils.HtmlBuilder;
import com.malinka.twitterapi.utils.TwitterConfigurationBuilder;
import play.Configuration;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malinkas on 5/17/16.
 */
public class TwitterServiceImpl implements TwitterService {
    private Configuration configuration;

    @Inject
    public TwitterServiceImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Retrieves the user timeline by the screenname
     * @param screenName
     * @return the list of formatted statuses
     */
    public List<com.malinka.twitterapi.models.Status> getUserTimeLine(String screenName){
        //formatted statuses will be contained
        List<com.malinka.twitterapi.models.Status> formattedStatusList=new ArrayList<>();
        try {
            TwitterConfigurationBuilder twitterConfigurationBuilder=TwitterConfigurationBuilder.getInstance(this.configuration);
            //retrieve the status list for a user
            List<Status> statuses = twitterConfigurationBuilder.getTwitter().getUserTimeline(screenName,new Paging(1,1000));

            for (Status status : statuses) {

                String statusText=status.getText();
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());

                //modify URL entities
                statusText=this.modifyURLEntity(statusText,"");

                //if user mentioned entities are there, modify with adding an anchor tag
                if(status.getUserMentionEntities()!=null && status.getUserMentionEntities().length!=0){
                    for(UserMentionEntity userMentionEntity:status.getUserMentionEntities()){
                         statusText=this.modifyWithUserMentionedEntity(statusText, userMentionEntity.getScreenName());
                    }
                }
                //if user hashtag entities are there, modify with adding an anchor tag
                if(status.getHashtagEntities()!=null && status.getHashtagEntities().length!=0){
                    for(HashtagEntity hashtagEntity:status.getHashtagEntities()){
                        statusText=this.modifyWithHashTagEntity(statusText, hashtagEntity.getText());
                    }
                }

                com.malinka.twitterapi.models.Status formattedStatus=new com.malinka.twitterapi.models.Status(statusText);
                formattedStatus.setCreatedAt(status.getCreatedAt());
                com.malinka.twitterapi.models.User user=new User(status.getUser().getName());
                user.setUserId(status.getUser().getId());
                formattedStatus.setUser(user);
                formattedStatusList.add(formattedStatus);

            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());

        }
        return formattedStatusList;
    }

    /**
     *
     * @param screenName
     * @return  the list of followers
     */
    public List<User> getFollowers(String screenName){
        List<User> followerList=new ArrayList<>();
        try{
            TwitterConfigurationBuilder twitterConfigurationBuilder=TwitterConfigurationBuilder.getInstance(this.configuration);
            long lCursor = -1;
            IDs friendsIDs = twitterConfigurationBuilder.getTwitter().getFriendsIDs(screenName, lCursor);
            System.out.println(twitterConfigurationBuilder.getTwitter().showUser(screenName).getName());
            System.out.println("==========================");

            do
            {
                for (long i : friendsIDs.getIDs())
                {
                    User user=new User(twitterConfigurationBuilder.getTwitter().showUser(i).getScreenName());
                    user.setUserId(i);
                    followerList.add(user);

                }
            }while(friendsIDs.hasNext());
        }catch(Exception e){
            e.printStackTrace();
        }
        return followerList;
    }


    /**
     *
     * @param statusText
     * @param screenName
     * @return string with user mentioned entities replaced with relevant anchor tag
     */
    private String modifyWithUserMentionedEntity(String statusText, String screenName){
        statusText=statusText.replaceAll("@"+screenName, HtmlBuilder.createAnchor("@"+screenName,configuration.getString("app.twitter.baseURL")+screenName));
        return statusText;
    }

    /**
     *
     * @param statusText
     * @param hashTagText
     * @return string with user hashtag entities replaced with relevant anchor tag
     */
    private String modifyWithHashTagEntity(String statusText, String hashTagText){
        statusText=statusText.replaceAll("#"+hashTagText, HtmlBuilder.createAnchor("#"+hashTagText,configuration.getString("app.twitter.https.baseURL")+hashTagText));
        return statusText;
    }

    /**
     *
     * @param statusText
     * @param urlText
     * @return string with user url entities replaced with relevant anchor tag
     */
    private String modifyURLEntity(String statusText,String urlText){

        statusText= statusText.replaceAll("(\\A|\\s)((http|https|ftp|mailto):\\S+)(\\s|\\z)",
                "$1"+HtmlBuilder.createAnchor("$2","$2")+"$4");
        return statusText;
    }
}
