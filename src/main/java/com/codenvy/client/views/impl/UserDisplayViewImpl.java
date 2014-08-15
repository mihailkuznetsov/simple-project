package com.codenvy.client.views.impl;

import com.codenvy.client.User;
import com.codenvy.client.presenter.UserDisplayPresenter;
import com.codenvy.client.views.UserDisplayView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.List;


public class UserDisplayViewImpl extends Composite implements UserDisplayView {

    interface MyViewImplUiBinder extends UiBinder<DockLayoutPanel, UserDisplayViewImpl> {
    }

    private static MyViewImplUiBinder ourUiBinder = GWT.create(MyViewImplUiBinder.class);

    @UiField Button addButton;

    @UiField Button editButton;

    @UiField Button deleteButton;

    @UiField(provided = true) CellTable<User> table;

    private UserDisplayPresenter presenter;

    public UserDisplayViewImpl() {
        table = new CellTable<User>();
        initCellTable();
        initWidget(ourUiBinder.createAndBindUi(this));
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    private void initCellTable() {
        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getFirstName();
            }
        }, "First Name");
        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getLastName();
            }
        }, "Last Name");
        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getAge();
            }
        }, "Age");
        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getAddress();
            }
        }, "Address");

        table.setLoadingIndicator(new Label("The table is empty"));

        final NoSelectionModel<User> smodel = new NoSelectionModel<User>();
        table.setSelectionModel(smodel);
        smodel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                presenter.onUserSelected(smodel.getLastSelectedObject());
            }
        });
    }

    public void setPresenter(UserDisplayPresenter presenter) {
        this.presenter = presenter;
    }

    public void setUsers(List<User> users) {
        table.setRowData(users);
    }

    public void setEditButtonEnabled(boolean enabled) {
        editButton.setEnabled(enabled);
    }

    public void setDeleteButtonEnabled(boolean enabled) {
        deleteButton.setEnabled(enabled);
    }

    @UiHandler("addButton")
    public void onAddButtonClicked(ClickEvent event) {
            presenter.onAddButtonClicked();
    }

    @UiHandler("editButton")
    public void onEditButtonClicked(ClickEvent event) {
            presenter.onEditButtonClicked();
    }

    @UiHandler("deleteButton")
    public void onDeleteButtonClicked(ClickEvent event) {
            presenter.onDeleteButtonClicked();
    }


}