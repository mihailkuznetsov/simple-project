package com.codenvy.client.main;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.User;
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

    @UiField Label userAmountLabel;

    private ActionDelegate delegate;

    public MainViewImpl() {
        initCellTable();
        initWidget(ourUiBinder.createAndBindUi(this));
        userAmountLabel.setText(SimpleProjectMessages.IMPL.userAmount(0));
    }

    private void initCellTable() {
        table = new CellTable<User>();

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getFirstName();
            }
        }, SimpleProjectMessages.IMPL.firstName());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getLastName();
            }
        }, SimpleProjectMessages.IMPL.lastName());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getAge();
            }
        }, SimpleProjectMessages.IMPL.age());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getAddress();
            }
        }, SimpleProjectMessages.IMPL.address());

        table.setLoadingIndicator(new Label(SimpleProjectMessages.IMPL.emptyTableIndicator()));

        final NoSelectionModel<User> smodel = new NoSelectionModel<User>();
        table.setSelectionModel(smodel);
        smodel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                delegate.onUserSelected(smodel.getLastSelectedObject());
            }
        });
    }

    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    public void setInfoText(String text) {
        info.setText(text);
    }



    public void setUsers(List<User> users) {
        table.setRowData(users);
    }

    public void setUserAmountLabel(String text) {
        this.userAmountLabel.setText(text);
    }

    public void setEditButtonEnabled(boolean enabled) {
        editButton.setEnabled(enabled);
    }

    public void setDeleteButtonEnabled(boolean enabled) {
        deleteButton.setEnabled(enabled);
    }

    @UiHandler("addButton")
    public void onAddButtonClicked(ClickEvent event) {
        delegate.onAddButtonClicked();
    }

    @UiHandler("editButton")
    public void onEditButtonClicked(ClickEvent event) {
        delegate.onEditButtonClicked();
    }

    @UiHandler("deleteButton")
    public void onDeleteButtonClicked(ClickEvent event) {
        delegate.onDeleteButtonClicked();
    }

    @UiHandler("englishButton")
    public void onEnglishButtonClicked(ClickEvent event) {
        delegate.onEnglishButtonClicked();
    }

    @UiHandler("russianButton")
    public void onRussianButtonClicked(ClickEvent event) {
        delegate.onRussianButtonClicked();
    }
}