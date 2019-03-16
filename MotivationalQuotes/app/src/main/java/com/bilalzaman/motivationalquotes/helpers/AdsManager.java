package com.bilalzaman.motivationalquotes.helpers;

/**
 * Created by BilalZaman on 20/12/2018.
 */
public class AdsManager {

//    private static final String TAG = "AdsManager";
//    public static AdsManager adsManager;
//    com.facebook.ads.InterstitialAd fbInterstitialAd;
//    private InterstitialAd interstitial;
//    private Context context;
//
//    private AdsManager(Context context) {
//        this.context = context;
//        MobileAds.initialize(context);
//        MobileAds.setAppVolume(0.01f);
//        interstitial = new InterstitialAd(context);
//        this.fbInterstitialAd = new com.facebook.ads.InterstitialAd(context, "YOUR_PLACEMENT_ID"/*context.getString(R.string.interstitial_facebook)*/);
//        interstitial.setAdUnitId(Constants.applicationID);
//
//        loadInterstitial();
//        loadFacebookInterstitialAd();
//    }
//
//
//    public static AdsManager getInstance(Context context) {
//        if (adsManager == null) {
//            adsManager = new AdsManager(context);
//        }
//        return adsManager;
//    }
//
//    private AdRequest appendUserConsent() {
//        AdRequest adRequest = null;
//        adRequest = new AdRequest.Builder().addTestDevice("F589D1A8767A40F1941FCDA1B42AE725").build();
////        if (PreferenceHelper.getInstance().getBol(context.getString(R.string.npa), false)) {
////            Bundle bundle = new Bundle();
////            bundle.putString(context.getString(R.string.npa), "1");
////            Log.d(TAG, "consent status: npa");
////            adRequest = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, bundle).build();
////        } else {
////            Log.d(TAG, "consent status: pa");
////
////        }
//        return adRequest;
//    }
//
//    private void loadInterstitial() {
//        interstitial.loadAd(appendUserConsent());
//        interstitial.setAdListener(new AdListener() {
//            @Override
//            public void onAdFailedToLoad(int i) {
//                super.onAdFailedToLoad(i);
//                // if interstitial ad failed to load, then reload it after 5 seconds
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // after the ad has been shown, load interstitial again and cache it
//                        loadInterstitial();
//                    }
//                }, 10000);
//                Log.e("interstitialAd", "onInterstitialAdFailedToLoad " + String.valueOf(i));
//            }
//
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                Log.e("interstitialAd", "onInterstitialAdLoaded");
//            }
//
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//                // after the ad has been closed, load interstitial again and cache it
//                loadInterstitial();
//            }
//        });
//    }
//
//    public void showInterstitial() {
//        if (interstitial.isLoaded()) {
//            interstitial.show();
//        }
//    }
//
//    public void createAndShowBanner(final AdView adView) {
//        AdRequest adRequest = appendUserConsent();
//        adView.loadAd(adRequest);
//        adView.setAdListener(new AdListener() {
//
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                adView.setVisibility(View.VISIBLE);
//
//            }
//
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//            }
//
//            @Override
//            public void onAdFailedToLoad(int i) {
//                adView.setVisibility(View.GONE);
//                super.onAdFailedToLoad(i);
//            }
//        });
//    }
//
//    public void showFacbookBanner(Context context, final LinearLayout linearLayout) {
//        final com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, "YOUR_PLACEMENT_ID"/*context.getString(C0950R.string.banner_facebook)*/, AdSize.BANNER_HEIGHT_50);
//        if (linearLayout != null) {
//            adView.loadAd();
//            adView.setAdListener(new com.facebook.ads.AdListener() {
//                public void onAdClicked(Ad ad) {
//                }
//
//                public void onLoggingImpression(Ad ad) {
//                }
//
//                public void onError(Ad ad, AdError adError) {
//
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append("Facebook BannerAd -> onError: ");
//                    stringBuilder.append(adError.getErrorMessage());
//                    Log.d(TAG, stringBuilder.toString());
//                }
//
//                public void onAdLoaded(Ad ad) {
//                    Log.d(TAG, "Facebook BannerAd -> onAdLoaded");
//                    linearLayout.removeAllViews();
//                    linearLayout.addView(adView);
//                }
//            });
//        }
//    }
//
//    public void showExitFacbookBanner(Context context, final LinearLayout linearLayout) {
//        final com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, "YOUR_PLACEMENT_ID"/*context.getString(C0950R.string.banner_facebook)*/, AdSize.RECTANGLE_HEIGHT_250);
//        if (linearLayout != null) {
//            adView.loadAd();
//            adView.setAdListener(new com.facebook.ads.AdListener() {
//                public void onAdClicked(Ad ad) {
//                }
//
//                public void onLoggingImpression(Ad ad) {
//                }
//
//                public void onError(Ad ad, AdError adError) {
//
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append("Facebook BannerAd -> onError: ");
//                    stringBuilder.append(adError.getErrorMessage());
//                    Log.d(TAG, stringBuilder.toString());
//                }
//
//                public void onAdLoaded(Ad ad) {
//                    Log.d(TAG, "Facebook BannerAd -> onAdLoaded");
//                    linearLayout.removeAllViews();
//                    linearLayout.addView(adView);
//                }
//            });
//        }
//    }
//
//    private void loadFacebookInterstitialAd() {
//
//        this.fbInterstitialAd.setAdListener(new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//                // Interstitial ad displayed callback
//                Log.e(TAG, "Interstitial ad displayed.");
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//                // Interstitial dismissed callback
//                Log.e(TAG, "Interstitial ad dismissed.");
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                // Ad error callback
//                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Interstitial ad is loaded and ready to be displayed
//                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
//                // Show the ad
////                    interstitialAd.show();
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Ad clicked callback
//                Log.d(TAG, "Interstitial ad clicked!");
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Ad impression logged callback
//                Log.d(TAG, "Interstitial ad impression logged!");
//            }
//        });
//        this.fbInterstitialAd.loadAd();
//
//    }
//
//    public void showFacebookInterstitialAd() {
//        if (fbInterstitialAd.isAdLoaded()) {
//            fbInterstitialAd.show();
//        } else {
//            loadFacebookInterstitialAd();
//        }
//    }

}
