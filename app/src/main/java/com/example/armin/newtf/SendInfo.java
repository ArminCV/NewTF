package com.example.armin.newtf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class SendInfo extends AppCompatActivity {

    public String diseaseName, description, treatment, prevention;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        diseaseName = intent.getStringExtra("key");
        SendResult(diseaseName);
    }

    public void SendResult(String diseaseName){
        switch (diseaseName){
            case "Ectoparasites":
                description = getString(R.string.epD);
                treatment = getString(R.string.epP);
                prevention = getString(R.string.epT);
                break;
            case "Cattle Warts":
                description = getString(R.string.wrD);
                treatment = getString(R.string.wrP);
                prevention = getString(R.string.wrT);
                break;
            case "Lumpy Skin":
                description = getString(R.string.lsD);
                treatment = getString(R.string.lsP);
                prevention = getString(R.string.lsT);
                break;
            case "Pink Eye":
                description = getString(R.string.peD);
                treatment = getString(R.string.peP);
                prevention = getString(R.string.peT);
                break;
            case "Lameness":
                description = getString(R.string.lmD);
                treatment = getString(R.string.lmP);
                prevention = getString(R.string.lmT);
                break;
            default:
                break;
        }
        Intent intent = new Intent(this, DisplayInfo.class);
        intent.putExtra("1", description);
        intent.putExtra("2", treatment);
        intent.putExtra("3", prevention);
        startActivity(intent);
    }

}
