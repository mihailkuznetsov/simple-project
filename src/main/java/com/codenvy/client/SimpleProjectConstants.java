package com.codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface SimpleProjectConstants extends Constants {
    SimpleProjectConstants IMPL = GWT.create(SimpleProjectConstants.class);

    String add();

    String edit();

    String delete();

    String ok();

    String cancel();

    String firstName();

    String lastName();

    String age();

    String address();

    String addDialogTitle();

    String editDialogTitle();

    String emptyTableIndicator();
}
