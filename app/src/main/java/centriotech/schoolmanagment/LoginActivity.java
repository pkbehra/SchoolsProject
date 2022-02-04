package centriotech.schoolmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import centriotech.schoolmanagment.Utility.SharedPreference;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;

public class LoginActivity extends AppCompatActivity {

    TextView forgotpass;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    EditText et_num, et_pass;
    Button btn_signin;
    SharedPreferenceConfig sharedPreferenceConfig;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/Login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_num = findViewById(R.id.phone);
        et_pass = findViewById(R.id.password);
        btn_signin = findViewById(R.id.btn_login);
        forgotpass = findViewById(R.id.tvForgotPass);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
//
//        }
        if (checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }
        if (sharedPreferenceConfig.getnum("user") == null) {

        } else if (sharedPreferenceConfig.getnum("user").length() > 2) {
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_num.getText().toString().length() > 0 && et_pass.getText().toString().length() > 0) {
                    loginPage(et_num.getText().toString(), et_pass.getText().toString());

                } else {
                    Toast.makeText(getApplicationContext(), "Enter Mobile Number and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void loginPage(final String MobileNo, final String Password) {

        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                if (result.trim().equals("Login  Successfuly")) {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */
                    sharedPreferenceConfig.writeLoginStatus(true);

                    sharedPreferenceConfig.putnum(et_num.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(intent);
                    finish();
//


                }
                if (result.trim().equals("Please enter correct email and password")) {

                    // If username and password does not match display a error message
                    Toast.makeText(getApplicationContext(), result + "please try again", Toast.LENGTH_LONG).show();

                }
                if (result.trim().equals("exception") || result.equals("unsuccessful")) {

                    Toast.makeText(getApplicationContext(), "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Tag", "Error :" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("MobileNo", MobileNo);
                params.put("Password", Password);

                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(mStringRequest);
    }


    private boolean checkAndRequestPermissions() {
        int permissionCamera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        int readexternal = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int externalStoaragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (externalStoaragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (readexternal != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        finish();
    }
}
