package com.codenvy.client.main;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.edit.UserEditDialogPresenter;
import com.codenvy.client.events.ChangeToEnglishEvent;
import com.codenvy.client.events.ChangeToRussianEvent;
import com.codenvy.client.model.User;
import com.google.gwt.event.shared.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainView view;

    @Mock
    UserEditDialogPresenter dialogPresenter;


    @Mock
    EventBus eventBus;

    @Mock
    SimpleProjectMessages messages;

    MainPresenter presenter;

    @Mock
    MainPresenter.CallBack callBack;

    @Mock
    ChangeToEnglishEvent changeToEnglishEvent;

    @Mock
    ChangeToRussianEvent changeToRussianEvent;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(MainPresenterTest.class);
        presenter = new MainPresenter(view, dialogPresenter, eventBus, messages);
    }

    @Test
    public void onAddButtonClicked() {
        presenter.onAddButtonClicked();

        verify(dialogPresenter).showDialog((User) isNull(), (MainPresenter.CallBack) anyObject());
    }

    @Test
    public void onEditButtonClicked() {
        presenter.onEditButtonClicked();

        verify(dialogPresenter).showDialog((User) anyObject(), (MainPresenter.CallBack) anyObject());
    }

    @Test
    public void onDeleteButtonClicked() {
        presenter.onDeleteButtonClicked();

        verify(view).setUsers(anyListOf(User.class));
        verify(view).setUserAmountLabel(messages.userAmount(anyInt()));
        verify(view).setEditButtonEnabled(false);
        verify(view).setDeleteButtonEnabled(false);
    }

    @Test
    public void onEnglishButtonClicked() {
        presenter.onEnglishButtonClicked();

        verify(eventBus).fireEvent(isA(ChangeToEnglishEvent.class));
    }

    @Test
    public void onRussianButtonClicked() {
        presenter.onRussianButtonClicked();

        verify(eventBus).fireEvent(isA(ChangeToRussianEvent.class));
    }

    @Test
    public void onUserSelected() {
        presenter.onUserSelected((User) anyObject());

        verify(view).setEditButtonEnabled(true);
        verify(view).setDeleteButtonEnabled(true);
    }
}
