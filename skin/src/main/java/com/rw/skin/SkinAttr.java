package com.rw.skin;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.rw.skin.utils.SkinResource;
import com.rw.skin.utils.SkinTheme;

import java.util.ArrayList;
import java.util.List;

public class SkinAttr {

    private static final List<String> mAttributes = new ArrayList<>();

    static {
        mAttributes.add("background");
        mAttributes.add("src");
        mAttributes.add("textColor");
        mAttributes.add("drawableLeft");
        mAttributes.add("drawableTop");
        mAttributes.add("drawableRight");
        mAttributes.add("drawableBottom");

    }

    private List<SkinView> mSkinViews = new ArrayList<>();

    public void applySkin(){
        for (SkinView skinView :mSkinViews) {
            skinView.applySkin();
        }
    }

    public void look(View view , AttributeSet attr){
        List<SkinAttrPair> pairs = new ArrayList<>();
        for (int i = 0 ;i < attr.getAttributeCount();i++) {
            String name = attr.getAttributeName(i);
            if (mAttributes.contains(name)) {
                String value = attr.getAttributeValue(i);
                if (value.startsWith("#"))
                    continue;
                int resId;
                if (value.startsWith("?")) {
                    int attrId = Integer.parseInt(value.substring(1));
                    resId = SkinTheme.getResId(view.getContext() , new int[]{attrId})[0];
                }else {
                    resId = Integer.parseInt(value.substring(1));
                }
                SkinAttrPair pair = new SkinAttrPair(name , resId);
                pairs.add(pair);
            }
        }
        if (!pairs.isEmpty() || view instanceof SkinViewSupport){
            SkinView skinView = new SkinView(view , pairs);
            skinView.applySkin();
            mSkinViews.add(skinView);
        }

    }

    public static class SkinView{
        private View view;
        private List<SkinAttrPair> attrs;

        public SkinView(View view, List<SkinAttrPair> attrs) {
            this.view = view;
            this.attrs = attrs;
        }

        public void applySkin(){
            applySkinSupport();
            for (SkinAttrPair pair : attrs) {
                Drawable left = null, top = null, right = null, bottom = null;
                switch (pair.attrName) {
                    case "background":
                        Object background = SkinResource.getInstance().getBackground(pair.resId);
                        if (background instanceof Integer) {
                            view.setBackgroundColor((Integer) background);
                        }else {
                            ViewCompat.setBackground(view , (Drawable) background);
                        }
                        break;
                    case "src":
                        Object src = SkinResource.getInstance().getBackground(pair.resId);
                        if (src instanceof Integer) {
                            ((ImageView)view).setImageDrawable(new ColorDrawable((Integer) src));
                        }else {
                            ((ImageView)view).setImageDrawable((Drawable) src);
                        }
                        break;
                    case "textColor":
                        ColorStateList color = SkinResource.getInstance().getColorStateList(pair.resId);
                        ((TextView)view).setTextColor(color);
                        break;
                    case "drawableLeft":
                        left = SkinResource.getInstance().getDrawable(pair.resId);
                        left.setBounds(0 , 0 , left.getMinimumWidth() , left.getMinimumHeight());
                        break;
                    case "drawableTop":
                        top = SkinResource.getInstance().getDrawable(pair.resId);
                        top.setBounds(0 , 0 , left.getMinimumWidth() , left.getMinimumHeight());
                        break;
                    case "drawableRight":
                        right = SkinResource.getInstance().getDrawable(pair.resId);
                        right.setBounds(0 , 0 , left.getMinimumWidth() , left.getMinimumHeight());
                        break;
                    case "drawableBottom":
                        bottom = SkinResource.getInstance().getDrawable(pair.resId);
                        bottom.setBounds(0 , 0 , left.getMinimumWidth() , left.getMinimumHeight());
                        break;

                }
                if (left != null || top != null || right != null || bottom != null){
                    ((TextView)view).setCompoundDrawables(left , top , right , bottom);
                }
            }
        }


        private void applySkinSupport(){
            if (view instanceof SkinViewSupport) {
                ((SkinViewSupport)view).applySkin();
            }
        }

    }

    public static class SkinAttrPair {
        private String attrName;
        private int resId;

        public SkinAttrPair(String attrName, int resId) {
            this.attrName = attrName;
            this.resId = resId;
        }
    }

}
