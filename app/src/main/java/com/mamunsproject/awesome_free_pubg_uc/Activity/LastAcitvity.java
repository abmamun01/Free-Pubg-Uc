package com.mamunsproject.awesome_free_pubg_uc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mamunsproject.awesome_free_pubg_uc.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LastAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_acitvity);


        Button button=findViewById(R.id.buttonss);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Done! ",Toast.LENGTH_SHORT).show();

            }
        });
    }
}