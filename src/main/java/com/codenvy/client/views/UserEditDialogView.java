package com.codenvy.client.views;

import com.codenvy.client.presenter.UserEditDialogPresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface UserEditDialogView extends IsWidget{

    String getFirstName();

    String getLastName();

    String getAge();

    String getAddress();

    void showDialog(String dialogTitle, String firstName, String lastName, String age, String address);

    void setPresenter(UserEditDialogPresenter presenter);

    void closeDialogBox();
}
