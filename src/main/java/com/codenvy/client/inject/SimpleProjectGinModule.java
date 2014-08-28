/*
 * Created by IntelliJ IDEA.
 * User: michail
 * Date: 19.08.14
 * Time: 16:05
 */
package com.codenvy.client.inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class SimpleProjectGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
    }
}