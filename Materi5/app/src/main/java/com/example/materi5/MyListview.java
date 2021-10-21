package com.example.project5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MyListview extends AppCompatActivity {
    ListView listView;
    public String bhs_program[]={"prolog","c+","pascal","cobol","perl","Algo L","Java","PHP","phyton"};

    Spinner combo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listview);

        listView=(ListView) findViewById(R.id.listdata);
        combo=(Spinner) findViewById(R.id.combodata);

        ArrayAdapter adapter=new ArrayAdapter(MyListview.this,R.layout.support_simple_spinner_dropdown_item,bhs_program);
        listView.setAdapter(adapter);
        combo.setAdapter(adapter);

    }
}