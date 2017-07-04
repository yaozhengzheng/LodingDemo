package com.example.lodingdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PeopleActivity extends AppCompatActivity {

    @Bind(R.id.list_item)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        ButterKnife.bind(this);
        List<Contact> list = GetPeople.getInstance().getpeople(this);
        listView.setAdapter(new MyAdapter(this, list));
    }
}
