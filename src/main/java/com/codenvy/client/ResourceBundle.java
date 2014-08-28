package com.codenvy.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import com.google.inject.Singleton;

@Singleton
public interface ResourceBundle extends ClientBundle {

    @Source("SimpleProject.css")
    MainResource css();

    @Source("GWT-logo.png")
    ImageResource image();

    @Source("info_en.txt")
    TextResource info_en();

    @Source("info_ru.txt")
    TextResource info_ru();

    public interface MainResource extends CssResource {
        String buttonPanel();

        String table();

        String controlButton();

        String image();

        String userAmountLabel();

        String dialogButtonPanel();

        String dialogButton();
    }
}
