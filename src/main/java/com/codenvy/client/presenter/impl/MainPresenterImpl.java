package com.codenvy.client.presenter.impl;

import com.codenvy.client.User;
import com.codenvy.client.presenter.UserEditDialogPresenter;
import com.codenvy.client.presenter.MainPresenter;
import com.codenvy.client.views.MainView;
import com.google.gwt.user.client.ui.HasWidgets;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {

    private final MainView view;

    private final UserEditDialogPresenter dialogPresenter;

    private final List<User> users;

    private User lastSelectedUser;

    public MainPresenterImpl(MainView view, UserEditDialogPresenter userEditDialogPresenter){
        this.view = view;
        this.view.setPresenter(this);

        this.dialogPresenter = userEditDialogPresenter;

        this.users = new ArrayList<User>();
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
