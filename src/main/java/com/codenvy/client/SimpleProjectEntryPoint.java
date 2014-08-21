package com.codenvy.client;

import com.codenvy.client.inject.SimpleProjectGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleProjectEntryPoint implements EntryPoint {

    public void onModuleLoad() {
        SimpleProjectGinjector injector = GWT.create(SimpleProjectGinjector.class);

        ResourceBundle.IMPL.css().ensureInjected();

        injector.getMainPresenter().go(RootLayoutPanel.get());
    }
}