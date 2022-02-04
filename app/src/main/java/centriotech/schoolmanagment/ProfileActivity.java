package centriotech.schoolmanagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;
import centriotech.schoolmanagment.ui.AdminLogin;
import centriotech.schoolmanagment.ui.ParentLogin;
import centriotech.schoolmanagment.ui.TeacherLogin;
import centriotech.schoolmanagment.ui.TransportLogin;

public class ProfileActivity extends AppCompatActivity {

    BottomNavigationView nav_view_profile;

    SharedPreferenceConfig sharedPreferenceConfig;
    TextView logout, number;
    String num = "", Key = "";
    String Type = "", loginName = "";
    RequestQueue requestQueue;
    StringRequest stringRequest;
    TextView tv_name;
    String HttpUrl = "https://orapune.com/API_TEST/FetchType.php";
    String HTTPURL;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue1;
    private StringRequest mStringRequest1;
    private String url = "https://www.naukarikatta.com/School/FetechClassDivision.php";
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    String result = "";

    private RelativeLayout rlChangePassword;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = findViewById(R.id.tvLogout);
        number = findViewById(R.id.tvAccountName);
        rlChangePassword=findViewById(R.id.rlChangePassword);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        num = sharedPreferenceConfig.getnum("user");
        fetchType(num);


        nav_view_profile = findViewById(R.id.nav_view_profile);

        nav_view_profile.setOnNavigationItemSelectedListener(navListener);

        nav_view_profile.setItemBackground(null);

        rlChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), ForgotPassActivity.class);
//                intent.putExtra("mobile", editText_num.getText().toString());
//                intent.putExtra("backendotp", backedotp);
                startActivity(intent);


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceConfig.writeLogoutStatus(true);
                sharedPreferenceConfig.putnum("");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

                Toast.makeText(getApplicationContext(), "Logout sucessfull", Toast.LENGTH_SHORT).show();


            }
        });


    }


    public void fetchType(final String MobileNo) {


        // Creating string request with post method.
        stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        try {
                            JSONObject jsonObject = new JSONObject(ServerResponse);
                            int success = jsonObject.getInt("success");
                            if (success == 1) {
                                JSONArray cars = jsonObject.getJSONArray("cars");

                                for (int i = 0; i < cars.length(); i++) {
                                    JSONObject car = cars.getJSONObject(i);
//                                    Type = car.get("Type").toString();
                                    Type = car.get("Type").toString();
                                    if (Type.equals("Teacher")) {
//                                        Key = "mobileno";
//                                        HTTPURL = "https://orapune.com/API_TEST/TeacherProfile.php";
//                                        selectName(num);
//                                        TeacherLogin teacherLogin = new TeacherLogin();
//                                        FragmentManager fragmentManager = getSupportFragmentManager();
//                                        fragmentManager.beginTransaction().replace(R.id.frame, teacherLogin).commit();
                                        number.setText(MobileNo);

                                    } else if (Type.equals("Admin")) {
                                        number.setText(MobileNo);
//                                        AdminLogin adminLogin = new AdminLogin();
//                                        FragmentManager fm = getSupportFragmentManager();
//                                        fm.beginTransaction().replace(R.id.frame, adminLogin).commit();
                                        // Toast.makeText(getApplicationContext(), "Admin", Toast.LENGTH_SHORT).show();
                                    } else if (Type.equals("Parent")) {
                                        number.setText(MobileNo);
                                        // selectClassDivision(num);
//                                        ParentLogin parentLogin = new ParentLogin();
//                                        FragmentManager fm = getSupportFragmentManager();
//                                        fm.beginTransaction().replace(R.id.frame, parentLogin).commit();
                                    } else if (Type.equals("Transport")) {
                                        number.setText(MobileNo);
//                                        Key = "mobileNo";
//                                        HTTPURL = "https://www.naukarikatta.com/School/TransportProfile.php";
//                                        selectName(num);
//                                        TransportLogin transportLogin = new TransportLogin();
//                                        FragmentManager fm = getSupportFragmentManager();
//                                        fm.beginTransaction().replace(R.id.frame, transportLogin).commit();
                                    }


                                }

                            } else {
                                // Toast.makeText(getApplicationContext(), "no data found ", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.

                        // Showing error message if something goes wrong.
                        Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("MobileNo", MobileNo);


                return params;
            }

        };

        // Creating RequestQueue.
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);


    }

    public void selectName(final String Contact_No) {

        mStringRequest1 = new StringRequest(Request.Method.POST, HTTPURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray cars = jsonObject.getJSONArray("cars");

                        for (int i = 0; i < cars.length(); i++) {
                            JSONObject car = cars.getJSONObject(i);
                            loginName = car.get("name").toString();
                            tv_name.setText(loginName);
//                               String Roll= car.get("grNo").toString();

                        }

                    } else {
                        //  Toast.makeText(getApplicationContext(), "no data found ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put(Key, Contact_No);

                return params;
            }
        };
        mRequestQueue1 = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue1.add(mStringRequest1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Intent intent1 = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(intent1);
                    finish();

                    break;


                case R.id.navigation_dashboard:


//                    sharedPreferenceConfig.writeLogoutStatus(true);
//                    sharedPreferenceConfig.putnum("");
//                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    finish();

                    Toast.makeText(getApplicationContext(), "Dashboard", Toast.LENGTH_SHORT).show();


                    break;
                case R.id.navigation_profile:
//                    Profile profile = new Profile();
//                    FragmentManager fragmentManager2 = getSupportFragmentManager();
//                    fragmentManager2.beginTransaction().replace(R.id.frame, profile).addToBackStack("Profile").commit();

                    Intent intent2 = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent2);
                    finish();

                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed()
    {
//        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
//        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
//        back_pressed = System.currentTimeMillis();


        Intent intent1 = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent1);
        finish();
    }


}