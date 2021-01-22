package com.example.complete.dagger;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class LoginPresenter implements ICommonView {

    @NonNull
    ICommonView view;

    @Inject
    public LoginPresenter(ICommonView view) {
        this.view = view;
    }

    public void login() {
        Toast.makeText(view.getContext() , "登录...." , Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return null;
    }
}
