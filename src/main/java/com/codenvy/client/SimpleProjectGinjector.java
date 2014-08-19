package com.codenvy.client;

import com.codenvy.client.edit.UserEditDialogPresenter;
import com.codenvy.client.main.MainPresenter;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(SimpleProjectGinModule.class)
public interface SimpleProjectGinjector extends Ginjector {
    MainPresenter getMainPresenter();
}
