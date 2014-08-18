package com.codenvy.client.presenter;

import com.codenvy.client.User;
import com.codenvy.client.presenter.impl.MainPresenterImpl;

public interface UserEditDialogPresenter {

    void onOkButtonClicked();

    void onCancelButtonClicked();

    void showDialog(User user, MainPresenterImpl.CallBack callBack);
}
