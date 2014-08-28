package com.codenvy.client.main;

import com.codenvy.client.model.User;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(MainViewImpl.class)
public interface MainView extends IsWidget {
    public interface ActionDelegate {
        void onUserSelected(User lastSelectedUser);

        void onAddButtonClicked();

        void onEditButtonClicked();

        void onDeleteButtonClicked();

        void onStatusButtonClicked();

        void onEnglishButtonClicked();

        void onRussianButtonClicked();
    }

    void setDelegate(ActionDelegate delegate);

    void setDecriptionText(String text);

    void setUsers(List<User> users);

    void setUserAmountLabel(String text);

    void setEditButtonEnabled(boolean enabled);

    void setDeleteButtonEnabled(boolean enabled);

    void setStatusButtonEnabled(boolean enabled);
}
