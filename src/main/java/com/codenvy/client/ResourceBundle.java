package com.codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface ResourceBundle extends ClientBundle {
    ResourceBundle IMPL = GWT.create(ResourceBundle.class);

    @Source("SimpleProject.css")
    MainResource css();

    @Source("GWT-logo.png")
    ImageResource image();

    @Source("info_en.txt")
    TextResource info_en();

    @Source("info_ru.txt")
    TextResource info_ru();
}
