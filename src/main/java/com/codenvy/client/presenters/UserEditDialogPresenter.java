package com.codenvy.client.presenters;

import com.codenvy.client.User;
import com.codenvy.client.presenters.impl.MainPresenterImpl;

public interface UserEditDialogPresenter {
    void onOkButtonClicked();

    void onCancelButtonClicked();

    void showDialog(User user, MainPresenterImpl.CallBack callBack);
}
