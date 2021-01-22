package com.example.complete.dagger;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public interface ICommonView {

    Context getContext();

    default void launchActivity(@NonNull Intent intent) {
        int i = 1;
    }

}
