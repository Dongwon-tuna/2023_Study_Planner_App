package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {


    private Button btn_back ;
    private TextView tv_sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        String str = intent.getStringExtra("str");



        tv_sub = findViewById(R.id.tv_sub);

        tv_sub.setText(str);
        //-------------------------btn_back 기능
        btn_back = findViewById(R.id.btn_back);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this,MainActivity.class);
                startActivity(intent); // 액티비티 이동 해주는 구문
            }
        });

    }
}