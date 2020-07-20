package com.ammarahmed.rnadmob.nativeads;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.jetbrains.annotations.NotNull;

public class RNAdmobNativeAdsManager extends ReactContextBaseJavaModule {

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    public static final String HMAC_SHA_256 = "HmacSHA256";
    private static final String KEY_ALGORITHM = "AES";

    public RNAdmobNativeAdsManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public @NotNull String getName() {
        return "RNAdmobNativeAdsManager";
    }

    @ReactMethod
    public void loadAds(String adUnitID, int numOfAds, int requestInterval) {

        Constants.cacheManager.loadNativeAds(getReactApplicationContext(),adUnitID,numOfAds,requestInterval);

    }

    @ReactMethod
    public void getNumOfLoadedAds(String id, Promise promise) {

        promise.resolve(Constants.cacheManager.numberOfAds(id));

    }

    @ReactMethod
    public void isLoadingAds(Promise promise) {

        promise.resolve(Constants.cacheManager.isLoading());

    }

    @ReactMethod
    public void printLoadedAds() {

        Constants.cacheManager.printAds();

    }

    @ReactMethod
    public void hasLoadedAd(String id, Promise promise) {

        promise.resolve(Constants.cacheManager.hasLoadedAd(id));

    }
}
