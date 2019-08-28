package com.koustav.com.personaldiary;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koustav.com.personaldiary.Fragment.RegistrationFragment;

public class LoginActivity extends AppCompatActivity {
  private TextView biometricscheckTv, registrationTextview;
  private FingerprintManager fingerprintManager;
  private Button LoginButton;
  private EditText usernameET, passwordET;
  private ImageView loginIV;
  private LinearLayout loginLayout;
  private String username="tapabrata", password= "fzsv2";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    biometricscheckTv = findViewById(R.id.biometricsTv);
    LoginButton = findViewById(R.id.signInButton);
    usernameET = findViewById(R.id.usernameET);
    passwordET = findViewById(R.id.passwordET);
    loginIV = findViewById(R.id.loginIV);
    loginLayout = findViewById(R.id.loginlayout);
    registrationTextview = findViewById(R.id.registerTextview);
    registrationTextview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        findViewById(R.id.containerLayout).setVisibility(View.VISIBLE);
        pushFragments(new RegistrationFragment(),false,false,"registrationFragment");
        loginIV.setVisibility(View.GONE);
        loginLayout.setVisibility(View.GONE);

      }
    });
    scan();
    LoginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(usernameET.getText()==null)
        {
          usernameET.setHint("Username cant be empty empty");
          usernameET.setHintTextColor(getResources().getColor(R.color.IndianRed));
            Toast.makeText(LoginActivity.this,"username cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else if(passwordET.getText()==null)
        {
          passwordET.setHint("Password cant be empty");
          passwordET.setHintTextColor(getResources().getColor(R.color.IndianRed));
            Toast.makeText(LoginActivity.this,"password cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
          if((usernameET.getText().toString().equals(username))&&(passwordET.getText().toString().equals(password)))
          {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            MainActivity mainActivity = new MainActivity();
            LoginActivity.this.startActivity(intent);
          }
          else
          {
            Toast.makeText(LoginActivity.this,"username/password incorrect",Toast.LENGTH_SHORT).show();
          }
        }
      }
    });

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
          else {
            biometricscheckTv.setText("Can scan fingerprint");
          }
        }
      }
    }
    if (isSdkVersionSupported())
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
  private void pushFragments(Fragment fragment, boolean shouldAnimate,
                             boolean shouldAdd, String tag) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    if (shouldAnimate) {
      fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }
    fragmentTransaction.replace(R.id.containerLayout, fragment, tag);

    if (shouldAdd) {
      fragmentTransaction.addToBackStack(tag);
    } else {
      fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    fragmentTransaction.commit();
  }
}
