package centriotech.schoolmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VerifyenterOtp extends AppCompatActivity {

    EditText inputnumber1, inputnumber2, inputnumber3, inputnumber4, inputnumber5, inputnumber6;
    TextView textView, resendsotp;
    RelativeLayout verifybtton;
    String getbackedotp;
    ProgressBar progressBarverifyotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyenter_otp);
        inputnumber1 = findViewById(R.id.inputotp1);
        inputnumber2 = findViewById(R.id.inputotp2);
        inputnumber3 = findViewById(R.id.inputotp3);
        inputnumber4 = findViewById(R.id.inputotp4);
        inputnumber5 = findViewById(R.id.inputotp5);
        inputnumber6 = findViewById(R.id.inputotp6);

        textView = findViewById(R.id.textmobileshownumber);
        resendsotp = findViewById(R.id.textresendotp);
        verifybtton = findViewById(R.id.verify_pass_btn);
        progressBarverifyotp = findViewById(R.id.progress_bar_verify_otp);

        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        getbackedotp = getIntent().getStringExtra("backendotp");

        verifybtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (!inputnumber1.getText().toString().trim().isEmpty() && !inputnumber2.getText().toString().trim().isEmpty() && !inputnumber3.getText().toString().trim().isEmpty() && !inputnumber4.getText().toString().trim().isEmpty() && !inputnumber5.getText().toString().trim().isEmpty() && !inputnumber5.getText().toString().trim().isEmpty()) {
//
//                    String entercodeotp = inputnumber1.getText().toString() +
//                            inputnumber2.getText().toString() +
//                            inputnumber3.getText().toString() +
//                            inputnumber4.getText().toString() +
//                            inputnumber5.getText().toString() +
//                            inputnumber6.getText().toString();
//
//                    if (getbackedotp != null) {
//
//                        progressBarverifyotp.setVisibility(View.VISIBLE);
//                        verifybtton.setVisibility(View.INVISIBLE);
//
//                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
//                                getbackedotp, entercodeotp
//                        );
//                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
//                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                        progressBarverifyotp.setVisibility(View.GONE);
//                                        verifybtton.setVisibility(View.VISIBLE);
//                                        if (task.isSuccessful()) {
//                                            Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                            startActivity(intent);
//                                        } else {
//                                            Toast.makeText(VerifyenterOtp.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                    }
//                                });
//
//                    } else {
//                        Toast.makeText(VerifyenterOtp.this, "Please check Internet connection", Toast.LENGTH_SHORT).show();
//                    }
//
////                    Toast.makeText(VerifyenterOtp.this, "otp verify", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(VerifyenterOtp.this, "Please enter all number", Toast.LENGTH_SHORT).show();
//                }

                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(intent);

            }
        });

        numberotpmove();

        resendsotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        "+91" + getIntent().getStringExtra("mobile"),
//                        60,
//                        TimeUnit.SECONDS,
//                        VerifyenterOtp.this,
//                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                            @Override
//                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//
//                            }
//
//                            @Override
//                            public void onVerificationFailed(@NonNull FirebaseException e) {
//
//
//                                Toast.makeText(VerifyenterOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//
//                            @Override
//                            public void onCodeSent(@NonNull String newbackedotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                getbackedotp=newbackedotp;
//                                Toast.makeText(VerifyenterOtp.this, "OTP Send Sucessfully", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                );

                Toast.makeText(VerifyenterOtp.this, "OTP Send Sucessfully", Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void numberotpmove() {

        inputnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}