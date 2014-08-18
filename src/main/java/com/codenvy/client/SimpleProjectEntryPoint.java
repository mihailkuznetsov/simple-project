package com.codenvy.client;

import com.codenvy.client.presenters.UserEditDialogPresenter;
import com.codenvy.client.presenters.MainPresenter;
import com.codenvy.client.presenters.impl.UserEditDialogPresenterImpl;
import com.codenvy.client.presenters.impl.MainPresenterImpl;
import com.codenvy.client.views.MainView;
import com.codenvy.client.views.UserEditDialogView;
import com.codenvy.client.views.impl.MainViewImpl;
import com.codenvy.client.views.impl.UserEditDialogViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleProjectEntryPoint implements EntryPoint {
    public void onModuleLoad() {
        ResourceBundle.IMPL.css().ensureInjected();

        MainPresenter presenter = new MainPresenterImpl(new MainViewImpl(), new UserEditDialogPresenterImpl(new UserEditDialogViewImpl()), new SimpleEventBus());
        presenter.go(RootLayoutPanel.get());
    }
}