package com.example.complete.dagger;

import dagger.Component;

@Component(modules = CommonModule.class)
public interface CommonComponent {
    void inject(DaggerTestActivity activity);

}
