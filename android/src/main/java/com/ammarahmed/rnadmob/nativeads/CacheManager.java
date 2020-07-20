package com.ammarahmed.rnadmob.nativeads;

import android.content.Context;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CacheManager {

    private final ArrayList<UnifiedNativeAd> nativeAds = new ArrayList<>();

    Map< String, ArrayList<UnifiedNativeAd>> nativeAdsMap = new HashMap<>();

    private AdLoader adLoader;
    AdListener adListener;

    public boolean isLoading() {

        if (adLoader != null) {
            return adLoader.isLoading();
        } else {
            return false;
        }
    }

    public void printAds() {
        System.out.println("younes printing ");
        Set< Map.Entry< String,ArrayList<UnifiedNativeAd> > > st = nativeAdsMap.entrySet();
        System.out.println("younes printing set size: " + st.size());
        for (Map.Entry< String,ArrayList<UnifiedNativeAd>> me:st) {
           System.out.print("loaded ads: " + me.getKey() + ":");
           System.out.println(me.getValue().size());
        }
    }
    public int numberOfAds(String id) {
        if (nativeAdsMap.containsKey(id)){
            return nativeAdsMap.get(id).size();
        }else{
            return 0;
        }

    }

    public void attachAdListener(AdListener listener) {
       adListener = listener;
    }

    private final AdListener adListen = new AdListener() {
        @Override
        public void onAdFailedToLoad(int i) {
            super.onAdFailedToLoad(i);
            if (adListener == null) return;
          adListener.onAdFailedToLoad(i);
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            if (adListener == null) return;
            adListener.onAdClosed();
        }

        @Override
        public void onAdOpened() {
            super.onAdOpened();
            if (adListener == null) return;
            adListener.onAdOpened();
        }

        @Override
        public void onAdClicked() {
            super.onAdClicked();
            if (adListener == null) return;
           adListener.onAdClicked();

        }

        @Override
        public void onAdLoaded() {
            super.onAdLoaded();

            if (adListener == null) return;
            if (nativeAds.size() == 1) {
                adListener.onAdLoaded();
            }
        }

        @Override
        public void onAdImpression() {
            super.onAdImpression();
            if (adListener == null) return;
           adListener.onAdImpression();
        }

        @Override
        public void onAdLeftApplication() {
            super.onAdLeftApplication();
            if (adListener == null) return;
            adListener.onAdLeftApplication();
        }
    };



    public void loadNativeAds(Context context, String adUnitID, int numOfAdsToLoad, int requestInterval) {

        //         adUnitIDs = adUnitID;
        //     private String adUnitIDs;
        try {
            AdLoader.Builder builder = new AdLoader.Builder(context, adUnitID);
            builder.forUnifiedNativeAd(new onUnifiedNativeAdLoadedListener(adUnitID, nativeAdsMap, context));
            VideoOptions videoOptions = new VideoOptions.Builder()
                    .setStartMuted(true)
                    .build();

            NativeAdOptions adOptions = new NativeAdOptions.Builder()
                    .setVideoOptions(videoOptions)
                    .setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                    .build();
            builder.withNativeAdOptions(adOptions);

            adLoader = builder.withAdListener(adListen).build();

            adLoader.loadAds(new AdRequest.Builder().build(),numOfAdsToLoad);

            long previousAdRequestTime = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public UnifiedNativeAd getNativeAd(String id) {
//         if ((System.currentTimeMillis() - previousAdRequestTime) > newAdRequestInterval) {
//             loadNativeAds(mContext,adUnitIDs,numAdRequested, (int) newAdRequestInterval);
//             return null;
//         }

        if (nativeAdsMap.containsKey(id) && nativeAdsMap.get(id).size() != 0) {
            Random random = new Random();
            int randomNumber = random.nextInt(nativeAdsMap.get(id).size());
            //             nativeAdsMap.get(id).remove(randomNumber);
//             WritableMap args = Arguments.createMap();
//             args.putInt(id, nativeAdsMap.get(id).size());
//             EventEmitter.sendEvent((ReactContext) mContext, Constants.EVENT_AD_PRELOAD_DELETED, args);
            return nativeAdsMap.get(id).get(randomNumber);
        } else {
            return  null;
        }
    }

    public WritableMap hasLoadedAd(String id) {

        if (nativeAdsMap.containsKey(id) && nativeAdsMap.get(id).size() != 0) {
            WritableMap args = Arguments.createMap();
            args.putBoolean(id, true);
            return args;
        } else {
            WritableMap args = Arguments.createMap();
            args.putBoolean(id, false);
            return args;
        }
    }

}

