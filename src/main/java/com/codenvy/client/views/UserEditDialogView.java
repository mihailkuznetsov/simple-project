package com.codenvy.client.views;

import com.codenvy.client.presenters.UserEditDialogPresenter;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;

public interface UserEditDialogView extends IsWidget{
    TextBox getFirstName();

    TextBox getLastName();

    TextBox getAge();

    TextBox getAddress();

    void setDialogTitle(String dialogTitle);

    void showDialog();

    void setPresenter(UserEditDialogPresenter presenter);

    void closeDialogBox();
}
