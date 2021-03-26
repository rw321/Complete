package com.example.complete.dagger;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.complete.R;

import javax.inject.Inject;


public class DaggerTestActivity extends AppCompatActivity implements ICommonView {

    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        DaggerCommonComponent.builder().commonModule(new CommonModule(this)).build().inject(this);
    }

    public void login(View view) {
        mLoginPresenter.login();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
