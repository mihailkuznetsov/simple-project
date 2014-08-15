package com.codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface ResourseBundle extends ClientBundle {

    ResourseBundle IMPL = GWT.create(ResourseBundle.class);

    @ClientBundle.Source("UserDisplay.css")
    ButtonsCssResourse css();

}
