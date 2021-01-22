package com.example.complete.dagger;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.complete.R;

import javax.inject.Inject;

import butterknife.OnClick;

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
