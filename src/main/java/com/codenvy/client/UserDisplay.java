package com.codenvy.client;

import com.codenvy.client.presenter.EditUserInfoPresenter;
import com.codenvy.client.presenter.UserDisplayPresenter;
import com.codenvy.client.presenter.impl.EditUserInfoPresenterImpl;
import com.codenvy.client.presenter.impl.UserDisplayPresenterImpl;
import com.codenvy.client.views.EditUserInfoView;
import com.codenvy.client.views.impl.EditUserInfoViewImpl;
import com.codenvy.client.views.impl.UserDisplayViewImpl;
import com.codenvy.client.views.UserDisplayView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class UserDisplay implements EntryPoint {

    public void onModuleLoad() {

        ResourseBundle.IMPL.css().ensureInjected();

        UserDisplayView mainView = new UserDisplayViewImpl();
        EditUserInfoView dialogView = new EditUserInfoViewImpl();
        EditUserInfoPresenter dialogPresenter = new EditUserInfoPresenterImpl(dialogView);
        UserDisplayPresenter presenter = new UserDisplayPresenterImpl(mainView, dialogPresenter);
        presenter.go(RootLayoutPanel.get());
    }
}