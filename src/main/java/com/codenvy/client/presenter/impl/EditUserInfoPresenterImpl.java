package com.codenvy.client.presenter.impl;

import com.codenvy.client.User;
import com.codenvy.client.presenter.EditUserInfoPresenter;
import com.codenvy.client.views.EditUserInfoView;
import com.google.gwt.user.client.Window;

public class EditUserInfoPresenterImpl implements EditUserInfoPresenter {

    private final EditUserInfoView view;

    private UserDisplayPresenterImpl.CallBack callBack;

    public EditUserInfoPresenterImpl(EditUserInfoView view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    public void onOkButtonClicked() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String age = view.getAge();
        String address = view.getAddress();

        if ((firstName.trim().length()!=0) && (lastName.trim().length()!=0) &&
                (age.trim().length()!=0) && address.trim().length()!=0) {
            callBack.onUserChanged(new User(firstName, lastName,
                    age, address));
            view.closeDialogBox();
        } else {
            Window.alert("User cannot contain empty fields");
        }

    }

    public void onCancelButtonClicked() {
        view.closeDialogBox();
    }

    public void showDialog(User user, UserDisplayPresenterImpl.CallBack callBack) {
        if (user==null) {
            view.showDialog("Add a new user:", "", "", "", "");
        } else {
            view.showDialog("Edit current user", user.getFirstName(), user.getLastName(),
                    user.getAge(), user.getAddress());
        }
        this.callBack = callBack;
    }

}
