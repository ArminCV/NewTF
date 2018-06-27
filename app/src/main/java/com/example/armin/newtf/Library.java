package com.example.armin.newtf;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Library extends AppCompatActivity {
    String DiseaseList[] = {"Cattle Lice", "Lumpy Skin Disease", "Demodectic mange", "Cattle warts", "Ringworm", "Pinkeye", "Mastitis Disease"};
    ListView simpleList;
    Cursor cursor;
    int duration;
    Context context;
    String text="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_library, R.id.textView, DiseaseList);
        simpleList.setAdapter(arrayAdapter);
    }

}
