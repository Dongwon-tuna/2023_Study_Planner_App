package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_move;

    EditText et_id;
    Button btn_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_id = findViewById(R.id.et_id); //id에 숨을 불어 넣어 줌
        btn_test  = findViewById(R.id.btn_test);

        btn_move = findViewById(R.id.btn_move);

        // btn_test의 버튼 역할
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_id.setText("황동원1234");
            }
        });

        // btn_move의 버튼 역할
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                startActivity(intent); // 액티비티 이동 해주는 구문
            }
        });


    }
}