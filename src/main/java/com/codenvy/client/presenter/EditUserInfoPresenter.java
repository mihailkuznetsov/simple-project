package com.codenvy.client.presenter;

import com.codenvy.client.User;
import com.codenvy.client.presenter.impl.UserDisplayPresenterImpl;

public interface EditUserInfoPresenter {

    void onOkButtonClicked();

    void onCancelButtonClicked();

    void showDialog(User user, UserDisplayPresenterImpl.CallBack callBack);
}
