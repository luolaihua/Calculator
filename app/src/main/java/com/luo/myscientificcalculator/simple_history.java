package com.luo.myscientificcalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class simple_history extends AppCompatActivity {
    private ArrayList <String> data = new ArrayList<>();
    private static String  history_str = "666" ;
    private String [] history_String = {"1214224","1214224","1214224","1214224","1214224"};

    @Override
    protected void onResume() {
        super.onResume();
        //SharedPreferences sharedPreferences = getSharedPreferences("simple_history", MODE_PRIVATE);
        //history_str = sharedPreferences.getString("str_history","6262929191561");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_history);
        SharedPreferences sharedPreferences = getSharedPreferences("simple_history", MODE_PRIVATE);
        history_str = sharedPreferences.getString("str_history","6262929191561");
        history_String = history_str.split("#");
        ListView history = (ListView) findViewById(R.id.simple_history);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(simple_history.this,
                android.R.layout.simple_list_item_1, history_String);
        history.setAdapter(adapter);

        history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(simple_history.this, history_String[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
