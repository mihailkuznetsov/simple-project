package com.codenvy.client.views;

import com.codenvy.client.User;
import com.codenvy.client.presenter.MainPresenter;
import com.google.gwt.user.client.ui.IsWidget;

import java.util.List;

public interface MainView extends IsWidget{

    void setPresenter(MainPresenter presenter);

    void setUsers(List<User> users);

    void setEditButtonEnabled(boolean enabled);

    void setDeleteButtonEnabled(boolean enabled);

}
