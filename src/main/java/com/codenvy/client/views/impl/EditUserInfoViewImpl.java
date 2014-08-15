package com.codenvy.client.views.impl;

import com.codenvy.client.presenter.EditUserInfoPresenter;
import com.codenvy.client.views.EditUserInfoView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class EditUserInfoViewImpl extends DialogBox implements EditUserInfoView{
    interface EditUserInfoViewUiBinder extends UiBinder<Widget, EditUserInfoViewImpl> {
    }

    private static EditUserInfoViewUiBinder ourUiBinder = GWT.create(EditUserInfoViewUiBinder.class);

    private EditUserInfoPresenter presenter;

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

    @UiField
    Label nameLabel;

    public EditUserInfoViewImpl() {
        setWidget(ourUiBinder.createAndBindUi(this));
        this.setAnimationEnabled(true);
        this.setGlassEnabled(true);
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

    public void showDialog(String dialogTitle, String firstName, String lastName, String age, String address) {

        this.setText(dialogTitle);

        this.firstName.setText(firstName);
        this.lastName.setText(lastName);
        this.age.setText(age);
        this.address.setText(address);

        this.center();
        this.show();

        this.firstName.setFocus(true);
    }


    public void setPresenter(EditUserInfoPresenter presenter) {
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