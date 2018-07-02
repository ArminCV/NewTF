package com.example.armin.newtf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayInfo extends AppCompatActivity{

    TextView desc, treat, prev;
    ImageView imgv;
    String description, treatment, prevention, msg;
    EditText phoneNum;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        Intent intent = getIntent();
        bundle = this.getIntent().getExtras();

        imgv = findViewById(R.id.imgv);
        desc = findViewById(R.id.description);
        treat = findViewById(R.id.treatment);
        prev = findViewById(R.id.prevention);

        int img = bundle.getInt("image");
        description = intent.getStringExtra("1");
        treatment = intent.getStringExtra("2");
        prevention = intent.getStringExtra("3");

        imgv.setImageResource(img);
        desc.setText(description);
        treat.setText(treatment);
        prev.setText(prevention);

        phoneNum = findViewById(R.id.et_id);

        final Button send_btn = (Button) findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(phoneNum.getText())){
                    Toast.makeText(getApplicationContext(), "Enter mobile number first", Toast.LENGTH_SHORT).show();
                }
               else{
                    msg = description;
                    String id = phoneNum.getText().toString();
                    sendSMS(id, msg);
                }
            }
        });

    }

    public void sendSMS(String id, String msg){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(id, null, msg, null, null);
        Toast.makeText(getApplicationContext(), "Message Sent!",Toast.LENGTH_SHORT).show();
    }

}
