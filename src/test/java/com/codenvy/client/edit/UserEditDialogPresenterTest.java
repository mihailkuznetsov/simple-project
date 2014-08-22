package com.codenvy.client.edit;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.main.MainPresenter;
import com.codenvy.client.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserEditDialogPresenterTest {

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
        presenter.showDialog(null, callBack);

        verify(view).setDialogTitle(messages.addDialogTitle());
        verify(view).setFirstName("");
        verify(view).setLastName("");
        verify(view).setAge("");
        verify(view).setAddress("");

        verify(view).setFocusOnFirstName();
        verify(view).showDialog();
    }

    @Test
    public void testShowEditDialog() {
        when(user.getFirstName()).thenReturn("1");
        when(user.getLastName()).thenReturn("2");
        when(user.getAge()).thenReturn("3");
        when(user.getAddress()).thenReturn("4");

        presenter.showDialog(user, callBack);

        verify(view).setDialogTitle(messages.addDialogTitle());
        verify(view).setFirstName("1");
        verify(view).setLastName("2");
        verify(view).setAge("3");
        verify(view).setAddress("4");

        verify(view).setFocusOnFirstName();
        verify(view).showDialog();
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

        verify(callBack).onUserChanged((User) anyObject());

        verify(view).closeDialogBox();
    }

    @Test
    public void testOkClickedWhenUserHasEmptyFields() {
        when(view.getFirstName()).thenReturn("1");
        when(view.getLastName()).thenReturn("");
        when(view.getAge()).thenReturn("3");
        when(view.getAddress()).thenReturn("");

        presenter.showDialog(user, callBack);
        presenter.onOkButtonClicked();

        verify(view).getFirstName();
        verify(view).getLastName();
        verify(view).getAge();
        verify(view).getAddress();

        verify(view, never()).closeDialogBox();
    }

    @Test
    public void testOnCancelClicked() {
        presenter.onCancelButtonClicked();

        verify(view).closeDialogBox();
    }
}
