package com.example.complete.generic;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;
import com.example.complete.databinding.ActivityGenericBinding;


/**
 * 缺陷 :
 * 不能实例化
 * 不能作用在静态域 , 泛型方法可以用static修饰
 * 不能用instanceOf
 * 泛型不能用基础数据类型,必须用包装类型
 * 泛型类获取类名的时候只能获取到原生类型,类型参数获取不到
 * 可以用泛型声明数组,但是不能实例化
 * 泛型类不能派生自Expection
 * 不能捕获泛型类的异常(可以捕获到异常之后,抛出泛型类的对象)
 *
 * JDK处理泛型
 * 泛型擦除
 * 普通泛型类会变成Object
 * 泛型类型限定会变成泛型的第一个限定类型,如果调用其他类型的方法,会在合适的地方进行强转
 *
 * Signature会保留泛型信息
 *
 */
public class GenericTestActivity extends BaseActivity<ActivityGenericBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_generic;
    }

    @Override
    protected void initData() {
        super.initData();
        Pair<Person> pair1 = new Pair<>();
        Pair<Worker> pair2 = new Pair<>();
        //pair1和pair2之间没有任何继承关系
        //pair1 = new Pair<Worker>();
        //类型参数之间的继承关系,不代表泛型类的继承关系
        //泛型可以继承或者扩展其他泛型类
        Pair<Person> pair3 = new ExpandPair<>();

        print(new Generic<Fruit>());
        //print(new Generic<Apple>()); 报错

        print2(new Generic<Apple>());
        print2(new Generic<Hongfushi>());
        //print2(new Generic<Fruit>());   //? extends 确定了泛型的上限

        print3(new Generic<Apple>());
        print3(new Generic<Fruit>());
        //print3(new Generic<Hongfushi>());  ? super 确定了泛型的下限


    }

    private void print(Generic<Fruit> p) {

    }

    /**
     * 安全的访问数据
     * @param p 泛型只能是Apple和它的子类
     *
     * */
    private void print2(Generic<? extends Apple> p) {
        Apple data = p.getData();
        //Hongfushi hongfushi = p.getData();  //get方法获取到的类型只能是该泛型的上限

//        p.setData(new Apple());
//        p.setData(new Hongfushi());   //因为p的类型不确定,出于安全考虑,不允许设置数据

    }


    /**
     * 安全的写入数据,只能写入下限类和其子类
     * @param p 泛型只能是Apple和它的父类
     */
    private void print3(Generic<? super Apple> p) {
        p.setData(new Apple());
        p.setData(new Hongfushi());
        //p.setData(new Fruit());  因为? super 确定了泛型的下限
        // ,所以setData设置下限及其子类(根据多态的原则,子类可以赋值给父类)是安全的
        // 下限类和其子类可以安全的转型

        Object data = p.getData();
        //getData()返回下限类的超类 ,Object因为是所有类的超类
        //所以返回Object类是安全的
    }


}
