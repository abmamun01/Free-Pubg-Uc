package com.mamunsproject.awesome_free_pubg_uc.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.anupkumarpanwar.scratchview.ScratchView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.awesome_free_pubg_uc.R;
import com.unity3d.ads.UnityAds;

import java.util.Random;

public class Scratch extends AppCompatActivity {

    private String GameId = "3948525";
    private String bannerAdsId = "Banner";
    private String InterstitialAdsId = "interstiatialForDiamondTopUp";
    private String rewardedVideoAdsId="rewardedVideo";
    private String videoAds="video";

    private boolean testMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);


        UnityAds.initialize(Scratch.this, GameId, testMode);


        TextView textView=findViewById(R.id.revealText);
        ScratchView scratchView=findViewById(R.id.scractviewid);



        Random random=new Random();
        int rand=random.nextInt(10);

        scratchView.setRevealListener(new ScratchView.IRevealListener() {
            @Override
            public void onRevealed(ScratchView scratchView) {


                textView.setText("Congrats You Got "+rand + "Coins !");
                Toast.makeText(getApplicationContext(),"Revealed ",Toast.LENGTH_SHORT).show();



                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database
                        .collection("Users")
                        .document(FirebaseAuth.getInstance().getUid())
                        .update("coins", FieldValue.increment(rand)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {



                        Toast.makeText(Scratch.this, rand +" Coins added in account.", Toast.LENGTH_SHORT).show();
                        if (UnityAds.isReady(InterstitialAdsId)) {
                            UnityAds.show(Scratch.this, InterstitialAdsId);
                        }

                        finish();

                    }
                });


            }

            @Override
            public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (UnityAds.isReady(InterstitialAdsId)) {
            UnityAds.show(Scratch.this, InterstitialAdsId);
            super.onBackPressed();

        }else{
            super.onBackPressed();
        }
    }

}