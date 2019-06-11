package com.koustav.com.yaadein.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.koustav.com.yaadein.R;

public class MemoryList extends Fragment {
  private Button addNewButton;
  public FragmentTransaction ft;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View mview= inflater.inflate(R.layout.fragment_memory_list,container,false);
    initView(mview);
    return mview;
  }



  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    addNewButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.i("clickworking","clicking");
        AddNewMemory addNewMemory = new AddNewMemory();
        ft =getFragmentManager().beginTransaction();
        ft.add(addNewMemory,"new_memory");
        ft.replace(R.id.fragmentcontainer,addNewMemory);
        ft.setTransition(
          FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

      }
    });
  }
  private void initView(View mview) {
    addNewButton = mview.findViewById(R.id.addNewButton);

  }
}

