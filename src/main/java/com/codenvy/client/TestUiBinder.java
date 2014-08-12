package com.codenvy.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestUiBinder implements EntryPoint {

    public void onModuleLoad() {
        RootLayoutPanel.get().add(new SpecialPanel());
    }
}