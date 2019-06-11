package com.koustav.com.yaadein;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.koustav.com.yaadein.Fragment.MemoryList;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pushFragments(new MemoryList(), false, false, "memoryList");

  }
  private void pushFragments(Fragment fragment, boolean shouldAnimate,
                             boolean shouldAdd, String tag) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    if (shouldAnimate) {
      fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }
    fragmentTransaction.replace(R.id.fragmentcontainer, fragment, tag);

    if (shouldAdd) {
      fragmentTransaction.addToBackStack(tag);
    } else {
      fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    fragmentTransaction.commit();
  }
}
