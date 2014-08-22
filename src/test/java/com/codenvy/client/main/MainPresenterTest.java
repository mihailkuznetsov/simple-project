package com.codenvy.client.main;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.edit.UserEditDialogPresenter;
import com.codenvy.client.events.ChangeToEnglishEvent;
import com.codenvy.client.events.ChangeToRussianEvent;
import com.codenvy.client.model.User;
import com.google.gwt.event.shared.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.*;

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

    @Mock
    User user;

    @InjectMocks
    MainPresenter presenter;

    @Test
    public void testAddButtonClicked() {
        presenter.onAddButtonClicked();

        verify(dialogPresenter).showDialog((User) isNull(), (MainPresenter.CallBack) anyObject());
    }

    @Test
    public void testEditButtonClicked() {
        presenter.onUserSelected(user);
        presenter.onEditButtonClicked();

        verify(view).setEditButtonEnabled(true);
        verify(view).setDeleteButtonEnabled(true);

        verify(dialogPresenter).showDialog((User) anyObject(), (MainPresenter.CallBack) anyObject());
    }

    @Test
    public void testDeleteUser() {
        presenter.onUserSelected(user);
        presenter.onDeleteButtonClicked();

        verify(view).setEditButtonEnabled(true);
        verify(view).setDeleteButtonEnabled(true);

        verify(view).setUsers(anyListOf(User.class));
        verify(view).setUserAmountLabel(messages.userAmount(anyInt()));

        verify(view).setEditButtonEnabled(false);
        verify(view).setDeleteButtonEnabled(false);
    }

    @Test
    public void testEnglishButtonClicked() {
        presenter.onEnglishButtonClicked();

        verify(eventBus).fireEvent(isA(ChangeToEnglishEvent.class));
    }

    @Test
    public void testRussianButtonClicked() {
        presenter.onRussianButtonClicked();

        verify(eventBus).fireEvent(isA(ChangeToRussianEvent.class));
    }

    @Test
    public void testAddCallBack() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                MainPresenter.CallBack callBack = (MainPresenter.CallBack) invocationOnMock.getArguments()[1];
                callBack.onUserChanged((User) anyObject());
                return null;
            }
        }).when(dialogPresenter).showDialog((User) isNull(), (MainPresenter.CallBack) anyObject());

        presenter.onAddButtonClicked();

        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        verify(view).setUsers(argument.capture());
        assertTrue(argument.getValue().contains(user));
        verify(view).setUserAmountLabel(messages.userAmount(argument.getValue().size()));

        verify(view).setEditButtonEnabled(false);
        verify(view).setDeleteButtonEnabled(false);
    }

    @Test
    public void testEditCallBack() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                MainPresenter.CallBack callBack = (MainPresenter.CallBack) invocationOnMock.getArguments()[1];
                callBack.onUserChanged(user);
                return null;
            }
        }).when(dialogPresenter).showDialog((User) isNull(), (MainPresenter.CallBack) anyObject());

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                MainPresenter.CallBack callBack = (MainPresenter.CallBack) invocationOnMock.getArguments()[1];
                callBack.onUserChanged(user);
                return null;
            }
        }).when(dialogPresenter).showDialog((User) anyObject(), (MainPresenter.CallBack) anyObject());

        presenter.onAddButtonClicked();
        presenter.onUserSelected(user);
        presenter.onEditButtonClicked();

        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        verify(view, times(2)).setUsers(argument.capture());
        assertTrue(argument.getValue().contains(user));

        verify(view, times(2)).setEditButtonEnabled(false);
        verify(view, times(2)).setDeleteButtonEnabled(false);
    }
}
