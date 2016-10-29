package com.malinka.twitterapi.modules;

import com.malinka.twitterapi.utils.CursorGenerator;
import play.ApplicationLoader;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;

/**
 * Created by malinkas on 5/18/16.
 * Custome application loader which initializes the cursor map
 */
public class CustomApplicationLoader extends GuiceApplicationLoader {


    @Override
    public GuiceApplicationBuilder builder(ApplicationLoader.Context context) {
        CursorGenerator.initCursorMap();
        return initialBuilder
                .in(context.environment())
                .loadConfig(context.initialConfiguration())
                .overrides(overrides(context));
    }


}