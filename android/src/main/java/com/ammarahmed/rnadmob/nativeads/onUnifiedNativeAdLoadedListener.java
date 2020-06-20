package com.ammarahmed.rnadmob.nativeads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;


public class onUnifiedNativeAdLoadedListener implements UnifiedNativeAd.OnUnifiedNativeAdLoadedListener {
    String adUnitId;
    ArrayList<UnifiedNativeAd> nativeAds;

    public onUnifiedNativeAdLoadedListener(String adUnitId, ArrayList<UnifiedNativeAd> nativeAds){
        this.adUnitId = adUnitId;
        this.nativeAds = nativeAds;
    }
    @Override
    public void onUnifiedNativeAdLoaded(UnifiedNativeAd nativeAd) {
        this.nativeAds.add(nativeAd);
    }
}
