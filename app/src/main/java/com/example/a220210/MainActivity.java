package com.example.a220210;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.Manifest;
import android.net.Uri;
import android.content.pm.PackageManager;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final int RUNTIME_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.call).setOnClickListener(this);
    }

    // 실행하고자 하는 AVD버전이 22이상일 경우, 일부분의 명령어는 안드로이드에서 별도의 승인을 받아야 사용할 수 있다.
    // ContextCompat.checkSelfPermission()은 사용자가 사용할 수있는 권한이 승인 되어 있는지 확인 하는 메서드
    public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    RUNTIME_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            startCall();
        }
    }

    private void startCall() {
        Intent callIntent = new Intent();
        callIntent.setAction(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0101234557"));
        startActivity(callIntent);
    }

    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RUNTIME_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 사용자가 권한을 수락하면
                    startCall();
                } else {

                }
                return;
            }
        }
    }
}

