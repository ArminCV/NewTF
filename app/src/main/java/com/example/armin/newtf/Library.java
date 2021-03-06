package com.example.armin.newtf;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Library extends AppCompatActivity {
    String DiseaseList[] = {"Ectoparasites", "Cattle Warts", "Lumpy Skin", "Pink Eye", "Lameness"};
    ListView diseaseList;
    Bundle bundle;
    public String description, treatment, prevention;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        bundle = new Bundle();
        diseaseList = (ListView) findViewById(R.id.diseaseList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_library, R.id.textView, DiseaseList);
        diseaseList.setAdapter(arrayAdapter);

        diseaseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                libraryCall(adapterView, view, i, l);
            }
        });
    }

    public void libraryCall(AdapterView<?> adapterView, View view, int i, long l){

        switch (i){
                case 0:
                    bundle.putInt("image", R.drawable.ep_img);
                    description = getString(R.string.epD);
                    treatment = getString(R.string.epP);
                    prevention = getString(R.string.epT);
                    break;
                case 1:
                    bundle.putInt("image", R.drawable.cw_img);
                    description = getString(R.string.wrD);
                    treatment = getString(R.string.wrP);
                    prevention = getString(R.string.wrT);
                    break;
                case 2:
                    bundle.putInt("image", R.drawable.ls_img);
                    description = getString(R.string.lsD);
                    treatment = getString(R.string.lsP);
                    prevention = getString(R.string.lsT);
                    break;
                case 3:
                    bundle.putInt("image", R.drawable.pe_img);
                    description = getString(R.string.peD);
                    treatment = getString(R.string.peP);
                    prevention = getString(R.string.peT);
                    break;
                case 4:
                    bundle.putInt("image", R.drawable.lm_img);
                    description = getString(R.string.lmD);
                    treatment = getString(R.string.lmP);
                    prevention = getString(R.string.lmT);
                    break;
                default:
                    break;
            }//switch end

        Intent intent = new Intent(this, DisplayInfo.class);
        intent.putExtras(bundle);
        intent.putExtra("1", description);
        intent.putExtra("2", treatment);
        intent.putExtra("3", prevention);
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
