package com.codenvy.client.edit;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.main.MainPresenter;
import com.codenvy.client.model.User;
import com.google.inject.Inject;

public class UserEditDialogPresenter implements UserEditDialogView.ActionDelegate {
    private final UserEditDialogView view;

    private MainPresenter.CallBack callBack;

    private final SimpleProjectMessages messages;

    @Inject
    public UserEditDialogPresenter(UserEditDialogView view, SimpleProjectMessages messages) {
        this.view = view;
        this.view.setDelegate(this);
        this.messages = messages;
    }

    public void onOkButtonClicked() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String age = view.getAge();
        String address = view.getAddress();

        if (firstName.isEmpty() || (lastName.isEmpty()) || (age.isEmpty()) || address.isEmpty()) {
//            Window.alert(messages.emptyUserDataErrorMessage());
        } else {
            callBack.onUserChanged(new User(firstName, lastName,
                    age, address));
            view.closeDialogBox();
        }
    }

    public void onCancelButtonClicked() {
        view.closeDialogBox();
    }

    public void showDialog(User user, MainPresenter.CallBack callBack) {
        this.callBack = callBack;

        if (user == null) {
            view.setDialogTitle(messages.addDialogTitle());
            view.setFirstName("");
            view.setLastName("");
            view.setAge("");
            view.setAddress("");
        } else {
            view.setDialogTitle(messages.editDialogTitle());
            view.setFirstName(user.getFirstName());
            view.setLastName(user.getLastName());
            view.setAge(user.getAge());
            view.setAddress(user.getAddress());
        }

        view.showDialog();
        view.setFocusOnFirstName();
    }
}
