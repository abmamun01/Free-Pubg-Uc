package com.mamunsproject.awesome_free_pubg_uc.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.awesome_free_pubg_uc.Activity.RoyalPass;
import com.mamunsproject.awesome_free_pubg_uc.Model.User;
import com.mamunsproject.awesome_free_pubg_uc.Model.Withdraw_Request;
import com.mamunsproject.awesome_free_pubg_uc.R;
import com.unity3d.ads.UnityAds;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Wallet_Fragment extends Fragment {

    FirebaseFirestore database;
    User user;
    TextView ccoins;
    Button button1,button2,button3,button4,button5,button6;
    EditText editText;
    FirebaseAuth auth;


    private String GameId = "3948525";

    private String InterstitialAdsId = "interstiatialForDiamondTopUp";
    private String rewardedVideoAdsId="rewardedVideo";
    private String videoAds="video";



    private boolean testMode = true;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_wallet_, container, false);

        UnityAds.initialize(getActivity(), GameId, testMode);


        auth=FirebaseAuth.getInstance();

        ccoins = view.findViewById(R.id.currentCoins);
        button1=view.findViewById(R.id.thirtyDiamondid);
        button2=view.findViewById(R.id.sixtyDiamondId);
        button3=view.findViewById(R.id.getElitepass);
        button4=view.findViewById(R.id.getDjAlok);
        editText=view.findViewById(R.id.free_fire_id);

        database= FirebaseFirestore.getInstance();


        database.collection("Users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user=documentSnapshot.toObject(User.class);

                ccoins=view.findViewById(R.id.currentCoins);
                ccoins.setText(user.getCoins()+"");

            }
        });




//=====================================Button 1==================================================


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button1.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        button1.setEnabled(true);
                    }
                },3000);


                if (UnityAds.isReady(videoAds)) {
                    UnityAds.show(getActivity(), videoAds);

                }else {
                    Toast.makeText(getContext(),"Please Wait!  ",Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "The interstitial wasn't loaded yet.");

                }


                if (!editText.getText().toString().isEmpty()){
                    if (user.getCoins() > 5000) {

                        String name=user.getName();
                        String uid=FirebaseAuth.getInstance().getUid();
                        String pubgId=editText.getText().toString();
                        long ccoins=user.getCoins();
                        String demand="30 Diamond";


                        Withdraw_Request request=new Withdraw_Request(uid,pubgId, user.getName(),ccoins,demand,name);


                        database.collection("withdraws")
                                .document(name)
                                .set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                button1.setEnabled(false);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        button1.setEnabled(true);
                                    }
                                },10000);



                            }
                        });

                        database
                                .collection("Users")
                                .document(FirebaseAuth.getInstance().getUid())
                                .update("coins", FieldValue.increment(-5000)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(getContext(), 5000 +" Coins Subracted From Your account.", Toast.LENGTH_SHORT).show();


                            }
                        });

                    }
                    else {

                        new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Not Enough Coin !")
                                .setContentText("You Have to Earn 5000 Coins ! To Get 30 Diamond.")
                                .show();
                    }


                }else {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Please Enter Your Free Fire id!")
                            .show();
                }

            }
        });




        //=====================================Button 1==================================================


//=====================================Button 2==================================================

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button2.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        button2.setEnabled(true);
                    }
                },3000);


                if (UnityAds.isReady(videoAds)) {
                    UnityAds.show(getActivity(), videoAds);

                }else {
                    Toast.makeText(getContext(),"Try Again Later!  ",Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "The interstitial wasn't loaded yet.");

                }


                if (!editText.getText().toString().isEmpty()){
                    if (user.getCoins() > 5000) {

                        String name=user.getName();
                        String uid=FirebaseAuth.getInstance().getUid();
                        String pubgId=editText.getText().toString();
                        long ccoins=user.getCoins();
                        String demand="30 Diamond";




                        Withdraw_Request request=new Withdraw_Request(uid,pubgId, user.getName(),ccoins,demand,name);


                        database.collection("withdraws")
                                .document(name)
                                .set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                // startActivity(new Intent(getContext(), Massege.class));
                                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Successfully!")
                                        .setContentText("Request Sent successfully!")
                                        .show();
                            }
                        });

                        database
                                .collection("Users")
                                .document(FirebaseAuth.getInstance().getUid())
                                .update("coins", FieldValue.increment(-5000)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(getContext(), 5000 +" Coins Subracted From Your account.", Toast.LENGTH_SHORT).show();


                            }
                        });

                    }
                    else {

                        new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Not Enough Coin !")
                                .setContentText("You Have to Earn 5000 Coins ! To Get 30 Diamond.")
                                .show();
                    }


                }else {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Please Enter Your Free Fire id!")
                            .show();
                }

            }
        });


