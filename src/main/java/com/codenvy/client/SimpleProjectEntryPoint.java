package com.codenvy.client;

import com.codenvy.client.presenter.UserEditDialogPresenter;
import com.codenvy.client.presenter.MainPresenter;
import com.codenvy.client.presenter.impl.UserEditDialogPresenterImpl;
import com.codenvy.client.presenter.impl.MainPresenterImpl;
import com.codenvy.client.views.MainView;
import com.codenvy.client.views.UserEditDialogView;
import com.codenvy.client.views.impl.MainViewImpl;
import com.codenvy.client.views.impl.UserEditDialogViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleProjectEntryPoint implements EntryPoint {

    public void onModuleLoad() {

        UserEditDialogView dialogView = new UserEditDialogViewImpl();
        UserEditDialogPresenter dialogPresenter = new UserEditDialogPresenterImpl(dialogView);

        MainView mainView = new MainViewImpl();
        MainPresenter presenter = new MainPresenterImpl(mainView, dialogPresenter);

        presenter.go(RootLayoutPanel.get());
    }
}