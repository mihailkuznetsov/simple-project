package com.codenvy.client.edit;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.main.MainPresenter;
import com.codenvy.client.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserEditDialogPresenterTest {
    private static final String ADD_DIALOG_TITLE = "ADD_TITLE";
    private static final String EDIT_DIALOG_TITLE = "EDIT_TITLE";

    @Mock
    private UserEditDialogView view;

    @Mock
    private SimpleProjectMessages messages;

    @Mock
    private User user;

    @Mock
    private MainPresenter.CallBack callBack;

    @InjectMocks
    private UserEditDialogPresenter presenter;

    @Test
    public void testShowAddDialog() {
        when(messages.addDialogTitle()).thenReturn(ADD_DIALOG_TITLE);

        presenter.showDialog(null, callBack);

        verify(view).setDialogTitle(eq(ADD_DIALOG_TITLE));
        verify(messages).addDialogTitle();

        verify(view).setFirstName(eq(""));
        verify(view).setLastName(eq(""));
        verify(view).setAge(eq(""));
        verify(view).setAddress(eq(""));

        verify(view).setFocusOnFirstName();
        verify(view).showDialog();
    }

    @Test
    public void testShowEditDialog() {
        when(messages.editDialogTitle()).thenReturn(EDIT_DIALOG_TITLE);
        when(user.getFirstName()).thenReturn("1");
        when(user.getLastName()).thenReturn("2");
        when(user.getAge()).thenReturn("3");
        when(user.getAddress()).thenReturn("4");

        presenter.showDialog(user, callBack);

        verify(view).setDialogTitle(eq(EDIT_DIALOG_TITLE));
        verify(messages).editDialogTitle();

        verify(view).setFirstName(eq("1"));
        verify(view).setLastName(eq("2"));
        verify(view).setAge(eq("3"));
        verify(view).setAddress(eq("4"));

        verify(view).showDialog();
        verify(view).setFocusOnFirstName();
    }

    @Test
    public void testOkClickedWhenUserHasNotEmptyFields() {
        when(view.getFirstName()).thenReturn("1");
        when(view.getLastName()).thenReturn("2");
        when(view.getAge()).thenReturn("3");
        when(view.getAddress()).thenReturn("4");

        presenter.showDialog(user, callBack);
        presenter.onOkButtonClicked();

        verify(view).getFirstName();
        verify(view).getLastName();
        verify(view).getAge();
        verify(view).getAddress();

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(callBack).onUserChanged(argument.capture());

        User newUser = argument.getValue();
        assertTrue(newUser.getFirstName().equals("1"));
        assertTrue(newUser.getLastName().equals("2"));
        assertTrue(newUser.getAge().equals("3"));
        assertTrue(newUser.getAddress().equals("4"));

        verify(view).closeDialogBox();
    }

    @Test
    public void testOnCancelClicked() {
        presenter.onCancelButtonClicked();

        verify(view).closeDialogBox();
    }
}
