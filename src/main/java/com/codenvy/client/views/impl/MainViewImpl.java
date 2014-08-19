package com.codenvy.client.views.impl;

import com.codenvy.client.SimpleProjectConstants;
import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.User;
import com.codenvy.client.presenters.MainPresenter;
import com.codenvy.client.views.MainView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;

import java.util.List;


public class MainViewImpl extends Composite implements MainView {
    interface MyViewImplUiBinder extends UiBinder<DockLayoutPanel, MainViewImpl> {
    }

    private static MyViewImplUiBinder ourUiBinder = GWT.create(MyViewImplUiBinder.class);

    @UiField Button addButton;

    @UiField Button editButton;

    @UiField Button deleteButton;

    @UiField(provided = true) CellTable<User> table;

    @UiField Button englishButton;

    @UiField Button russianButton;

    @UiField Label info;

    @UiField Label userCount;

    private MainPresenter presenter;

    public MainViewImpl() {
        this.initCellTable();
        initWidget(ourUiBinder.createAndBindUi(this));

        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        setUserCount(0);
    }

    private void initCellTable() {
        table = new CellTable<User>();

        SimpleProjectConstants constants = SimpleProjectConstants.IMPL;

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getFirstName();
            }
        }, constants.firstName());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getLastName();
            }
        }, constants.lastName());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getAge();
            }
        }, constants.age());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getAddress();
            }
        }, constants.address());

        table.setLoadingIndicator(new Label(constants.emptyTableIndicator()));

        final NoSelectionModel<User> smodel = new NoSelectionModel<User>();
        table.setSelectionModel(smodel);
        smodel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                presenter.onUserSelected(smodel.getLastSelectedObject());
            }
        });
    }

    public void setInfoText(String text) {
        info.setText(text);
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    public void setUsers(List<User> users) {
        table.setRowData(users);
    }

    public void setUserCount(int userCount) {
        this.userCount.setText(SimpleProjectMessages.IMPL.userCount(userCount));
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

    @UiHandler("englishButton")
    public void onEnglishButtonClicked(ClickEvent event) {
        presenter.onEnglishButtonClicked();
    }

    @UiHandler("russianButton")
    public void onRussianButtonClicked(ClickEvent event) {
        presenter.onRussianButtonClicked();
    }
}