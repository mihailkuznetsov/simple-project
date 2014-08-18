package com.codenvy.client.presenters;

import com.codenvy.client.User;
import com.google.gwt.user.client.ui.HasWidgets;


public interface MainPresenter {
    void onUserSelected(User lastSelectedUser);

    void go(HasWidgets panel);

    void onAddButtonClicked();

    void onEditButtonClicked();

    void onDeleteButtonClicked();

    void onEnglishButtonClicked();

    void onRussianButtonClicked();
}
