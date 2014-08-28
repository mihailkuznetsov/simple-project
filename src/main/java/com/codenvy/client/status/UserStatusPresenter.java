package com.codenvy.client.status;

import com.codenvy.client.model.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserStatusPresenter implements UserStatusView.ActionDelegate {

    private UserStatusView view;

    private User selectedUser;

    @Inject
    public UserStatusPresenter(UserStatusView view) {
        this.view = view;
        this.view.setDelegate(this);
    }


    @Override
    public void onOkButtonClicked() {
        selectedUser.setStatus(view.getUserStatusText());
        view.closeDialog();
    }

    @Override
    public void onCancelButtonClicked() {
        view.closeDialog();
    }

    public void showDialog(User user) {
        selectedUser = user;
        view.setUserInfo(user.getFullInfo());
        view.setUserStatusText(user.getStatus());
        view.showDialog();
    }
}
