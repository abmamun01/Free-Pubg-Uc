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
import com.mamunsproject.awesome_free_pubg_uc.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LogIN extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private Button login_btn, singuppage;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_i_n);


        init();
        clickListener();

        if (auth.getCurrentUser()!=null){

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

    }


    private void init() {

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        login_btn = findViewById(R.id.login);
        email = findViewById(R.id.emailid);
        password = findViewById(R.id.passwordid);
        singuppage = findViewById(R.id.signUp);


        //======================


    }

    private void clickListener() {

        singuppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));


            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SweetAlertDialog pDialog = new SweetAlertDialog(LogIN.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();


                if (!password.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()){
                    String emails, passwords;
                    emails = email.getText().toString();
                    passwords = password.getText().toString();

                    auth.signInWithEmailAndPassword(emails, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Successfully Loged In", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        }
                    });
                }else {
                    new SweetAlertDialog(LogIN.this)
                            .setTitleText("Field must not be Empty!")
                            .show();

                    pDialog.dismissWithAnimation();


                }



            }
        });



    }

}