package com.example.complete.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    private ICommonView view;

    public CommonModule(ICommonView view) {
        this.view = view;
    }

    @Provides
    public ICommonView provideICommonView() {
        return this.view;
    }

}
