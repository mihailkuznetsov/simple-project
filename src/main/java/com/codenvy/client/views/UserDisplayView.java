package com.codenvy.client.views;

import com.codenvy.client.User;
import com.codenvy.client.presenter.UserDisplayPresenter;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.List;

public interface UserDisplayView extends IsWidget{

    void setPresenter(UserDisplayPresenter presenter);

    void setUsers(List<User> users);

    void setEditButtonEnabled(boolean enabled);

    void setDeleteButtonEnabled(boolean enabled);
}
