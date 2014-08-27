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
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private static final String USER_AMOUNT = "USER_AMOUNT";

    @Captor
    private ArgumentCaptor<List<User>> listCaptor;

    @Mock
    private MainView view;

    @Mock
    private UserEditDialogPresenter dialogPresenter;

    @Mock
    private EventBus eventBus;

    @Mock
    private SimpleProjectMessages messages;

    @Mock
    private User user;

    @Mock
    private MainPresenter.CallBack callBack;

    @InjectMocks
    private MainPresenter presenter;


    @Test
    public void testAddButtonClickedWithCallBack() {
        when(messages.userAmount(anyInt())).thenReturn(USER_AMOUNT);
        initAddCallBack();

        presenter.onAddButtonClicked();

        verify(dialogPresenter).showDialog(eq((User) null), isA(MainPresenter.CallBack.class));

        verify(view).setUsers(listCaptor.capture());
        assertTrue(listCaptor.getValue().contains(user));

        ArgumentCaptor<Integer> afterListSize = ArgumentCaptor.forClass(Integer.class);
        verify(messages).userAmount(afterListSize.capture());
        assertTrue(afterListSize.getValue().equals(listCaptor.getValue().size()));
        verify(view).setUserAmountLabel(eq(USER_AMOUNT));

        verify(view).setEditButtonEnabled(false);
        verify(view).setDeleteButtonEnabled(false);
    }

    @Test
    public void testEditButtonClickedWithCallBack() {
        initAddCallBack();
        presenter.onAddButtonClicked();
        presenter.onUserSelected(user);

        reset(view, dialogPresenter);
        initEditCallBack();

        presenter.onEditButtonClicked();

        verify(dialogPresenter).showDialog(isA(User.class), isA(MainPresenter.CallBack.class));

        verify(view).setUsers(listCaptor.capture());
        assertTrue(listCaptor.getValue().contains(user));

        verify(view).setEditButtonEnabled(false);
        verify(view).setDeleteButtonEnabled(false);
    }

    @Test
    public void testDeleteButtonClicked() {
        initAddCallBack();
        presenter.onAddButtonClicked();
        presenter.onUserSelected(user);

        verify(view).setUsers(listCaptor.capture());
        assertTrue(listCaptor.getValue().contains(user));

        reset(view, dialogPresenter, messages);
        when(messages.userAmount(anyInt())).thenReturn(USER_AMOUNT);

        presenter.onDeleteButtonClicked();

        verify(view).setUsers(listCaptor.capture());
        List<User> resultList = listCaptor.getValue();

        assertFalse(resultList.contains(user));

        ArgumentCaptor<Integer> afterListSize = ArgumentCaptor.forClass(Integer.class);
        verify(messages).userAmount(afterListSize.capture());
        assertTrue(afterListSize.getValue().equals(resultList.size()));
        verify(view).setUserAmountLabel(eq(USER_AMOUNT));

        verify(view).setEditButtonEnabled(false);
        verify(view).setDeleteButtonEnabled(false);
    }

    @Test
    public void testUserSelected() {
        presenter.onUserSelected(user);

        verify(view).setEditButtonEnabled(true);
        verify(view).setDeleteButtonEnabled(true);
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

    private void initAddCallBack() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                MainPresenter.CallBack callBack = (MainPresenter.CallBack) invocationOnMock.getArguments()[1];
                callBack.onUserChanged(user);
                return null;
            }
        }).when(dialogPresenter).showDialog(eq((User) null), isA(MainPresenter.CallBack.class));
    }

    private void initEditCallBack() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                MainPresenter.CallBack callBack = (MainPresenter.CallBack) invocationOnMock.getArguments()[1];
                callBack.onUserChanged(user);
                return null;
            }
        }).when(dialogPresenter).showDialog(isA(User.class), isA(MainPresenter.CallBack.class));
    }
}
