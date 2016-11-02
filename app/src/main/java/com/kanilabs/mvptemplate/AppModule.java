package com.kanilabs.mvptemplate;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by razibkani on 11/1/16.
 */

@Module
public class AppModule {

    private final Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

}