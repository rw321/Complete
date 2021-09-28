package com.rw.skin;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.example.common.utils.SPUtils;
import com.rw.skin.utils.SkinResource;

import java.lang.reflect.Method;
import java.util.Observable;

public class SkinManager extends Observable {

    public static final String KEY_SKIN_APK_PATH = "skin_apk_path";

    private Application mApplication;
    private ApplicationActivityLiveRecycler mSkinActivityLiveRecycler;

    private SkinManager(){}

    private static class Holder {
        private static SkinManager INSTANCE = new SkinManager();
    }

    public static SkinManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 初始化资源
     * @param mApplication
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void init(Application mApplication){
       this.mApplication = mApplication;
        SkinResource.getInstance().init(mApplication);
        mSkinActivityLiveRecycler = new ApplicationActivityLiveRecycler(this);
        mApplication.registerActivityLifecycleCallbacks(mSkinActivityLiveRecycler);

    }

    public void loadSkin(String skinApkPath) {
        if (TextUtils.isEmpty(skinApkPath)){
            SkinResource.getInstance().reset();
            SPUtils.put(KEY_SKIN_APK_PATH , "");
        }else{
            Resources appResource = mApplication.getResources();
            try {
                AssetManager assetManager = AssetManager.class.newInstance();
                Method addAssetPathMethod = assetManager.getClass().getMethod("addAssetPath");
                addAssetPathMethod.setAccessible(true);
                addAssetPathMethod.invoke(assetManager , skinApkPath);

                Resources skinResource = new Resources(assetManager , appResource.getDisplayMetrics()
                        , appResource.getConfiguration());

                SkinResource.getInstance().applySkin(skinApkPath , skinResource);

                SPUtils.put(KEY_SKIN_APK_PATH , skinApkPath);


            } catch (Exception e) {
                e.printStackTrace();
            }

            setChanged();

            notifyObservers();

        }



    }

}
