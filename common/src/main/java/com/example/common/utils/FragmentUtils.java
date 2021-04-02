package com.example.common.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FragmentUtils {

    public static void addFragment(FragmentManager fm , int containerId , Fragment fragment){
        if (fragment == null || fm == null || containerId == 0)
            return;
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(containerId , fragment);
        transaction.commit();
    }


    public static void hideFragment(FragmentManager fm , Fragment fragment) {
        if (fragment == null || fm == null || !fragment.isAdded())
            return;
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    public static void showFragment(FragmentManager fm , Fragment fragment) {
        if (fragment == null || fm == null || !fragment.isAdded())
            return;
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    public static void replaceFragment(FragmentManager fm , int containerId , Fragment fragment) {
        if (fragment == null || fm == null || containerId == 0)
            return;
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(containerId , fragment);
        transaction.commit();
    }

}
