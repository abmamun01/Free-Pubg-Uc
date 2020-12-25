package com.mamunsproject.awesome_free_pubg_uc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.mamunsproject.awesome_free_pubg_uc.MainActivity;
import com.mamunsproject.awesome_free_pubg_uc.R;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

public class RoyalPass extends AppCompatActivity {

    private String GameId = "3948525";
    private String interstitialAdsId = "interstiatialForDiamondTopUp";
    private String rewardedVideoAdsId="rewardedVideo";
    private String videoAds="video";

    private boolean testMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_royal_pass);


        UnityAds.initialize(RoyalPass.this, GameId, testMode);

        //=========================================================================Interstitial Ads =============================================================================



        //=========================================================================Interstitial Ads =============================================================================

    }


    @Override
    public void onBackPressed() {
        if (UnityAds.isReady(videoAds)) {
            UnityAds.show(RoyalPass.this, videoAds);

        }else{
            super.onBackPressed();
        }
    }


}