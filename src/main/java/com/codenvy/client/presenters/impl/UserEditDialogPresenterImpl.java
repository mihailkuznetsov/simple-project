package com.codenvy.client.presenters.impl;

import com.codenvy.client.SimpleProjectConstants;
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
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String age = view.getAge();
        String address = view.getAddress();

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
            view.setDialogTitle(SimpleProjectConstants.IMPL.addDialogTitle());
            view.setFirstName("");
            view.setLastName("");
            view.setAge("");
            view.setAddress("");
        } else {
            view.setDialogTitle(SimpleProjectConstants.IMPL.editDialogTitle());
            view.setFirstName(user.getFirstName());
            view.setFirstName(user.getLastName());
            view.setFirstName(user.getAge());
            view.setFirstName(user.getAddress());
        }
        view.showDialog();
    }
}
