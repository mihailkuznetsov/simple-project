package com.codenvy.client;

import com.google.gwt.i18n.client.Messages;
import com.google.inject.Singleton;

@Singleton
public interface SimpleProjectMessages extends Messages {
    String add();

    String edit();

    String delete();

    String status();

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

    String editUserInfo();

    String currentUser();

    String statusDialogTitle();
}
