package com.rw.skin.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SkinResource {

    private Resources mAppResource;
    private Resources mSkinResource;
    private String mSkinPkgName;
    private Boolean isDefaultSkin = true;

    private SkinResource() {
    }

    private static class Holder {
        private static SkinResource INSTANCE = new SkinResource();
    }

    public static SkinResource getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Context context) {
        mAppResource = context.getResources();
    }

    public void reset(){
        mSkinResource = null;
        mSkinPkgName = "";
        isDefaultSkin = true;
    }

    public void applySkin(String skinPkgName , Resources resources) {
        mSkinPkgName = skinPkgName;
        mSkinResource = resources;
        isDefaultSkin = false;
    }

    public int getIdentifier(int resId) {
        if (isDefaultSkin)
            return resId;

        String resName = mAppResource.getResourceEntryName(resId);
        String resType = mAppResource.getResourceTypeName(resId);
        int skinId = mSkinResource.getIdentifier(resName , resType , mSkinPkgName);
        return skinId;

    }

    public int getColor(int resId) {
        if (isDefaultSkin)
            return mAppResource.getColor(resId);
        int skinId = getIdentifier(resId);
        return skinId == 0? mAppResource.getColor(resId): mSkinResource.getColor(skinId);
    }

    public Drawable getDrawable(int resId) {
        if (isDefaultSkin)
            return mAppResource.getDrawable(resId);
        int skinId = getIdentifier(resId);
        return skinId == 0? mAppResource.getDrawable(resId): mSkinResource.getDrawable(skinId);
    }

    /**
     * 可能是Color 也可能是drawable
     *
     * @return
     */
    public Object getBackground(int resId) {
        String resourceTypeName = mAppResource.getResourceTypeName(resId);

        if ("color".equals(resourceTypeName)) {
            return getColor(resId);
        } else {
            // drawable
            return getDrawable(resId);
        }
    }

    public ColorStateList getColorStateList(int resId) {
        if (isDefaultSkin) {
            return mAppResource.getColorStateList(resId);
        }
        int skinId = getIdentifier(resId);
        if (skinId == 0) {
            return mAppResource.getColorStateList(resId);
        }
        return mSkinResource.getColorStateList(skinId);
    }


}
