package com.example.complete.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.bean.Course;
import com.example.complete.bean.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaTestActivity extends BaseActivity {
    @BindView(R.id.tv_print)
    TextView tvPrint;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("Rxjava");
    }

    @OnClick(R.id.tv_print)
    public void print() {
        String [] datas = {"Monday" , "Tuesday" , "Wednesday" , "Thursday" , "Friday" , "Saturday" , "Sunday"};
        Observable.from(datas).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    @OnClick(R.id.tv_show)
    public void show() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(R.mipmap.a7);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<Drawable>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error!");
            }

            @Override
            public void onNext(Drawable drawable) {
                ivImage.setImageDrawable(drawable);
            }
        });

    }

    @OnClick(R.id.tv_change)
    public void changeThread() {
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("number ====" + integer);
                    }
                });
    }

    @OnClick(R.id.tv_map)
    public void mapTest() {
        Observable.just(R.mipmap.a7)
                .map(new Func1<Integer, Bitmap>() {

                    @Override
                    public Bitmap call(Integer integer) {
                        return BitmapFactory.decodeResource(getResources() , integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        ivImage.setImageBitmap(bitmap);
                    }
                });
    }

    @OnClick(R.id.tv_flatmap)
    public void flatMap() {
        List<Student> students = new ArrayList<>();
        for (int index =0; index < 10; index++) {
            Student stu = new Student();
            stu.name = "学生" + (char)(index + 65);
            stu.courses = new ArrayList<>();
            Course course = new Course();
            if (index % 3 == 0) {
                course.course = "Chinese";
            }else if (index % 3 == 1) {
                course.course = "Math";
            }else if (index % 3 == 2) {
                course.course = "English";
            }
            stu.courses.add(course);

            students.add(stu);
        }
        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.courses);
            }
        }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                System.out.println("course = " + course.course);
            }
        });
    }

}
