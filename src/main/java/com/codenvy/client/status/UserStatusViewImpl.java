package com.codenvy.client.status;

import com.codenvy.client.SimpleProjectMessages;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;


public class UserStatusViewImpl extends DialogBox implements UserStatusView {
    interface UserStatusViewImplUiBinder extends UiBinder<Widget, UserStatusViewImpl> {
    }

    private SimpleProjectMessages messages;

    @UiField
    Button okButton;

    @UiField
    Button cancelButton;

    @UiField
    Label userInfo;

    @UiField
    TextArea statusText;

    private UserStatusView.ActionDelegate delegate;

    @Inject
    public UserStatusViewImpl(UserStatusViewImplUiBinder ourUiBinder, SimpleProjectMessages messages) {
        this.messages = messages;

        this.setAnimationEnabled(true);
        this.setGlassEnabled(true);

        this.setText(this.messages.statusDialogTitle());

        setWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void closeDialog() {
        this.hide();
    }

    @Override
    public void showDialog() {
        this.center();
        this.show();
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setUserStatusText(String text) {
        this.statusText.setText(text);
    }

    @Override
    public void setUserInfo(String title) {
        userInfo.setText(title);
    }

    @Override
    public String getUserStatusText() {
        return statusText.getText();
    }

    @UiHandler("okButton")
    public void onOkButtonClicked(ClickEvent event) {
        delegate.onOkButtonClicked();
    }

    @UiHandler("cancelButton")
    public void onCancelButtonClicked(ClickEvent event) {
        delegate.onCancelButtonClicked();
    }
}