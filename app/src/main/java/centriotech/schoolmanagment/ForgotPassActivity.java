package centriotech.schoolmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ForgotPassActivity extends AppCompatActivity {

    EditText editText_num;
    RelativeLayout button_pass;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        editText_num = findViewById(R.id.input_mobile_numbers);
        button_pass = findViewById(R.id.change_pass_btns);
        progressBar = findViewById(R.id.progress_bar_sending_otps);


        button_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText_num.getText().toString().trim().isEmpty()) {
                    if ((editText_num.getText().toString().trim()).length() == 10) {
                        progressBar.setVisibility(View.VISIBLE);
                        button_pass.setVisibility(View.INVISIBLE);
//                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                "+91" + editText_num.getText().toString(),
//                                60,
//                                TimeUnit.SECONDS,
//                                ForgotPassActivity.this,
//                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                    @Override
//                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                                        progressBar.setVisibility(View.GONE);
//                                        button_pass.setVisibility(View.VISIBLE);
//                                    }
//
//                                    @Override
//                                    public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                        progressBar.setVisibility(View.GONE);
//                                        button_pass.setVisibility(View.VISIBLE);
//                                        Toast.makeText(ForgotPassActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                                    }
//
//                                    @Override
//                                    public void onCodeSent(@NonNull String backedotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                        progressBar.setVisibility(View.GONE);
//                                        button_pass.setVisibility(View.VISIBLE);
//                                        Intent intent = new Intent(getApplicationContext(), VerifyenterOtp.class);
//                                        intent.putExtra("mobile", editText_num.getText().toString());
//                                        intent.putExtra("backendotp", backedotp);
//                                        startActivity(intent);
//                                    }
//                                }
//                        );


                        Intent intent = new Intent(getApplicationContext(), VerifyenterOtp.class);
                        intent.putExtra("mobile", editText_num.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(ForgotPassActivity.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgotPassActivity.this, "Enter Mobile No", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }
}