package com.example.armin.newtf;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String MODEL_PATH = "file:///android_asset/optimized_graph.pb";
    private static final String LABEL_PATH = "file:///android_asset/labels.txt";

    private static final int INPUT_SIZE = 299;
    private static final int IMAGE_MEAN = 128;
    private static final float IMAGE_STD = 128;
    private static final String INPUT_NAME = "Mul";
    private static final String OUTPUT_NAME = "final_result";

    private Classifier classifier;

    private Executor executor = Executors.newSingleThreadExecutor();
    private TextView textViewResult;
    private Button btnDetectObject, info;
    private ImageView imageViewResult;
    private CameraView cameraView;
    public String newDisease, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView = findViewById(R.id.cameraView);
        textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());
        btnDetectObject = findViewById(R.id.btnDetectObject);
        info = findViewById(R.id.info);
        info.setVisibility(View.INVISIBLE);

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

                Bitmap bitmap = cameraKitImage.getBitmap();

                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);

                final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);

                result = results.toString().replace("[", "").replace("]", "");

                textViewResult.setText("Suspected Disease: " + result);

                if(result.equalsIgnoreCase("healthyskin")){
                    info.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Healthy Skin", Toast.LENGTH_LONG).show();
                }
                else{
                    info.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

        btnDetectObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.captureImage();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInfo(view);
            }
        });

        initTensorFlowAndLoadModel();
    }

    public void displayInfo(View v){
        if(result.equalsIgnoreCase("healthyskin")){
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }else if(result.equalsIgnoreCase("ectoparasites")){
            newDisease="Ectoparasites";
        }
        else if(result.equalsIgnoreCase("cattlewarts")){
            newDisease="Cattle Warts";
        }
        else if(result.equalsIgnoreCase("lameness")){
            newDisease="Lameness";
        }
        else if(result.equalsIgnoreCase("lumpyskin")){
            newDisease="Lumpy Skin";
        }
        else if(result.equalsIgnoreCase("pinkeye")){
            newDisease="Pink Eye";
        }else { }
        Intent intent = new Intent(this, SendInfo.class);
        intent.putExtra("key", newDisease);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {

        cameraView.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                classifier.close();
            }
        });
    }

    private void initTensorFlowAndLoadModel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_PATH,
                            LABEL_PATH,
                            INPUT_SIZE,
                            IMAGE_MEAN,
                            IMAGE_STD,
                            INPUT_NAME,
                            OUTPUT_NAME);
                    makeButtonVisible();
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }

    private void makeButtonVisible() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnDetectObject.setVisibility(View.VISIBLE);
            }
        });
    }

}
