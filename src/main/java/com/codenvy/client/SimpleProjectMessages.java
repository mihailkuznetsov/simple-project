package com.codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface SimpleProjectMessages extends Messages {
    SimpleProjectMessages IMPL = GWT.create(SimpleProjectMessages.class);

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

    String emptyUserDataErrorMessage();

    String emptyTableIndicator();

    String userAmount(@PluralCount int userAmount);
}
