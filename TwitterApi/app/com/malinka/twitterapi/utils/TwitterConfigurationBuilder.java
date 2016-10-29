package com.malinka.twitterapi.utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.Configuration;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by malinkas on 5/17/16.
 */
@Singleton
public class TwitterConfigurationBuilder {
    private static Configuration configuration;

    @Inject
    public TwitterConfigurationBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    private TwitterConfigurationBuilder() {
    }

    private static TwitterConfigurationBuilder twitterConfigurationBuilder;

    private Twitter twitter;

    public static TwitterConfigurationBuilder getInstance(Configuration configuration){
          if(twitterConfigurationBuilder==null){

              twitterConfigurationBuilder=new TwitterConfigurationBuilder();
              try {
                  ConfigurationBuilder cb = new ConfigurationBuilder();
                  cb.setDebugEnabled(true)
                          .setOAuthConsumerKey(configuration.getString("oauth.consumerKey"))
                          .setOAuthConsumerSecret(configuration.getString("oauth.consumerSecret"))
                          .setOAuthAccessToken(configuration.getString("oauth.accessToken"))
                          .setOAuthAccessTokenSecret(configuration.getString("oauth.accessTokenSecret"));


                  TwitterFactory twitterFactory = new TwitterFactory(cb.build());
                  twitterConfigurationBuilder.setTwitter(twitterFactory.getInstance());
                  twitterConfigurationBuilder.getTwitter().verifyCredentials();

              } catch (Exception te) {
                  te.printStackTrace();
                  System.out.println(te.getMessage());

              }
          }
        return twitterConfigurationBuilder;
    }

    public void setTwitter(Twitter twitter){
        this.twitter=twitter;
    }

    public Twitter getTwitter(){
        return this.twitter;
    }
}
