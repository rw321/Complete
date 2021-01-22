package com.example.complete.design;

/**
 * 人的模板类
 */
public abstract class PersonComplete {

    public final void desc() {
        hair();
        skin();
        eye();
    }

    protected abstract void hair();

    protected abstract void skin();

    protected abstract void eye();


}
