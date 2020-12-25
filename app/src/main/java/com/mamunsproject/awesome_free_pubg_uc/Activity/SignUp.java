package com.mamunsproject.awesome_free_pubg_uc.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.awesome_free_pubg_uc.MainActivity;
import com.mamunsproject.awesome_free_pubg_uc.Model.User;
import com.mamunsproject.awesome_free_pubg_uc.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private Button sign_btn,loginuppage  ;
    private EditText email,password,pubgid,name_;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        clickListener();
    }



    private void init() {

        auth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        sign_btn=findViewById(R.id.signUp);
        email=findViewById(R.id.emailid);
        password=findViewById(R.id.passwordid);
        loginuppage=findViewById(R.id.signUp);
        pubgid=findViewById(R.id.pubgid);
        name_=findViewById(R.id.nameid );
        database=FirebaseFirestore.getInstance();





    }

    private void clickListener() {

        loginuppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LogIN.class));
            }
        });

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SweetAlertDialog pDialog = new SweetAlertDialog(SignUp.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();

                String emails,passwords,PubgId,name;

                emails=email.getText().toString();
                passwords=password.getText().toString();
                PubgId=pubgid.getText().toString();
                name=name_.getText().toString();


                User user=new User(name,emails,passwords,PubgId);


                if (!password.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()&&!pubgid.getText().toString().isEmpty()){
                    auth.createUserWithEmailAndPassword(emails,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                //======for Unique ID======
                                String uid=task.getResult().getUser().getUid();

                                database.collection("Users")
                                        .document(uid)
                                        .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()){
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            finish();
                                        }else {
                                            Toast.makeText(getApplicationContext(),"Failed!"+task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();


                                        }
                                    }
                                });
                                Toast.makeText(getApplicationContext(),"Successfully Created!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                pDialog.dismissWithAnimation();
                            }else {
                                Toast.makeText(getApplicationContext(),"Failed!",Toast.LENGTH_SHORT).show();
                                pDialog.dismissWithAnimation();


                            }

                        }
                    });

                }
                else {

                    new SweetAlertDialog(SignUp.this)
                            .setTitleText("Field must not be Empty!")
                            .show();
                    pDialog.dismissWithAnimation();


                }


            }
        });

    }
}