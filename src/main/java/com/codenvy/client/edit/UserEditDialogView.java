package com.codenvy.client.edit;

import com.google.gwt.user.client.ui.IsWidget;

public interface UserEditDialogView extends IsWidget{
    public interface ActionDelegate {
        void onOkButtonClicked();

        void onCancelButtonClicked();
    }

    void setDelegate(ActionDelegate delegate);

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

    void closeDialogBox();

    void setFocusOnFirstName();
}
