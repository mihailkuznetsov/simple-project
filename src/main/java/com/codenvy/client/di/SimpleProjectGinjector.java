package com.codenvy.client.di;

import com.codenvy.client.main.MainPresenter;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(SimpleProjectGinModule.class)
public interface SimpleProjectGinjector extends Ginjector {
    MainPresenter getMainPresenter();
}
