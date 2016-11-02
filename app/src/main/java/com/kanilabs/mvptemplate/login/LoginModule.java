package com.kanilabs.mvptemplate.login;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by razibkani on 11/1/16.
 */

@Module
public class LoginModule {

    private final LoginContract.View mView;

    public LoginModule(LoginContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @Singleton
    LoginContract.View provideView() {
        return mView;
    }
}