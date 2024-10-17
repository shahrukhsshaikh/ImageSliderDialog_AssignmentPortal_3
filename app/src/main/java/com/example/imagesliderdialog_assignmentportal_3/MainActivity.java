package com.example.imagesliderdialog_assignmentportal_3;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button imageSilder;
    Dialog customDialog;
    ImageView cancelButton, myImageSilder;
    TextView arrayListCount;
    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};
    int currentIndex = 0;
    Handler handler;
    Runnable imageSliderRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initViews() {
        imageSilder = findViewById(R.id.image_Silder); // Your button to open the slider
    }

    private void initListeners() {
        imageSilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageSliderDialog(); // Open the custom dialog when the button is clicked
            }
        });
    }

    private void openImageSliderDialog() {
        // Initialize the dialog
        customDialog = new Dialog(MainActivity.this);
        customDialog.setContentView(R.layout.image_slider);

        // Initialize dialog views
        cancelButton = customDialog.findViewById(R.id.cancelButton);
        myImageSilder = customDialog.findViewById(R.id.myImageSilder);
        arrayListCount = customDialog.findViewById(R.id.arrayListCount);

        // Start the image slider
        startImageSlider();

        // Set up the cancel button click listener to dismiss the dialog
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler != null && imageSliderRunnable != null) {
                    handler.removeCallbacks(imageSliderRunnable);
                }
                customDialog.dismiss(); // Close the dialog when the cancel button is clicked
            }
        });

        // Show the custom dialog
        customDialog.show();
    }

    // Start the image slider (forward)
    private void startImageSlider() {
        currentIndex = 0;
        handler = new Handler();
        imageSliderRunnable = new Runnable() {
            @Override
            public void run() {
                myImageSilder.setImageResource(images[currentIndex]);
                arrayListCount.setText("Image Index: " + (currentIndex + 1));
                currentIndex = (currentIndex + 1) % images.length;
                handler.postDelayed(this, 2000); // 2-second delay
            }
        };
        handler.post(imageSliderRunnable);
    }
}