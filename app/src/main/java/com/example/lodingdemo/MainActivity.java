package com.example.lodingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_number)
    Button btnNumber;
    @Bind(R.id.btn_msg)
    Button btnMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toast.makeText(this, "我悄悄的启动了", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_number, R.id.btn_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //获取联系人
            case R.id.btn_number:
                startActivity(new Intent(MainActivity.this, PeopleActivity.class));
                break;
            //获取短信
            case R.id.btn_msg:

                break;
        }
    }
}
