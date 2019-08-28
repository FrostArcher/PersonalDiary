package com.koustav.com.personaldiary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.util.Log;
import android.widget.TextView;

public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {
  Context context;
  public FingerPrintHandler(Context context){
   this.context = context;
  }
  public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
    CancellationSignal cancellationSignal = new CancellationSignal();
    fingerprintManager.authenticate(cryptoObject, cancellationSignal,0,this,null);
  }

  @Override
  public void onAuthenticationError(int errorCode, CharSequence errString) {
    super.onAuthenticationError(errorCode, errString);
    Log.i("autherr","error::::::"+errString);
    this.update(errString, false);
  }

  @Override
  public void onAuthenticationFailed() {
    super.onAuthenticationFailed();
    this.update("Authentication Failed",false);
  }

  @Override
  public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
    super.onAuthenticationHelp(helpCode, helpString);
    this.update(helpString,false);
  }

  @Override
  public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
    super.onAuthenticationSucceeded(result);
    this.update("Authentication Successful",true);
  }

  public void update(CharSequence errString, boolean b) {
    TextView authenticationTv = (TextView) ((Activity)context).findViewById(R.id.biometricsTv);
    if (b)
    {
      Intent intent = new Intent(context, MainActivity.class);
      MainActivity mainActivity = new MainActivity();
      context.startActivity(intent);
    }
    else
      authenticationTv.setText("Error: "+errString);
  }
}