//=====================================Button 2==================================================

//=====================================Button 3==================================================

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button3.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        button3.setEnabled(true);
                    }
                },3000);


                if (UnityAds.isReady(videoAds)) {
                    UnityAds.show(getActivity(), videoAds);

                }else {
                    Toast.makeText(getContext(),"Try Again Later!  ",Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "The interstitial wasn't loaded yet.");

                }


                if (!editText.getText().toString().isEmpty()){
                    if (user.getCoins() > 5000) {

                        String name=user.getName();
                        String uid=FirebaseAuth.getInstance().getUid();
                        String ffid=editText.getText().toString();
                        long ccoins=user.getCoins();
                        String demand="30 Diamond";

                        Withdraw_Request request=new Withdraw_Request(uid,ffid, user.getName(),ccoins,demand,name);


                        database.collection("withdraws")
                                .document(name)
                                .set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                // startActivity(new Intent(getContext(), Massege.class));
                                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Successfully!")
                                        .setContentText("Request Sent successfully!")
                                        .show();
                            }
                        });

                        database
                                .collection("Users")
                                .document(FirebaseAuth.getInstance().getUid())
                                .update("coins", FieldValue.increment(-5000)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(getContext(), 5000 +" Coins Subracted From Your account.", Toast.LENGTH_SHORT).show();


                            }
                        });

                    }
                    else {

                        new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Not Enough Coin !")
                                .setContentText("You Have to Earn 5000 Coins ! To Get 30 Diamond.")
                                .show();
                    }


                }else {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Please Enter Your Free Fire id!")
                            .show();
                }

            }
        });






//=====================================Button 3==================================================




        //=====================================Button 4==================================================


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button4.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        button4.setEnabled(true);
                    }
                },3000);


                if (UnityAds.isReady(videoAds)) {
                    UnityAds.show(getActivity(), videoAds);

                }else {
                    Toast.makeText(getContext(),"Try Again Later!  ",Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "The interstitial wasn't loaded yet.");

                }


                if (!editText.getText().toString().isEmpty()){
                    if (user.getCoins() > 5000) {

                        String name=user.getName();
                        String uid=FirebaseAuth.getInstance().getUid();
                        String ffid=editText.getText().toString();
                        long ccoins=user.getCoins();
                        String demand="30 Diamond";

                        Withdraw_Request request=new Withdraw_Request(uid,ffid, user.getName(),ccoins,demand,name);


                        database.collection("withdraws")
                                .document(name)
                                .set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                // startActivity(new Intent(getContext(), Massege.class));
                                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Successfully!")
                                        .setContentText("Request Sent successfully!")
                                        .show();
                            }
                        });

                        database
                                .collection("Users")
                                .document(FirebaseAuth.getInstance().getUid())
                                .update("coins", FieldValue.increment(-5000)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(getContext(), 5000 +" Coins Subracted From Your account.", Toast.LENGTH_SHORT).show();


                            }
                        });

                    }
                    else {

                        new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Not Enough Coin !")
                                .setContentText("You Have to Earn 5000 Coins ! To Get 30 Diamond.")
                                .show();
                    }


                }else {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Please Enter Your Free Fire id!")
                            .show();
                }

            }
        });


//=====================================Button 4==================================================




        return view;


    }
}