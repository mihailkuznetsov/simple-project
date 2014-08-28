package com.codenvy.client.main;

import com.codenvy.client.ResourceBundle;
import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.edit.UserEditPresenter;
import com.codenvy.client.events.ChangeToEnglishEvent;
import com.codenvy.client.events.ChangeToEnglishEventHandler;
import com.codenvy.client.events.ChangeToRussianEvent;
import com.codenvy.client.events.ChangeToRussianEventHandler;
import com.codenvy.client.model.User;
import com.codenvy.client.status.UserStatusPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class MainPresenter implements MainView.ActionDelegate {

    private final MainView view;

    private final UserEditPresenter userEditPresenter;

    private final UserStatusPresenter userStatusPresenter;

    private final EventBus eventBus;

    private final SimpleProjectMessages messages;

    private final List<User> users;

    private User lastSelectedUser;

    private final CallBack addCallBack;

    private final CallBack editCallBack;

    @Inject
    public MainPresenter(MainView mainView,
                         UserEditPresenter userEditPresenter,
                         UserStatusPresenter userStatusPresenter,
                         EventBus eventBus,
                         final SimpleProjectMessages messages) {
        this.view = mainView;
        this.view.setDelegate(this);

        this.userEditPresenter = userEditPresenter;
        this.userStatusPresenter = userStatusPresenter;
        this.eventBus = eventBus;
        eventBus.addHandler(ChangeToEnglishEvent.TYPE, new ChangeToEnglishEventHandler() {
            public void onChangeToEnglish(ChangeToEnglishEvent event) {
                view.setDecriptionText(ResourceBundle.IMPL.info_en().getText());
            }
        });
        eventBus.addHandler(ChangeToRussianEvent.TYPE, new ChangeToRussianEventHandler() {
            public void onChangeToRussian(ChangeToRussianEvent event) {
                view.setDecriptionText(ResourceBundle.IMPL.info_ru().getText());
            }
        });

        this.messages = messages;

        this.users = new ArrayList<>();

        this.addCallBack = new CallBack() {
            public void onUserChanged(User user) {
                users.add(user);
//                List<User> list = new ArrayList<>();
//                list.add(new User("1","1","1","2"));
//                list.add(new User("2","23","32","32"));
                view.setUsers(users);
                view.setUserAmountLabel(messages.userAmount(users.size()));
                view.setEditButtonEnabled(false);
                view.setDeleteButtonEnabled(false);
                view.setStatusButtonEnabled(false);
            }
        };

        this.editCallBack = new CallBack() {
            public void onUserChanged(User user) {
                users.set(users.indexOf(lastSelectedUser), user);
                view.setUsers(users);

                view.setEditButtonEnabled(false);
                view.setDeleteButtonEnabled(false);
                view.setStatusButtonEnabled(false);
            }
        };
    }

    public void onAddButtonClicked() {
        userEditPresenter.showDialog(null, addCallBack);
    }

    public void onEditButtonClicked() {
        userEditPresenter.showDialog(lastSelectedUser, editCallBack);
    }

    public void onDeleteButtonClicked() {
        users.remove(lastSelectedUser);

        view.setUsers(users);
        view.setUserAmountLabel(messages.userAmount(users.size()));

        view.setEditButtonEnabled(false);
        view.setDeleteButtonEnabled(false);
    }

    public void onStatusButtonClicked() {
        userStatusPresenter.showDialog(lastSelectedUser);
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
        view.setStatusButtonEnabled(true);

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
