package centriotech.schoolmanagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import centriotech.schoolmanagment.ui.AdminProfile;
import centriotech.schoolmanagment.ui.Dashboard;
import centriotech.schoolmanagment.ui.ParentLogin;
import centriotech.schoolmanagment.ui.ParentProfile;
import centriotech.schoolmanagment.ui.TeacherLogin;
import centriotech.schoolmanagment.ui.TeacherProfile;
import centriotech.schoolmanagment.ui.TransportLogin;
import centriotech.schoolmanagment.ui.TransportProfile;


public class WelcomeActivity extends AppCompatActivity {


    BottomNavigationView nav_view;

    SharedPreferenceConfig sharedPreferenceConfig;
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

    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        nav_view = findViewById(R.id.nav_view);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        num = sharedPreferenceConfig.getnum("user");
        fetchType(num);


        nav_view.setOnNavigationItemSelectedListener(navListener);

        nav_view.setItemBackground(null);

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
                                    Type = car.get("Type").toString();
                                    Type = car.get("Type").toString();
                                    if (Type.equals("Teacher")) {
//                                        Key = "mobileno";
//                                        HTTPURL = "https://orapune.com/API_TEST/TeacherProfile.php";
//                                        selectName(num);
                                        TeacherLogin teacherLogin = new TeacherLogin();
                                        FragmentManager fragmentManager = getSupportFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.frame, teacherLogin).commit();


                                    } else if (Type.equals("Admin")) {
                                        AdminLogin adminLogin = new AdminLogin();
                                        FragmentManager fm = getSupportFragmentManager();
                                        fm.beginTransaction().replace(R.id.frame, adminLogin).commit();
                                    } else if (Type.equals("Parent")) {
                                        // selectClassDivision(num);
                                        ParentLogin parentLogin = new ParentLogin();
                                        FragmentManager fm = getSupportFragmentManager();
                                        fm.beginTransaction().replace(R.id.frame, parentLogin).commit();
                                    } else if (Type.equals("Transport")) {
                                        Key = "mobileNo";
                                        HTTPURL = "https://www.naukarikatta.com/School/TransportProfile.php";
                                        selectName(num);
                                        TransportLogin transportLogin = new TransportLogin();
                                        FragmentManager fm = getSupportFragmentManager();
                                        fm.beginTransaction().replace(R.id.frame, transportLogin).commit();
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


                    if (Type.equals("Teacher")) {

//                            Key = "mobileno";
//                            HTTPURL = "https://orapune.com/API_TEST/TeacherProfile.php";
//                            selectName(num);
                        TeacherLogin teacherLogin = new TeacherLogin();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.frame, teacherLogin).commit();

                    } else if (Type.equals("Parent")) {
                        ParentLogin parentLogin = new ParentLogin();
                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.frame, parentLogin).commit();
                    } else if (Type.equals("Transport")) {
                        TransportLogin transportLogin = new TransportLogin();
                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.frame, transportLogin).commit();
                    } else if (Type.equals("Admin")) {
                        AdminLogin adminLogin = new AdminLogin();
                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.frame, adminLogin).commit();
                    }


                    break;


                case R.id.navigation_dashboard:


//                    sharedPreferenceConfig.writeLogoutStatus(true);
//                    sharedPreferenceConfig.putnum("");
//                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
//                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    finish();

                    Dashboard dashboard = new Dashboard();
                    FragmentManager fmmms = getSupportFragmentManager();
                    fmmms.beginTransaction().replace(R.id.frame, dashboard).addToBackStack("Teacher").commit();


                    Toast.makeText(getApplicationContext(), "Dashboard", Toast.LENGTH_SHORT).show();


                    break;
                case R.id.navigation_profile:


                    if (Type.equals("Teacher")) {

                        TeacherProfile teacherProfile = new TeacherProfile();
                        FragmentManager fmmm = getSupportFragmentManager();
                        fmmm.beginTransaction().replace(R.id.frame, teacherProfile).addToBackStack("Teacher").commit();

                    } else if (Type.equals("Parent")) {
                        ParentProfile parentProfile = new ParentProfile();
                        FragmentManager fmmm = getSupportFragmentManager();
                        fmmm.beginTransaction().replace(R.id.frame, parentProfile).addToBackStack("Parent").commit();

                    } else if (Type.equals("Transport")) {

                        TransportProfile transportProfile = new TransportProfile();
                        FragmentManager fmmm = getSupportFragmentManager();
                        fmmm.beginTransaction().replace(R.id.frame, transportProfile).addToBackStack("Transport").commit();


                    } else if (Type.equals("Admin")) {
                        AdminProfile adminProfile = new AdminProfile();
                        FragmentManager fmmm = getSupportFragmentManager();
                        fmmm.beginTransaction().replace(R.id.frame, adminProfile).addToBackStack("Admin").commit();

                    }


                    break;


            }
            return true;
        }
    };


//    @Override
//    public void onBackPressed()
//    {
////        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
////        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
////        back_pressed = System.currentTimeMillis();
//
//
//    }


}