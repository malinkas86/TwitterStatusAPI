package com.malinka.twitterapi.modules;

import com.google.inject.AbstractModule;
import com.malinka.twitterapi.services.StatusService;
import com.malinka.twitterapi.services.StatusServiceImpl;
import com.malinka.twitterapi.services.TwitterService;
import com.malinka.twitterapi.services.TwitterServiceImpl;

/**
 * Created by malinkas on 1/10/16.
 */
public class BindingModule extends AbstractModule {
    protected void configure() {
        bind(TwitterService.class).to(TwitterServiceImpl.class);
        bind(StatusService.class).to(StatusServiceImpl.class);
    }
}
