package com.koustav.com.personaldiary.Fragment;

import android.content.Context;
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
import android.widget.EditText;

import com.koustav.com.personaldiary.LoginActivity;
import com.koustav.com.personaldiary.R;

import java.io.File;

public class RegistrationFragment extends Fragment {
    private EditText firstnameET, lastnameET, passwordET, confirmET, emailET;
    private Button registerButton;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mview= inflater.inflate(R.layout.fragment_registration,container,false);
        mContext = (LoginActivity) getActivity();
        initView(mview);
        return mview;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private void initView(View mview) {
    firstnameET = mview.findViewById(R.id.FirstNameET);
    lastnameET = mview.findViewById(R.id.LastNameET);
    passwordET = mview.findViewById(R.id.passwordET);
    confirmET = mview.findViewById(R.id.confirmPasswordET);
    emailET = mview.findViewById(R.id.EmailET);
    registerButton = mview.findViewById(R.id.registerButton);
    }
    private void save_registrationdetails(){
        String filename = "userfile";
        File file = new File(mContext.getFilesDir(), filename);



    }
}
