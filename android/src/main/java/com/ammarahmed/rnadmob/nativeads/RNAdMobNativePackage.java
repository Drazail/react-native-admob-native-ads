package com.ammarahmed.rnadmob.nativeads;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RNAdMobNativePackage implements ReactPackage {

    @Override
    public @NotNull List<NativeModule> createNativeModules(@NotNull ReactApplicationContext reactContext) {
        return Collections.singletonList(new RNAdmobNativeAdsManager(reactContext));
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public @NotNull List<ViewManager> createViewManagers(@NotNull ReactApplicationContext reactContext) {

        return Arrays.asList(
                new RNAdMobNativeViewManager(),
                new RNAdMobMediaViewManager(),
                new RNAdComponentsWrapperManager(),
                new RNAdmobAdChoicesManager()
        );
    }
}
