package com.example.complete.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.TimeUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.bean.Course;
import com.example.complete.bean.Student;
import com.example.complete.databinding.ActivityRxBinding;


import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class RxJavaTestActivity extends BaseActivity<ActivityRxBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("Rxjava");
//        create();
//        map();
//        filter();
//        combination();
//        assit();
//        calc();
//        concat();
//        connect();
//        single();
    }

    private void scheduler() {
        Scheduler.Worker worker = Schedulers.newThread().createWorker();
        worker.schedule(new Runnable() {
            @Override
            public void run() {

                if (!worker.isDisposed())
                    worker.dispose();

            }
        });


    }

    private void single() {
//        Single.create(new SingleOnSubscribe<String>() {
//            @Override
//            public void subscribe(SingleEmitter<String> emitter) throws Exception {
//
//            }
//        }).subscribe();
//
//        Single.fromPublisher(new Publisher<String>() {
//            @Override
//            public void subscribe(Subscriber<? super String> s) {
//
//            }
//        });
//
//        Single.just(1).map(integer -> integer).subscribe();

        Single.merge(Single.just(1) , Single.just(2))
                .subscribe(integer-> System.out.println("======" + integer));


        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {

            }
        } , BackpressureStrategy.BUFFER);


    }


    private void connect() {
        Observable observable = Observable.interval(1 , TimeUnit.SECONDS).take(10);
        ConnectableObservable publish = observable.replay();
        publish.subscribe(aLong -> System.out.println("first subscribe=====" + aLong));
        publish.connect();
        publish.delaySubscription(3 , TimeUnit.SECONDS)
                .subscribe(aLong->System.out.println("second subscribe=====" + aLong));
    }

    private void reduce() {
        Observable.just(1 , 2 ,3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(integer -> System.out.println("=======" + integer));
    }

    private void concat() {
        Observable.concat(Observable.just(1 , 2 , 3), Observable.just(2 , 3))
                .subscribe(integer -> System.out.println("=====" + integer));
    }

    private void calc() {
        Observable.just(1 , 2 ,3 , 4)

                .count().subscribe(aLong -> System.out.println("====" + aLong));
    }

    private void assit(){
        Integer[] data = {1 , 2 , 3 , 4 ,4, 5 , 6 , 7 , 8};


//        Observable.fromArray(data)
//                .delay(1 , TimeUnit.SECONDS)
//                .doOnEach(integerNotification -> System.out.println("===" + integerNotification.getValue()))
//                .doOnNext(integer -> System.out.println("===" + integer))
//                .doOnSubscribe(disposable -> System.out.println("订阅成功回调"))
//                .doOnComplete(() -> System.out.println("正常完成时回调"))
//                .doOnError(throwable -> System.out.println("错误时回调"))
//                .doOnTerminate(()->System.out.println("终止之前被调用(不论是complete还是error)"))
//                .doFinally(()->System.out.println("终止之后被调用(不论是complete还是error)"))
//                .subscribe(integer -> System.out.println("==========" + integer));

//                .timestamp(TimeUnit.SECONDS)
//                .subscribe(integerTimed -> integerTimed.time());

//                .toList()
//                .subscribe(integers -> System.out.println(integers));

//                .all(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer > 0;
//                    }
//                }).subscribe(aBoolean -> System.out.println("====" + aBoolean));

//        timeout();
//        amb();
//        skipUtils();
//        skipWhile();
    }

    private void sequenceEqual() {
        Observable.sequenceEqual(Observable.just(1 , 2) , Observable.just(1 , 2))
                .subscribe(aBoolean -> {});
    }

    private void skipWhile() {
        Observable.interval(1 , TimeUnit.SECONDS)
//            .skipWhile(aLong -> aLong < 4).take(4)
                .takeUntil(Observable.timer(4 , TimeUnit.SECONDS))
            .subscribe(aLong -> System.out.println("=====" + aLong));
    }

    private void skipUtils() {
        Observable.interval(1 , TimeUnit.SECONDS)
                .skipUntil(Observable.timer(3 , TimeUnit.SECONDS))
                .take(4).subscribe(aLong -> System.out.println("=======" + aLong));
    }

    private void contains() {
        Integer[] data = {1 , 2 , 3 , 4 ,4, 5 , 6 , 7 , 8};
        Observable.fromArray(data)
//                .contains(2)
//                .subscribe(aBoolean -> System.out.println("=====" + aBoolean));

        .defaultIfEmpty(3)
                .subscribe(integer -> System.out.println(""));

    }

    private void amb() {
        List<Observable<String>> data = new ArrayList<>();
        data.add(Observable.just("hello").delay(2 , TimeUnit.SECONDS));
        data.add(Observable.just("hello2").delay(1 , TimeUnit.SECONDS));
        Observable.amb(data).subscribe(s -> System.out.println("======" + s));
    }

    private void timeout() {
        Observable.never().timeout(1 , TimeUnit.SECONDS).subscribe(
                obj->{},
                throwable -> System.out.println("=====超时了"));
    }

    private void combination() {
//        Integer[] data = {1 , 2 , 3 , 4 ,4, 5 , 6 , 7 , 8};
//        Observable.fromArray(data)
//                .startWith(0)
//                .subscribe(integer -> System.out.println("=======" + integer));

//        merge();
//        join();
//        switch1();
//        zip();
        combineLatest();
    }

    private void combineLatest() {
        Observable.combineLatest(
                Observable.interval(1, TimeUnit.SECONDS),
                Observable.interval(2, TimeUnit.SECONDS),
                (aLong, aLong2) -> aLong + "====" + aLong2

        ).subscribe(s -> System.out.println(s));
    }

    private void andThenWhen() {
        Integer[] data = {1 , 2 , 3 , 4 ,4, 5 , 6 , 7 , 8};
        Observable source = Observable.fromArray(data);
        Observable<Long> tictoc = Observable.interval(1, TimeUnit.SECONDS);

    }

    private void zip(){
        Observable.zip(Observable.just("a" , "b")
        ,Observable.just(1 , 2) , (s , integer) -> s + integer)
                .subscribe(s-> System.out.println(s));
    }

    private void switch1() {
        Observable.switchOnNext(
                Observable.interval(100 , TimeUnit.MILLISECONDS)
                .map(aLong -> Observable.interval(20 , TimeUnit.MILLISECONDS)
                        .map(i2 -> aLong)
                )
        ).take(40).subscribe(aLong -> System.out.println("=========" + aLong));

    }

    private void join() {
        Observable.just("a" , "b")
                .join(Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; i < 4; i++) {
                            emitter.onNext(i);
                        }
                    }
                }), s-> Observable.timer(3000 , TimeUnit.MILLISECONDS)
                 , integer -> Observable.timer(2000 , TimeUnit.MILLISECONDS)
                ,(s , integer) -> s+ integer)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }


    private void merge() {
        Observable.merge(Observable.just(1) , Observable.just(2))
                .subscribe(integer -> System.out.println("=======" + integer));
    }

    private void filter() {
        Integer[] data = {1 , 2 , 3 , 4 ,4, 5 , 6 , 7 , 8};
//        Integer[] data = {1 , 2 };
        Observable.fromArray(data)
//                .filter(integer -> integer > 3)
//                .takeLast(3)
//                .elementAt(3)
//                .distinct()
                .debounce(1000 , TimeUnit.MILLISECONDS)
                .subscribe(integer -> System.out.println("=======" + integer));
    }

    private static void create() {

//        Observable
//                .empty()
//                .error(new NullPointerException())
//                .never()
//                .subscribe(new Observer<Object>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("======订阅");
//            }
//
//            @Override
//            public void onNext(Object o) {
//                System.out.println("======onNext");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("======onError");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("======onComplete");
//            }
//        });

//        Observable
//                .defer(new Callable<Observable<Integer>>() {
//                    @Override
//                    public Observable<Integer> call() throws Exception {
//                        return Observable.just(2);
//                    }
//                }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                System.out.println("============defer:" + integer);
//            }
//        });

//        Observable
//                .interval(1000 , 1000 , TimeUnit.MILLISECONDS)
//                .timer(1000 , TimeUnit.MILLISECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        System.out.println("=========" + aLong);
//                    }
//                });

//        Integer [] data ={1 , 2 , 3};
//
//        Observable.fromArray(data).subscribe(ints -> System.out.println("==========" + ints));

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext(1);
            }
        })
                .map(new Function<Object, String>() {
                    @Override
                    public String apply(Object o) throws Exception {
                        return "1";
                    }
                })

                .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void show() {

        Observable.create((ObservableOnSubscribe<Drawable>) subscriber -> {
            Drawable drawable = getResources().getDrawable(R.mipmap.a7);
            subscriber.onNext(drawable);

        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Drawable drawable) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void changeThread() {
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> System.out.println("==============" + integer));


        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });

    }

    public void map() {
//        Observable.just(R.mipmap.a7)
//                .map(integer -> BitmapFactory.decodeResource(getResources() , integer))
//                .subscribeOn(Schedulers.io())
//                  .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(bitmap -> mContentBinding.ivImage.setImageBitmap(bitmap));
//        flatMap();
//        scan();
//        groupBy();
//        cast();
    }


    /**
     * 在发射前,将数据项强制转换成制定类型(强转失败,如果有onError,则回调onError,如果没有,崩溃)
     */
    public void cast() {
        Integer[] data = {1 , 2 , 3 , 4 , 5 , 6 , 7 , 8};
        Observable.fromArray(data)
                .cast(String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("=============");
                    }
                });
    }

    public void groupBy() {

        Integer[] data = {1 , 2 , 3 , 4 , 5 , 6 , 7 , 8};
        Observable.fromArray(data).groupBy(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer value) throws Exception {

                return value%3;
            }
        }).subscribe(new Consumer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void accept(GroupedObservable<Integer, Integer> observable) throws Exception {
                observable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                       System.out.println(observable.getKey() + "======" + integer);
                    }
                });
            }
        });

    }

    public void scan() {
        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        Observable.fromIterable(data)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        System.out.println(integer + "======" + integer2);
                        return integer2 ;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("============" + integer);
            }
        });
    }

    public void flatMap() {

        Student stu = new Student();
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("语文"));
        courses.add(new Course("数学"));
        courses.add(new Course("英语"));
        stu.courses = courses;

        Observable.just(stu)
                .flatMap(new Function<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> apply(Student student) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<Course>() {
                            @Override
                            public void subscribe(ObservableEmitter<Course> emitter) throws Exception {
                                for (Course course : student.courses)
                                    emitter.onNext(course);
                            }
                        });
                    }
                }).subscribe(new Consumer<Course>() {
            @Override
            public void accept(Course courses) throws Exception {
                    System.out.println("===========" + courses.course);
            }
        });

    }

}
