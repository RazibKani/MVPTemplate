package com.kanilabs.mvptemplate.login;

import android.content.Context;

import com.kanilabs.mvptemplate.BasePresenter;
import com.kanilabs.mvptemplate.BaseView;

import rx.Observable;

/**
 * Created by razibkani on 11/1/16.
 */

public interface LoginContract {

    interface View extends BaseView {
        Context getLoginContext();
        void showLoginSuccess();
        void showLoginFailed();
        void setLoginValid();
        void setEmailError();
        void setPassError();
    }

    interface Presenter extends BasePresenter {
        void validateLogin(Observable<CharSequence> emailObs, Observable<CharSequence> passObs);
        void checkLogin(String email, String password);
    }

}