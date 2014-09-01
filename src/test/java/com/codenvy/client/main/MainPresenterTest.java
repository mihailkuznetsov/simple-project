package com.codenvy.client.main;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.edit.UserEditPresenter;
import com.codenvy.client.events.ChangeToEnglishEvent;
import com.codenvy.client.events.ChangeToRussianEvent;
import com.codenvy.client.model.User;
import com.codenvy.client.status.UserStatusPresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;
import com.googlecode.gwt.test.gin.GInjectorCreateHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@GwtModule("com.codenvy.SimpleProject")
public class MainPresenterTest extends GwtTestWithMockito {

    private static final String USER_AMOUNT = "USER_AMOUNT";

    @Captor
    private ArgumentCaptor<List<User>> listCaptor;

    @Mock
    private MainView view;

    @Mock
    private UserEditPresenter userEditPresenter;

    @Mock
    private UserStatusPresenter userStatusPresenter;
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

    @Before
    public void setUp() {
        addGwtCreateHandler(new GInjectorCreateHandler());
    }

    @Test
    public void testAddButtonClickedWithCallBack() {
        when(messages.userAmount(anyInt())).thenReturn(USER_AMOUNT);
        initAddCallBack();

        presenter.onAddButtonClicked();

        verify(userEditPresenter).showDialog(eq((User) null), isA(MainPresenter.CallBack.class));

        verify(view).setUsers(listCaptor.capture());
        assertTrue(listCaptor.getValue().contains(user));

        ArgumentCaptor<Integer> afterListSize = ArgumentCaptor.forClass(Integer.class);
        verify(messages).userAmount(afterListSize.capture());
        assertTrue(afterListSize.getValue().equals(listCaptor.getValue().size()));
        verify(view).setUserAmountLabel(eq(USER_AMOUNT));

        verify(view).setEditButtonEnabled(false);
        verify(view).setDeleteButtonEnabled(false);
        verify(view).setStatusButtonEnabled(false);
    }

    @Test
    public void testEditButtonClickedWithCallBack() {
        initAddCallBack();
        presenter.onAddButtonClicked();
        presenter.onUserSelected(user);

        reset(view, userEditPresenter);
        initEditCallBack();

        presenter.onEditButtonClicked();

        verify(userEditPresenter).showDialog(isA(User.class), isA(MainPresenter.CallBack.class));

        verify(view).setUsers(listCaptor.capture());
        assertTrue(listCaptor.getValue().contains(user));

        verify(view).setEditButtonEnabled(false);
        verify(view).setDeleteButtonEnabled(false);
        verify(view).setStatusButtonEnabled(false);
    }

    @Test
    public void testDeleteButtonClicked() {
        initAddCallBack();
        presenter.onAddButtonClicked();
        presenter.onUserSelected(user);

        verify(view).setUsers(listCaptor.capture());
        assertTrue(listCaptor.getValue().contains(user));

        reset(view, userEditPresenter, messages);
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
        verify(view).setStatusButtonEnabled(false);
    }

    @Test
    public void testStatusButtonClicked() {
        presenter.onUserSelected(user);
        presenter.onStatusButtonClicked();

        verify(userStatusPresenter).showDialog(user);
    }

    @Test
    public void testUserSelected() {
        presenter.onUserSelected(user);

        verify(view).setEditButtonEnabled(true);
        verify(view).setDeleteButtonEnabled(true);
        verify(view).setStatusButtonEnabled(true);
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
    public void testGo() {
        Widget widget = mock(Widget.class);
        when(view.asWidget()).thenReturn(widget);
        HasWidgets panel = mock(HasWidgets.class);

        presenter.go(panel);

        verify(panel).clear();
        verify(panel).add(eq(widget));
        verify(view).asWidget();
    }

    private void initAddCallBack() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                MainPresenter.CallBack callBack = (MainPresenter.CallBack) invocationOnMock.getArguments()[1];
                callBack.onUserChanged(user);
                return null;
            }
        }).when(userEditPresenter).showDialog(eq((User) null), isA(MainPresenter.CallBack.class));
    }

    private void initEditCallBack() {
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                MainPresenter.CallBack callBack = (MainPresenter.CallBack) invocationOnMock.getArguments()[1];
                callBack.onUserChanged(user);
                return null;
            }
        }).when(userEditPresenter).showDialog(isA(User.class), isA(MainPresenter.CallBack.class));
    }
}
