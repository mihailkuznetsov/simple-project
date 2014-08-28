package com.codenvy.client.edit;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.main.MainPresenter;
import com.codenvy.client.model.User;
import com.google.inject.Inject;

public class UserEditPresenter implements UserEditView.ActionDelegate {
    private final UserEditView view;

    private MainPresenter.CallBack callBack;

    private final SimpleProjectMessages messages;

    @Inject
    public UserEditPresenter(UserEditView view, SimpleProjectMessages messages) {
        this.view = view;
        this.view.setDelegate(this);
        this.messages = messages;
    }

    @Override
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
            view.closeDialog();
        }
    }

    @Override
    public void onCancelButtonClicked() {
        view.closeDialog();
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
