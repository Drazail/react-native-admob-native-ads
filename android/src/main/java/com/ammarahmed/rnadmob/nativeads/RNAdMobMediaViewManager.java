package com.ammarahmed.rnadmob.nativeads;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

import org.jetbrains.annotations.NotNull;

public class RNAdMobMediaViewManager extends ViewGroupManager<RNMediaView> {
    private static final String REACT_CLASS = "MediaView";


    @Override
    public @NotNull String getName() {
        return REACT_CLASS;
    }

    @Override
    protected @NotNull RNMediaView createViewInstance(@NotNull ThemedReactContext reactContext) {

        return new RNMediaView(reactContext);
    }

}

