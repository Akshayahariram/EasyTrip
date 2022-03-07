package com.hp.easytrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hp.easytrip.databinding.ActivityDetailsBinding;

public class Details extends AppCompatActivity {

    ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("NAME");
        String content = getIntent().getStringExtra("CONTENT");

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.collaping.setTitle(name);
        binding.content.setText(content);
        switch (name){
            case "Badrinath":
                binding.backImg.setImageDrawable(getResources().getDrawable(R.drawable.badrniath));
                break; case "Gangotri":
                binding.backImg.setImageDrawable(getResources().getDrawable(R.drawable.gangotri));
                break; case "Yamunotri":
                binding.backImg.setImageDrawable(getResources().getDrawable(R.drawable.yamunotrie));
                break; case "Kedarnath":
                binding.backImg.setImageDrawable(getResources().getDrawable(R.drawable.khendra));
                break;
        }
    }

}