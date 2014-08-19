package com.codenvy.client.views;

import com.codenvy.client.presenters.UserEditDialogPresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface UserEditDialogView extends IsWidget{
    String getFirstName();

    String getLastName();

    String getAge();

    String getAddress();

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setAge(String age);

    void setAddress(String address);

    void setDialogTitle(String dialogTitle);

    void showDialog();

    void setPresenter(UserEditDialogPresenter presenter);

    void closeDialogBox();
}
