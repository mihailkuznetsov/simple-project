package com.codenvy.client.status;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserStatusPresenterTest {
    private static final String FULL_INFO = "FULL_INFO";
    private static final String USER_STATUS_TEXT = "USER_STATUS_TEXT";

    @Mock
    SimpleProjectMessages messages;

    @Mock
    UserStatusView view;

    @Mock
    User user;

    @InjectMocks
    UserStatusPresenter presenter;

    @Test
    public void testOkButtonClicked() {
        when(view.getUserStatusText()).thenReturn(USER_STATUS_TEXT);
        presenter.showDialog(user);
        presenter.onOkButtonClicked();

        verify(user).setStatus(eq(USER_STATUS_TEXT));
        verify(view).getUserStatusText();
        verify(view).closeDialog();
    }

    @Test
    public void testCancelButtonClicked() {
        presenter.onCancelButtonClicked();

        view.closeDialog();
    }

    @Test
    public void testShowDialog() {
        when(user.getFullInfo()).thenReturn(FULL_INFO);

        presenter.showDialog(user);

        verify(view).setUserInfo(eq(FULL_INFO));
        verify(user).getFullInfo();

        verify(view).setUserStatusText(user.getStatus());
        verify(view).showDialog();
    }
}
