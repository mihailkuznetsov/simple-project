package com.codenvy.client.presenters.impl;

import com.codenvy.client.User;
import com.codenvy.client.presenters.UserEditDialogPresenter;
import com.codenvy.client.views.UserEditDialogView;
import com.google.gwt.user.client.Window;

public class UserEditDialogPresenterImpl implements UserEditDialogPresenter {
    private final UserEditDialogView view;

    private MainPresenterImpl.CallBack callBack;

    public UserEditDialogPresenterImpl(UserEditDialogView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    public void onOkButtonClicked() {
        String firstName = view.getFirstName().getText();
        String lastName = view.getLastName().getText();
        String age = view.getAge().getText();
        String address = view.getAddress().getText();

        if (firstName.isEmpty() || (lastName.isEmpty()) || (age.isEmpty()) || address.isEmpty()) {
            Window.alert("User cannot contain empty fields");
        } else {
            callBack.onUserChanged(new User(firstName, lastName,
                    age, address));
            view.closeDialogBox();
        }
    }

    public void onCancelButtonClicked() {
        view.closeDialogBox();
    }

    public void showDialog(User user, MainPresenterImpl.CallBack callBack) {
        this.callBack = callBack;

        if (user==null) {
            view.setDialogTitle("Add a new user:");
            view.getFirstName().setText("");
            view.getLastName().setText("");
            view.getAge().setText("");
            view.getAddress().setText("");
        } else {
            view.setDialogTitle("Edit current user:");
            view.getFirstName().setText(user.getFirstName());
            view.getLastName().setText(user.getLastName());
            view.getAge().setText(user.getAge());
            view.getAddress().setText(user.getAddress());
        }
        view.showDialog();
    }
}
