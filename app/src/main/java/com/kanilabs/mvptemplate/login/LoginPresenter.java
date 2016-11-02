package com.kanilabs.mvptemplate.login;

import android.text.TextUtils;
import android.util.Log;

import com.kanilabs.mvptemplate.R;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

import static android.util.Patterns.EMAIL_ADDRESS;

/**
 * Created by razibkani on 11/1/16.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    private final LoginContract.View mView;

    Subscription mSubscription = null;

    @Inject
    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void validateLogin(final Observable<CharSequence> emailObs, Observable<CharSequence> passObs) {

        mSubscription = Observable.combineLatest(emailObs, passObs,
                (newEmail, newPass) -> {

                    boolean isEmailValid = !TextUtils.isEmpty(newEmail)
                            && EMAIL_ADDRESS.matcher(newEmail).matches();

                    if (!isEmailValid)
                        mView.setEmailError();

                    boolean isPassValid = !TextUtils.isEmpty(newPass)
                            && newPass.length() > 5;

                    if (!isPassValid)
                        mView.setPassError();

                    return isEmailValid && isPassValid;

                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Boolean isValid) {
                if (isValid)
                    mView.setLoginValid();
            }
        });
    }

    @Override
    public void checkLogin(String email, String password) {
        String validEmail = mView.getLoginContext().getString(R.string.valid_email);
        String validPassword = mView.getLoginContext().getString(R.string.valid_password);

        if (email.equals(validEmail) && password.equals(validPassword))
            mView.showLoginSuccess();
        else
            mView.showLoginFailed();
    }
}