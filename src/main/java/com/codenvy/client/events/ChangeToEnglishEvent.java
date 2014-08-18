package com.codenvy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ChangeToEnglishEvent extends GwtEvent<ChangeToEnglishEventHandler> {
    public static Type<ChangeToEnglishEventHandler> TYPE = new Type<ChangeToEnglishEventHandler>();

    @Override
    public Type<ChangeToEnglishEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeToEnglishEventHandler handler) {
        handler.onChangeToEnglish(this);
    }
}
