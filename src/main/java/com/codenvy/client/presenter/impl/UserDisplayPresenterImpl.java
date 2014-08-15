package com.codenvy.client.presenter.impl;

import com.codenvy.client.User;
import com.codenvy.client.presenter.EditUserInfoPresenter;
import com.codenvy.client.presenter.UserDisplayPresenter;
import com.codenvy.client.views.UserDisplayView;
import com.google.gwt.user.client.ui.HasWidgets;

import java.util.ArrayList;
import java.util.List;

public class UserDisplayPresenterImpl implements UserDisplayPresenter {

    private final UserDisplayView view;

    private final EditUserInfoPresenter dialogPresenter;

    private final List<User> users;

    private User lastSelectedUser;

    public UserDisplayPresenterImpl(UserDisplayView view, EditUserInfoPresenter editUserInfoPresenter){
        this.view = view;
        this.view.setPresenter(this);
        this.users = new ArrayList<User>();
        this.dialogPresenter = editUserInfoPresenter;
    }

    public void onAddButtonClicked() {
        dialogPresenter.showDialog(null, new CallBack() {
            public void onUserChanged(User user) {
                users.add(user);
                view.setUsers(users);

                view.setEditButtonEnabled(false);
                view.setDeleteButtonEnabled(false);
            }
        });
    }

    public void onEditButtonClicked() {
        dialogPresenter.showDialog(lastSelectedUser, new CallBack() {
            public void onUserChanged(User user) {
                users.set(users.indexOf(lastSelectedUser), user);
                view.setUsers(users);

                view.setEditButtonEnabled(false);
                view.setDeleteButtonEnabled(false);
            }
        });
    }

    public void onDeleteButtonClicked() {
        users.remove(lastSelectedUser);
        view.setUsers(users);

        view.setEditButtonEnabled(false);
        view.setDeleteButtonEnabled(false);

    }

    public void onUserSelected(User lastSelectedUser) {
        view.setEditButtonEnabled(true);
        view.setDeleteButtonEnabled(true);




        this.lastSelectedUser = lastSelectedUser;
    }

    public void go(HasWidgets panel) {
        panel.clear();
        panel.add(view.asWidget());
    }

    public interface CallBack {
        void onUserChanged(User user);
    }

}
