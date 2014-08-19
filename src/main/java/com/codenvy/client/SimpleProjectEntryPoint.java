package com.codenvy.client;

import com.codenvy.client.edit.UserEditDialogPresenter;
import com.codenvy.client.main.MainPresenter;
import com.codenvy.client.main.MainViewImpl;
import com.codenvy.client.edit.UserEditDialogViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleProjectEntryPoint implements EntryPoint {
    public void onModuleLoad() {
        ResourceBundle.IMPL.css().ensureInjected();

        MainPresenter presenter = new MainPresenter(new MainViewImpl(), new UserEditDialogPresenter(new UserEditDialogViewImpl()), new SimpleEventBus());
        presenter.go(RootLayoutPanel.get());
    }
}