package com.example.armin.newtf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            //hide notification bar
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_main_menu);

            Button capture_btn = (Button) findViewById(R.id.capture_btn);
            capture_btn.setOnClickListener(this);
            Button library_btn = (Button) findViewById(R.id.library_btn);
            library_btn.setOnClickListener(this);
            Button credits_btn = (Button) findViewById(R.id.credits_btn);
            credits_btn.setOnClickListener(this);

        }catch(Exception e){}
    }

    public void onClick(View v){
        Intent intent = null;

        switch (v.getId()) {
            case R.id.capture_btn:
                intent = new Intent(this, CaptureImage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.library_btn:
                intent = new Intent(this, Library.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.credits_btn:
                intent = new Intent(this, Credits.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            default:
                break;
        }
    }//onClick end

}
