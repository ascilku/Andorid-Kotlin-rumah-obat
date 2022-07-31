package com.example.rumahobat_.activity.ui_apoteker.coba;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rumahobat_.R;



public class MainActivity extends AppCompatActivity  {
    private Button image, video, pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Siklus hidup onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Siklus hidup onResume",Toast.LENGTH_SHORT).show();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(this, "Siklus hidup onPause",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(this, "Siklus hidup onStop",Toast.LENGTH_SHORT).show();
//    }
//        image = (Button) findViewById(R.id.image);
//        video = (Button) findViewById(R.id.video);
//        pdf = (Button) findViewById(R.id.pdf);
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            image.setEnabled(false);
//            video.setEnabled(false);
//            pdf.setEnabled(false);
//            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
//        } else {
//            image.setEnabled(true);
//            video.setEnabled(true);
//            pdf.setEnabled(true);
//        }
//        image.setOnClickListener(this);
//        video.setOnClickListener(this);
//        pdf.setOnClickListener(this);
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == 0) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                image.setEnabled(true);
//                video.setEnabled(true);
//                pdf.setEnabled(true);
//            }
//        }
//    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.image:
//                Intent intent = new Intent(this, ImageActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.video:
////                Intent intent2 = new Intent(this, VideoActivity.class);
////                startActivity(intent2);
//                break;
//            case R.id.pdf:
////                Intent intent3 = new Intent(this, PdfActivity.class);
////                startActivity(intent3);
//                break;
//
//        }
//
//    }
}
