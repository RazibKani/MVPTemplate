package com.kanilabs.mvptemplate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by razibkani on 11/1/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent();
        setupPresenter();
    }

    protected abstract void setupComponent();

    protected abstract void setupPresenter();

}