package com.codenvy.client.status;

import com.google.inject.ImplementedBy;

@ImplementedBy(UserStatusViewImpl.class)
public interface UserStatusView {
    public interface ActionDelegate {
        void onOkButtonClicked();

        void onCancelButtonClicked();
    }

    void closeDialog();

    void showDialog();

    void setDelegate(ActionDelegate delegate);

    void setUserStatusText(String text);

    void setUserInfo(String title);

    String getUserStatusText();
}
