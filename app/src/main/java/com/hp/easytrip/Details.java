package com.hp.easytrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
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
        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.dir&&name.equals("Badrinath")){
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=Badrinath");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }  if (id==R.id.dir&&name.equals("Gangotri")){
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=Gangotri");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }  if (id==R.id.dir&&name.equals("Yamunotri")){
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=Yamunotri");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                } if (id==R.id.dir&&name.equals("Kedarnath")){
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=Kedarnath");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
                return false;
            }
        });
    }



}