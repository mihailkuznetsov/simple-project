package com.codenvy.client.edit;

import com.codenvy.client.SimpleProjectMessages;
import com.codenvy.client.main.MainPresenter;
import com.codenvy.client.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class AddUserWithEmptyFieldsTest {

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

    @Parameterized.Parameter
    public String firstName;

    @Parameterized.Parameter(value = 1)
    public String lastName;

    @Parameterized.Parameter(value = 2)
    public String age;

    @Parameterized.Parameter(value = 3)
    public String address;


    @Parameterized.Parameters
    public static List<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {"", "", "", ""},
                {"", "", "", "filled"},
                {"", "", "filled", ""},
                {"", "", "filled", "filled"},

                {"", "filled", "", ""},
                {"", "filled", "", "filled"},
                {"", "filled", "filled", ""},
                {"", "filled", "filled", "filled"},

                {"filled", "", "", ""},
                {"filled", "", "", "filled"},
                {"filled", "", "filled", ""},
                {"filled", "", "filled", "filled"},

                {"filled", "filled", "", ""},
                {"filled", "filled", "", "filled"},
                {"filled", "filled", "filled", ""},
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOkClickedWhenUserHasEmptyFields() {
        when(view.getFirstName()).thenReturn(firstName);
        when(view.getLastName()).thenReturn(lastName);
        when(view.getAge()).thenReturn(age);
        when(view.getAddress()).thenReturn(address);

        presenter.showDialog(user, callBack);
        presenter.onOkButtonClicked();

        verify(view).getFirstName();
        verify(view).getLastName();
        verify(view).getAge();
        verify(view).getAddress();

        verify(callBack, never()).onUserChanged((User) anyObject());
        verify(view, never()).closeDialogBox();

    }
}
