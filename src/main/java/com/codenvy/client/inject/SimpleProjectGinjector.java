package com.codenvy.client.inject;

import com.codenvy.client.ResourceBundle;
import com.codenvy.client.main.MainPresenter;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(SimpleProjectGinModule.class)
public interface SimpleProjectGinjector extends Ginjector {
    MainPresenter getMainPresenter();

    ResourceBundle getResourceBundle();
}
