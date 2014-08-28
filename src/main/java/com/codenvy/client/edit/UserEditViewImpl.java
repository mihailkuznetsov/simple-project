package com.codenvy.client.edit;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class UserEditViewImpl extends DialogBox implements UserEditView {

    @Singleton
    interface UserEditViewImplUiBinder extends UiBinder<Widget, UserEditViewImpl> {
    }

    private ActionDelegate delegate;

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

    @Inject
    public UserEditViewImpl(UserEditViewImplUiBinder ourUiBinder) {
        setWidget(ourUiBinder.createAndBindUi(this));

        this.setAnimationEnabled(true);
        this.setGlassEnabled(true);
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getFirstName() {
        return firstName.getText();
    }

    @Override
    public String getLastName() {
        return lastName.getText();
    }

    @Override
    public String getAge() {
        return age.getText();
    }

    @Override
    public String getAddress() {
        return address.getText();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    @Override
    public void setAge(String age) {
        this.age.setText(age);
    }

    @Override
    public void setAddress(String address) {
        this.address.setText(address);
    }

    @Override
    public void setDialogTitle(String dialogTitle) {
        this.setText(dialogTitle);
    }

    @Override
    public void showDialog() {
        this.center();
        this.show();

    }

    @Override
    public void closeDialog() {
        this.hide();
    }

    @Override
    public void setFocusOnFirstName() {
        this.firstName.setFocus(true);
    }

    @UiHandler("okButton")
    public void onOkButtonClicked(ClickEvent event) {
        delegate.onOkButtonClicked();
    }

    @UiHandler("cancelButton")
    public void onCancelButtonCLicked(ClickEvent event) {
        delegate.onCancelButtonClicked();
    }

}