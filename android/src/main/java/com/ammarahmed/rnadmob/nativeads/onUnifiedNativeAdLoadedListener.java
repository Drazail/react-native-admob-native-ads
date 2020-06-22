package com.ammarahmed.rnadmob.nativeads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReactContext;
import android.content.Context;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class onUnifiedNativeAdLoadedListener implements UnifiedNativeAd.OnUnifiedNativeAdLoadedListener {
    String adUnitId;
    Map< String, ArrayList<UnifiedNativeAd> > nativeAds;
    Context mContext;

//     private void sendEvent(String eventName, @Nullable WritableMap params) {
//       ReactContext reactContext = (ReactContext) mContext;
//       reactContext
//           .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
//           .emit(eventName, params);
//     }

    public onUnifiedNativeAdLoadedListener(String adUnitId, Map< String, ArrayList<UnifiedNativeAd> > nativeAds, Context context){
        this.adUnitId = adUnitId;
        this.nativeAds = nativeAds;
        this.mContext = context;
    }

    @Override
    public void onUnifiedNativeAdLoaded(UnifiedNativeAd nativeAd) {
        ArrayList<UnifiedNativeAd> temp;
        if (this.nativeAds.containsKey(this.adUnitId)){
            temp = this.nativeAds.get(this.adUnitId);
        }else{
            temp = new ArrayList<>();
        }
        temp.add(nativeAd);
        WritableMap args = Arguments.createMap();
        args.putInt(this.adUnitId, temp.size());
        EventEmitter.sendEvent((ReactContext) this.mContext, "testEventFuckAmin", args);
        this.nativeAds.put(this.adUnitId, temp);
    }
}
