package com.codenvy.client;

import com.codenvy.client.di.SimpleProjectGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleProjectEntryPoint implements EntryPoint {

    private final SimpleProjectGinjector injector = GWT.create(SimpleProjectGinjector.class);

    public void onModuleLoad() {
        ResourceBundle.IMPL.css().ensureInjected();

        injector.getMainPresenter().go(RootLayoutPanel.get());
    }
}