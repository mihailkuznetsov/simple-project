package com.codenvy.client.patcher;

import com.googlecode.gwt.test.patchers.PatchClass;
import com.googlecode.gwt.test.patchers.PatchMethod;

@PatchClass(com.google.gwt.user.client.Window.class)
public class WindowPatcher {

    @PatchMethod(override = true)
    public static void alert(String msg) {
    }
}
