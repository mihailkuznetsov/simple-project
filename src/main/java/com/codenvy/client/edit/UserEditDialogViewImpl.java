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

public class UserEditDialogViewImpl extends DialogBox implements UserEditDialogView {

    @Singleton
    interface EditUserInfoViewUiBinder extends UiBinder<Widget, UserEditDialogViewImpl> {
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
    public UserEditDialogViewImpl(EditUserInfoViewUiBinder ourUiBinder) {
        setWidget(ourUiBinder.createAndBindUi(this));

        this.setAnimationEnabled(true);
        this.setGlassEnabled(true);
    }

    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getAge() {
        return age.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    public void setAge(String age) {
        this.age.setText(age);
    }

    public void setAddress(String address) {
        this.address.setText(address);
    }

    public void setDialogTitle(String dialogTitle) {
        this.setText(dialogTitle);
    }

    public void showDialog() {
        this.center();
        this.show();

    }

    public void closeDialogBox() {
        this.hide();
    }

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