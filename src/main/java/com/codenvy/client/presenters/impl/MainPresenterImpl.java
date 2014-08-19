package com.codenvy.client.presenters.impl;

import com.codenvy.client.ResourceBundle;
import com.codenvy.client.User;
import com.codenvy.client.events.ChangeToEnglishEvent;
import com.codenvy.client.events.ChangeToEnglishEventHandler;
import com.codenvy.client.events.ChangeToRussianEvent;
import com.codenvy.client.events.ChangeToRussianEventHandler;
import com.codenvy.client.presenters.UserEditDialogPresenter;
import com.codenvy.client.presenters.MainPresenter;
import com.codenvy.client.views.MainView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {
    private final MainView view;

    private final UserEditDialogPresenter dialogPresenter;

    private final EventBus eventBus;

    private final List<User> users;

    private User lastSelectedUser;

    private final CallBack addCallBack;

    private final CallBack editCallBack;

    public MainPresenterImpl(MainView mainView, UserEditDialogPresenter userEditDialogPresenter, EventBus eventBus){
        this.view = mainView;
        this.view.setPresenter(this);

        this.dialogPresenter = userEditDialogPresenter;

        this.eventBus = eventBus;
        eventBus.addHandler(ChangeToEnglishEvent.TYPE, new ChangeToEnglishEventHandler() {
            public void onChangeToEnglish(ChangeToEnglishEvent event) {
                view.setInfoText(ResourceBundle.IMPL.info_en().getText());
            }
        });
        eventBus.addHandler(ChangeToRussianEvent.TYPE, new ChangeToRussianEventHandler() {
            public void onChangeToRussian(ChangeToRussianEvent event) {
                view.setInfoText(ResourceBundle.IMPL.info_ru().getText());
            }
        });

        this.users = new ArrayList<User>();

        this.addCallBack = new CallBack() {
            public void onUserChanged(User user) {
                users.add(user);
                view.setUsers(users);
                view.setUserCount(users.size());
                view.setEditButtonEnabled(false);
                view.setDeleteButtonEnabled(false);
            }
        };

        this.editCallBack = new CallBack() {
            public void onUserChanged(User user) {
                users.set(users.indexOf(lastSelectedUser), user);
                view.setUsers(users);

                view.setEditButtonEnabled(false);
                view.setDeleteButtonEnabled(false);
            }
        };
    }

    public void onAddButtonClicked() {
        dialogPresenter.showDialog(null, addCallBack);
    }

    public void onEditButtonClicked() {
        dialogPresenter.showDialog(lastSelectedUser, editCallBack);
    }

    public void onDeleteButtonClicked() {
        users.remove(lastSelectedUser);
        view.setUsers(users);
        view.setUserCount(users.size());
        view.setEditButtonEnabled(false);
        view.setDeleteButtonEnabled(false);
    }

    public void onEnglishButtonClicked() {
        eventBus.fireEvent(new ChangeToEnglishEvent());
    }

    public void onRussianButtonClicked() {
        eventBus.fireEvent(new ChangeToRussianEvent());
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
