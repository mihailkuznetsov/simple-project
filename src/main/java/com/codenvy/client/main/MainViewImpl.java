package com.codenvy.client.main;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.model.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;


public class MainViewImpl extends Composite implements MainView {

    @Singleton
    interface MainViewImplUiBinder extends UiBinder<DockLayoutPanel, MainViewImpl> {
    }

    private final SimpleProjectMessages messages;

    private ActionDelegate delegate;

    @UiField
    Button addButton;

    @UiField
    Button editButton;

    @UiField
    Button deleteButton;

    @UiField
    Button statusButton;

    @UiField(provided = true)
    CellTable<User> table;

    @UiField
    Button englishButton;

    @UiField
    Button russianButton;

    @UiField
    Label description;

    @UiField
    Label userAmountLabel;

    @Inject
    public MainViewImpl(MainViewImplUiBinder ourUiBinder, SimpleProjectMessages messages) {
        this.messages = messages;
        initCellTable();
        initWidget(ourUiBinder.createAndBindUi(this));
        userAmountLabel.setText(messages.userAmount(0));
    }

    private void initCellTable() {
        table = new CellTable<>();

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getFirstName();
            }
        }, messages.firstName());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getLastName();
            }
        }, messages.lastName());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getAge();
            }
        }, messages.age());

        table.addColumn(new TextColumn<User>() {
            @Override
            public String getValue(User user) {
                return user.getAddress();
            }
        }, messages.address());

        table.setLoadingIndicator(new Label(messages.emptyTableIndicator()));

        final NoSelectionModel<User> smodel = new NoSelectionModel<User>();
        table.setSelectionModel(smodel);
        smodel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
                delegate.onUserSelected(smodel.getLastSelectedObject());
            }
        });
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void setDecriptionText(String text) {
        description.setText(text);
    }

    @Override
    public void setUsers(List<User> users) {
        table.setRowData(users);
    }

    @Override
    public void setUserAmountLabel(String text) {
        this.userAmountLabel.setText(text);
    }

    @Override
    public void setEditButtonEnabled(boolean enabled) {
        editButton.setEnabled(enabled);
    }

    @Override
    public void setDeleteButtonEnabled(boolean enabled) {
        deleteButton.setEnabled(enabled);
    }

    @Override
    public void setStatusButtonEnabled(boolean enabled) {
        statusButton.setEnabled(enabled);
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

    @UiHandler("statusButton")
    public void onStatusButtonClicked(ClickEvent event) {
        delegate.onStatusButtonClicked();
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