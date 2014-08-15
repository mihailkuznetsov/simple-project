package com.codenvy.client.views;

import com.codenvy.client.presenter.EditUserInfoPresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface EditUserInfoView extends IsWidget{

    String getFirstName();

    String getLastName();

    String getAge();

    String getAddress();

    void showDialog(String dialogTitle, String firstName, String lastName, String age, String address);

    void setPresenter(EditUserInfoPresenter presenter);

    void closeDialogBox();
}
