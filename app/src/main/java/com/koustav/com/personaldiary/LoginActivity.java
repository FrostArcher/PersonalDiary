package com.koustav.com.personaldiary;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
  private TextView biometricscheckTv;
  private FingerprintManager fingerprintManager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    biometricscheckTv = findViewById(R.id.biometricsTv);
    scan();

  }
  public static boolean isBiometricPromptEnabled() {
    return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P);
  }
  public static boolean isSdkVersionSupported() {
    return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
  }
  public static boolean isHardwareSupported(Context context) {
    FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(context);
    return fingerprintManager.isHardwareDetected();
  }
  public static boolean isFingerprintAvailable(Context context) {
    FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(context);
    return fingerprintManager.hasEnrolledFingerprints();
  }
  public static boolean isPermissionGranted(Context context) {
    return ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) ==
      PackageManager.PERMISSION_GRANTED;
  }
  public void scan(){
    if(isBiometricPromptEnabled())
    {
      if (!isHardwareSupported(this))
        biometricscheckTv.setText("hardware not supported");
      else
      {
        if (!isPermissionGranted(this))
          biometricscheckTv.setText("Please grant biometrics permission in settings");
        else
        {
          if (!isFingerprintAvailable(this))
            biometricscheckTv.setText("Please Add a fringerpring to your device");
          else
            biometricscheckTv.setText("Can scan fingerprint");
        }
      }
    }
    else if (isSdkVersionSupported())
    {
      fingerprintManager= (FingerprintManager)getSystemService(FINGERPRINT_SERVICE);
      if (!isHardwareSupported(this))
        biometricscheckTv.setText("hardware not supported");
      else
      {
        if (!isPermissionGranted(this))
          biometricscheckTv.setText("Please grant biometrics permission in settings");
        else
        {
          if (!isFingerprintAvailable(this))
            biometricscheckTv.setText("Please Add a fringerpring to your device");
          else {
            biometricscheckTv.setText("Scan Fingerprint to Unlock Your Diary");
            FingerPrintHandler fingerPrintHandler = new FingerPrintHandler(this);
            fingerPrintHandler.startAuth(fingerprintManager,null);
          }
        }
      }
    }
  }
  @Override
  protected void onRestart() {
    super.onRestart();
    scan();
  }
}
