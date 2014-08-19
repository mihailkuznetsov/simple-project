package com.codenvy.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface SimpleProjectMessages extends Messages {
    SimpleProjectMessages IMPL = GWT.create(SimpleProjectMessages.class);

    @DefaultMessage("There are {0} users in a list")
    @AlternateMessage({"one", "There is {0} user in a list", "=0", "There are no users in a list"})
    String userCount(@PluralCount int userCount);

}
