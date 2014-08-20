package com.codenvy.client.edit;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.User;
import com.codenvy.client.main.MainPresenter;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserEditDialogPresenter implements UserEditDialogView.ActionDelegate {
    private final UserEditDialogView view;

    private MainPresenter.CallBack callBack;

    @Inject
    public UserEditDialogPresenter(UserEditDialogView view) {
        this.view = view;
        this.view.setDelegate(this);
    }

    public void onOkButtonClicked() {
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String age = view.getAge();
        String address = view.getAddress();

        if (firstName.isEmpty() || (lastName.isEmpty()) || (age.isEmpty()) || address.isEmpty()) {
            Window.alert(SimpleProjectMessages.IMPL.emptyUserDataErrorMessage());
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

        if (user==null) {
            view.setDialogTitle(SimpleProjectMessages.IMPL.addDialogTitle());
            view.setFirstName("");
            view.setLastName("");
            view.setAge("");
            view.setAddress("");
        } else {
            view.setDialogTitle(SimpleProjectMessages.IMPL.editDialogTitle());
            view.setFirstName(user.getFirstName());
            view.setFirstName(user.getLastName());
            view.setFirstName(user.getAge());
            view.setFirstName(user.getAddress());
        }
        view.setFocusOnFirstName();
        view.showDialog();
    }
}
