package com.mamunsproject.awesome_free_pubg_uc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mamunsproject.awesome_free_pubg_uc.Fragment.Home_Fragment;
import com.mamunsproject.awesome_free_pubg_uc.Fragment.LeaderBoard_Fragment;
import com.mamunsproject.awesome_free_pubg_uc.Fragment.Wallet_Fragment;
import com.mamunsproject.awesome_free_pubg_uc.Fragment.Winner_Fragment;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
    SmoothBottomBar smoothBottomBar;

    private String GameId = "3948525";
    private String bannerAdsId = "Banner";
    private String interstitialAdsId = "interstiatialForDiamondTopUp";
    private boolean testMode = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen

        UnityAds.initialize(MainActivity.this, GameId, testMode);



        //=========================================================================Interstitial Ads =============================================================================

        IUnityAdsListener unityAdsListener = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {

                Toast.makeText(getApplicationContext(), "Interstial Ad Ready", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnityAdsStart(String s) {

                Toast.makeText(getApplicationContext(), "Interstial Start", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
                Toast.makeText(getApplicationContext(), "Interstial Finished", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {

                Toast.makeText(getApplicationContext(), "Interstial Error", Toast.LENGTH_SHORT).show();

            }
        };

        UnityAds.setListener(unityAdsListener);

        //=========================================================================Interstitial Ads =============================================================================



        IUnityBannerListener unityBannerListener = new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {

                ((ViewGroup)findViewById(R.id.bannerads)).removeView(view);
                ((ViewGroup)findViewById(R.id.bannerads)).addView(view);


            }

            @Override
            public void onUnityBannerUnloaded(String s) {

            }

            @Override
            public void onUnityBannerShow(String s) {

            }

            @Override
            public void onUnityBannerClick(String s) {

            }

            @Override
            public void onUnityBannerHide(String s) {

            }

            @Override
            public void onUnityBannerError(String s) {

            }
        };
        UnityBanners.setBannerListener(unityBannerListener);


  //=========================================================================Auto Display Ads  =============================================================================
        if (UnityAds.isInitialized()){
            UnityAds.load(interstitialAdsId);
            UnityBanners.loadBanner(MainActivity.this,bannerAdsId);

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    UnityBanners.loadBanner(MainActivity.this,bannerAdsId);
                    displayInterstitialAds();

                }
            },5000);
        }else {
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    UnityAds.load(interstitialAdsId);
                    UnityBanners.loadBanner(MainActivity.this,bannerAdsId);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            displayInterstitialAds();
                            UnityBanners.loadBanner(MainActivity.this,bannerAdsId);


                        }
                    },5000);

                }
            },5000);
        }



        //=========================================================================Auto Display Ads  =============================================================================

        smoothBottomBar = findViewById(R.id.bottomBar);
//==================================Default Fragment in activity======================================
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, new Home_Fragment());
        transaction.commit();
        //==================================Default Fragment in activity======================================


        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (i) {


                    case 0:
                        transaction.replace(R.id.fragmentContainer, new Home_Fragment());
                        transaction.commit();
                        break;


                    case 1:
                        transaction.replace(R.id.fragmentContainer, new LeaderBoard_Fragment());
                        transaction.commit();
                        break;


                    case 2:
                        transaction.replace(R.id.fragmentContainer, new Winner_Fragment());
                        transaction.commit();
                        break;


                    case 3:
                        transaction.replace(R.id.fragmentContainer, new Wallet_Fragment());
                        transaction.commit();
                        break;

                }

                return false;

            }

        });


    }


    private void displayInterstitialAds() {
        if (UnityAds.isReady(interstitialAdsId)) {
            UnityAds.show(MainActivity.this, interstitialAdsId);
        }
    }
}