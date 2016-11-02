package com.kanilabs.mvptemplate.login;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.kanilabs.mvptemplate.BaseActivity;
import com.kanilabs.mvptemplate.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject LoginPresenter mPresenter;

    @BindView(R.id.login_email)
    EditText mFieldEmail;
    @BindView(R.id.login_password)
    EditText mFieldPass;
    @BindView(R.id.login_submit)
    Button mBtnSubmit;

    Observable<CharSequence> mObsEmailChanged;
    Observable<CharSequence> mObsPassChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mObsEmailChanged = RxTextView.textChanges(mFieldEmail).skip(1);
        mObsPassChanged = RxTextView.textChanges(mFieldPass).skip(1);

        mPresenter.validateLogin(mObsEmailChanged, mObsPassChanged);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void setupComponent() {
        DaggerLoginComponent
                .builder()
                .loginModule(new LoginModule(this))
                .build();
    }

    @Override
    protected void setupPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public Context getLoginContext() {
        return this;
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFailed() {
        Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoginValid() {
        mBtnSubmit.setEnabled(true);
    }

    @Override
    public void setEmailError() {
        mFieldEmail.setError(getString(R.string.verif_email));
    }

    @Override
    public void setPassError() {
        mFieldPass.setError(getString(R.string.verif_password));
    }

    @OnClick(R.id.login_submit)
    void doLogin() {
        mPresenter.checkLogin(mFieldEmail.getText().toString(), mFieldPass.getText().toString());
    }
}