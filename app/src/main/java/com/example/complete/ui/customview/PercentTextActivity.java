package com.example.complete.ui.customview;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityPerentTextBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PercentTextActivity extends BaseActivity<ActivityPerentTextBinding> {

    private Disposable subscribe;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_perent_text;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribe = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("=========" + aLong);
                        mContentBinding.ptvText.setPercent(aLong / 10f);
                        if (aLong == 10) {
                            cancel();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("问题是:" + throwable.getMessage());
                    }
                });
    }

    private void cancel() {
        if (subscribe != null && !subscribe.isDisposed()) subscribe.dispose();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancel();
    }
}
