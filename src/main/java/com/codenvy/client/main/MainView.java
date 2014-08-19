package com.codenvy.client.main;

import com.codenvy.client.User;
import com.codenvy.client.main.MainPresenter;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.List;

public interface MainView extends IsWidget{
    public interface ActionDelegate {
        void onUserSelected(User lastSelectedUser);

        void onAddButtonClicked();

        void onEditButtonClicked();

        void onDeleteButtonClicked();

        void onEnglishButtonClicked();

        void onRussianButtonClicked();
    }

    void setDelegate(ActionDelegate delegate);

    void setInfoText(String text);


    void setUsers(List<User> users);

    void setUserAmountLabel(String text);

    void setEditButtonEnabled(boolean enabled);

    void setDeleteButtonEnabled(boolean enabled);
}
