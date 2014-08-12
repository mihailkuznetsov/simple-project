package com.codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface Resourse extends ClientBundle {

    Resourse IMPL = GWT.create(Resourse.class);

    @ClientBundle.Source("TestUiBinder.css")
    MyCss css();

}
