package com.codenvy.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ChangeToRussianEvent extends GwtEvent<ChangeToRussianEventHandler> {
    public static Type<ChangeToRussianEventHandler> TYPE = new Type<ChangeToRussianEventHandler>();

    @Override
    public Type<ChangeToRussianEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChangeToRussianEventHandler handler) {
        handler.onChangeToRussian(this);
    }
}
