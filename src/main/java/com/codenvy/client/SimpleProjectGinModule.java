/*
 * Created by IntelliJ IDEA.
 * User: michail
 * Date: 19.08.14
 * Time: 16:05
 */
package com.codenvy.client;

import com.codenvy.client.edit.UserEditDialogView;
import com.codenvy.client.edit.UserEditDialogViewImpl;
import com.codenvy.client.main.MainView;
import com.codenvy.client.main.MainViewImpl;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class SimpleProjectGinModule extends AbstractGinModule {
    protected void configure() {
        bind(MainView.class).to(MainViewImpl.class).in(Singleton.class);
        bind(UserEditDialogView.class).to(UserEditDialogViewImpl.class).in(Singleton.class);
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
    }
}
