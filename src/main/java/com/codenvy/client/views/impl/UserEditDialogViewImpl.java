package com.codenvy.client.views.impl;

import com.codenvy.client.presenters.UserEditDialogPresenter;
import com.codenvy.client.views.UserEditDialogView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class UserEditDialogViewImpl extends DialogBox implements UserEditDialogView {
    interface EditUserInfoViewUiBinder extends UiBinder<Widget, UserEditDialogViewImpl> {
    }

    private static EditUserInfoViewUiBinder ourUiBinder = GWT.create(EditUserInfoViewUiBinder.class);

    private UserEditDialogPresenter presenter;

    @UiField
    TextBox firstName;

    @UiField
    TextBox lastName;

    @UiField
    TextBox age;

    @UiField
    TextBox address;

    @UiField
    Button okButton;

    @UiField
    Button cancelButton;

    public UserEditDialogViewImpl() {
        setWidget(ourUiBinder.createAndBindUi(this));
        this.setAnimationEnabled(true);
        this.setGlassEnabled(true);
    }

    public TextBox getFirstName() {
        return firstName;
    }

    public TextBox getLastName() {
        return lastName;
    }

    public TextBox getAge() {
        return age;
    }

    public TextBox getAddress() {
        return address;
    }

    public void setDialogTitle(String dialogTitle) {
        this.setText(dialogTitle);
    }

    public void showDialog() {
        this.center();
        this.show();

        this.firstName.setFocus(true);
    }

    public void setPresenter(UserEditDialogPresenter presenter) {
        this.presenter = presenter;
    }

    public void closeDialogBox() {
        this.hide();
    }

    @UiHandler("okButton")
    public void onOkButtonClicked(ClickEvent event) {
        presenter.onOkButtonClicked();
    }

    @UiHandler("cancelButton")
    public void onCancelButtonCLicked(ClickEvent event) {
        presenter.onCancelButtonClicked();
    }

}