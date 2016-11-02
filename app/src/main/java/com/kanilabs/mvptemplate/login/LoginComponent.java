package com.kanilabs.mvptemplate.login;

import com.kanilabs.mvptemplate.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by razibkani on 11/1/16.
 */

@Singleton
@Component(modules = {LoginModule.class, AppModule.class})
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}