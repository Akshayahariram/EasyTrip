package com.hp.easytrip;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hp.easytrip.databinding.ActivitySignupBinding;

import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {
    ActivitySignupBinding binding;
    FirebaseAuth mAuth;
    String mverificationid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);

        _onclick();



    }

    private void _onclick() {
        binding.getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!_getph().isEmpty()) {
                    binding.phLay.setEnabled(false);
                    binding.otpLay.setVisibility(View.VISIBLE);
                    binding.getOtpBtn.setVisibility(View.GONE);
                    _send_otp();
                }

            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!_get_otp().isEmpty()) {
                    _check_otp();
                }
            }
        });
    }


    private String _getph() {
        String ph = binding.phoneEdt.getText().toString().trim();
        if (ph.isEmpty()) {
            binding.phLay.setError("Invalid phone number");
        } else if (ph.length() != 10) {
            binding.phLay.setError("Invalid phone number");
        } else {
            return ph;
        }
        return "";
    }
    private void _send_otp() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + _getph())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                mverificationid = s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.phLay.setEnabled(true);
                                binding.otpLay.setVisibility(View.GONE);
                                binding.getOtpBtn.setVisibility(View.VISIBLE);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }


    private String _get_otp() {
        String otp = binding.otpEdt.getText().toString().trim();
        String cotp = binding.cotpEdt.getText().toString().trim();
        if (!otp.isEmpty()) {
            binding.otpEdtlay.setErrorEnabled(false);
        } else if (!cotp.isEmpty()) {
            binding.cotpEdtlay.setErrorEnabled(false);
        }
        if (otp.isEmpty()) {
            binding.otpEdtlay.setError("Invalid otp");
        } else if (cotp.isEmpty()) {
            binding.cotpEdtlay.setError("Invalid otp");
        } else if (!otp.equals(cotp)) {
            Toast.makeText(this, "Invalid Confirm OTP", Toast.LENGTH_SHORT).show();
        } else {
            return cotp;
        }
        return "";

    }


    private void _check_otp() {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mverificationid, _get_otp());
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        ProgressDialog dialog = new ProgressDialog(Signup.this);
        dialog.setMessage("Verifying...");
        dialog.setCancelable(false);
        dialog.show();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
}