package com.mamunsproject.awesome_free_pubg_uc.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.operation.ListenComplete;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.awesome_free_pubg_uc.Activity.RoyalPass;
import com.mamunsproject.awesome_free_pubg_uc.Activity.Scratch;
import com.mamunsproject.awesome_free_pubg_uc.Activity.Spinner_Activity;
import com.mamunsproject.awesome_free_pubg_uc.MainActivity;
import com.mamunsproject.awesome_free_pubg_uc.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Fragment extends Fragment {

    private SliderLayout sliderLayout;
    CardView spinCard, scratchCard, watchCard, joinCard;


    //=======================Unity Ads===========================
    private String GameId = "3948525";
    private String bannerAdsId = "Banner";
    private String InterstitialAdsId = "interstiatialForDiamondTopUp";
    private String rewardedVideoAdsId="rewardedVideo";
    private String videoAds="video";

    FirebaseFirestore database;


    private boolean testMode = true;


    //=======================Unity Ads===========================


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);


        database=FirebaseFirestore.getInstance();
        //=======================Unity Ads===========================
        final UnityAdsListener myAdsListener = new UnityAdsListener ();

        UnityAds.initialize(getActivity(), GameId, testMode);











        IUnityAdsListener unityAdsListener = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {

            }

            @Override
            public void onUnityAdsStart(String s) {



            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {

            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {


            }
        };

        UnityAds.setListener(unityAdsListener);


        //=======================Unity Ads===========================


        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);

        setSliderView();


        spinCard = view.findViewById(R.id.spinCard);
        scratchCard = view.findViewById(R.id.scratchCard);
        watchCard = view.findViewById(R.id.watchandEarn);
        joinCard = view.findViewById(R.id.joinContest);


        spinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UnityAds.isReady(InterstitialAdsId)) {
                    UnityAds.show(getActivity(), InterstitialAdsId);
                    startActivity(new Intent(getContext(), Spinner_Activity.class));

                }else {
                    Toast.makeText(getContext(),"Try Again !" ,Toast.LENGTH_SHORT).show();

                }


            }
        });


        scratchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UnityAds.isReady(InterstitialAdsId)) {
                    UnityAds.show(getActivity(), InterstitialAdsId);
                    startActivity(new Intent(getContext(), Scratch.class));

                }else {
                    Toast.makeText(getContext(),"Try Again !" ,Toast.LENGTH_SHORT).show();
                }

            }
        });


        watchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRewardedAd();

            }
        });


        joinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UnityAds.isReady(videoAds)) {
                    UnityAds.show(getActivity(), videoAds);
                    startActivity(new Intent(getContext(), RoyalPass.class));

                }else {
                    Toast.makeText(getContext(),"Try Again Later!  ",Toast.LENGTH_SHORT).show();
                }

                joinCard.setEnabled(true);


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Write whatever to want to do after delay specified (1 sec)
                        joinCard.setEnabled(true);

                    }
                }, 100000);


            }
        });


        return view;
    }


    private void setSliderView() {
        for (int i = 0; i < 12; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {

                case 0:
                    sliderView.setImageUrl("https://c4.wallpaperflare.com/wallpaper/666/441/256/pubg-pubh-playerunknown-s-battlegrounds-wallpaper-preview.jpg");

                    sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {

                            Toast.makeText(getContext(), "Clicked" + sliderView, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

                case 1:
                    sliderView.setImageDrawable(R.drawable.pubg13);
                    break;

                case 2:
                    sliderView.setImageUrl("http://www.pngmart.com/files/13/PUBG-Squad-Team-PNG-Image.png");
                    break;

                case 3:
                    sliderView.setImageUrl("https://images.unsplash.com/photo-1567027757540-7b572280fa22?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80");
                    break;

                case 4:
                    sliderView.setImageUrl("https://c4.wallpaperflare.com/wallpaper/576/796/883/video-game-playerunknown-s-battlegrounds-sniper-wallpaper-preview.jpg");
                    break;
                case 5:
                    sliderView.setImageUrl("https://c4.wallpaperflare.com/wallpaper/636/481/128/game-the-game-game-playerunknown-wallpaper-preview.jpg");
                    break;
                case 6:
                    sliderView.setImageUrl("https://i.pinimg.com/236x/e7/f1/71/e7f17157eaf8210cfc063122ce2bc2f8.jpg");
                    break;
                case 7:
                    sliderView.setImageUrl("https://i.pinimg.com/564x/7d/2b/11/7d2b1181dd4471d91f783b23b7981262.jpg");
                    break;
                case 8:
                    sliderView.setImageUrl("https://i.pinimg.com/564x/ce/b7/aa/ceb7aa3d3d58c2e6a923effe866ac8d7.jpg");
                    break;

                case 9:
                    sliderView.setImageUrl("https://i.pinimg.com/564x/8a/ec/e5/8aece56ebb114452c71628e5f3475f4e.jpg");
                    break;


                case 10:
                    sliderView.setImageUrl("https://i.pinimg.com/236x/f8/60/91/f860914cedd82e68a8d243c8ebf2241f.jpg");
                    break;


                case 11:
                    sliderView.setImageUrl("https://i.pinimg.com/564x/ca/f0/77/caf0771a6c6e931359dde3987927b8b5.jpg");
                    break;


                case 12:
                    sliderView.setImageUrl("https://i.pinimg.com/236x/41/a0/09/41a00985456ac8dc2afa2c49109fa37f.jpg");
                    break;


            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER);
            sliderLayout.addSliderView(sliderView);
        }


    }








    // Implement a function to display an ad if the Placement is ready:
    public void displayRewardedAd () {
        if (UnityAds.isReady (rewardedVideoAdsId)) {
            UnityAds.show (getActivity(), rewardedVideoAdsId);
            database
                    .collection("Users")
                    .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                    .update("coins", FieldValue.increment(10)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "10" + " Coins added in account.", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    // Implement the IUnityAdsListener interface methods:
    private class UnityAdsListener implements IUnityAdsListener {

        public void onUnityAdsReady (String placementId) {
            // Implement functionality for an ad being ready to show.

        }

        @Override
        public void onUnityAdsStart (String placementId) {
            // Implement functionality for a user starting to watch an ad.
        }

        @Override
        public void onUnityAdsFinish (String placementId, UnityAds.FinishState finishState) {
            // Implement conditional logic for each ad completion status:
            if (finishState.equals(UnityAds.FinishState.COMPLETED)) {
                // Reward the user for watching the ad to completion.

            } else if (finishState.equals(UnityAds.FinishState.SKIPPED)) {
                // Do not reward the user for skipping the ad.
            } else if (finishState.equals(UnityAds.FinishState.ERROR)) {
                // Log an error.
            }
        }

        @Override
        public void onUnityAdsError (UnityAds.UnityAdsError error, String message) {
            // Implement functionality for a Unity Ads service error occurring.
        }
    }

}




