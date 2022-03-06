package com.hp.easytrip;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hp.easytrip.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {
    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _getph();
                binding.phLay.setEnabled(false);
                binding.otpLay.setVisibility(View.VISIBLE);
                binding.getOtpBtn.setVisibility(View.GONE);
            }
        });


    }

    private String _getph() {
        String ph = binding.phoneEdt.getText().toString().trim();
        if (ph.isEmpty()) {
            binding.phLay.setError("Invalid phone number");
        } else if (ph.length() != 10) {
            binding.phLay.setError("Invalid phone number");
        }
        else{
            return ph;
        }
        return "";
    }

}