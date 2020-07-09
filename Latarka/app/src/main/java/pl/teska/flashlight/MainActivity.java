package pl.teska.flashlight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonEnable;
    private ImageView imageFlashlight;
    private static final int CAMERA_REQUEST = 50;
    private boolean flashlightStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageFlashlight = (ImageView) findViewById(R.id.imageFlashlight);
        buttonEnable = (Button) findViewById(R.id.buttonEnable);

        final boolean hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean isEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;

        buttonEnable.setEnabled(!isEnabled);
        imageFlashlight.setEnabled(isEnabled);

        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
            }
        });

        imageFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraFlash) {
                    if(flashlightStatus) flashlightOff();
                    else
                        flashlightOn();
                }
                else {
                    Toast.makeText(MainActivity.this, "W tym urządzeniu nie ma latarki.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void flashlightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,true);
            flashlightStatus = true;
            imageFlashlight.setImageResource(R.drawable.switch_on);
        } catch (CameraAccessException e) {

        }
    }

    private void flashlightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,false);
            flashlightStatus = false;
            imageFlashlight.setImageResource(R.drawable.switch_off);
        } catch (CameraAccessException e) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST :
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    buttonEnable.setEnabled(false);
                    buttonEnable.setText("Latarka włączona");
                    imageFlashlight.setEnabled(true);
                } else {
                    Toast.makeText(MainActivity.this, "Odmowa dostępu do latarki", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
