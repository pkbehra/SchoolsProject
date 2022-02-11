package centriotech.schoolmanagment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreference;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;
import centriotech.schoolmanagment.WelcomeActivity;

public class TeacherProfileUpdate extends Fragment {

    EditText tv_name,tv_teacherid,tv_address,tv_emailid,tv_class,tv_div,tv_sub,tv_gender;
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 10000;
    SharedPreferenceConfig sharedPreferenceConfig;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/TeacherProfile.php";
    private RequestQueue mRequestQueue1;
    private StringRequest mStringRequest1;
    private String url1 = "https://orapune.com/API_TEST/TeacherProfileUpdate.php";
    String result="";
    CardView btn_submit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.teacherprofileupdate,container,false);
        tv_name=view.findViewById(R.id.teacher_update_teachername);
        tv_teacherid=view.findViewById(R.id.teacher_update_teacher_id);
        tv_address=view.findViewById(R.id.teacher_update_teacheraddress);
        tv_emailid=view.findViewById(R.id.teacher_update_teacheremail);
        tv_class=view.findViewById(R.id.teacher_update_teacherclasses);
        tv_div=view.findViewById(R.id.teacher_update_teacherdiv);
        tv_sub=view.findViewById(R.id.teacher_update_subject);
        tv_gender=view.findViewById(R.id.teacher_update_gender);
        btn_submit=view.findViewById(R.id.teacher_update_submit);
        sharedPreferenceConfig=new SharedPreferenceConfig(getActivity());
        final String Number=  sharedPreferenceConfig.getnum("user");
        teacherProfile(Number);
        final String token = SharedPreference.getInstance(getActivity()).getDeviceToken();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTeacher(tv_name.getText().toString(),tv_teacherid.getText().toString(),
                        tv_address.getText().toString(),tv_emailid.getText().toString(),tv_class.getText().toString(),tv_div.getText().toString()
                        ,tv_sub.getText().toString(),tv_gender.getText().toString(),Number,token);


            }
        });
        return view;
    }

    public  void teacherProfile(final String mobileno){
        mStringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    int success=jsonObject.getInt("success") ;
                    if (success==1){
                        JSONArray cars=jsonObject.getJSONArray("cars");

                        for(int i=0;i<cars.length();i++){
                            JSONObject car=cars.getJSONObject(i);
                            String name=(String) car.get("name");
                            String teacherid= (String) car.get("teacherid");
                            String mobileno= (String) car.get("mobileno");
                            String address= (String) car.get("address");
                            String emailid= (String) car.get("emailid");
                            String classes= (String) car.get("class");
                            String division= (String) car.get("division");
                            String subject= (String) car.get("subject");
                            String password= (String) car.get("password");
                            String gender= (String) car.get("gender");
                            String typeofteacher= (String) car.get("typeofteacher");

                            tv_name.setText(name);
                            tv_address.setText(address);
                            tv_class.setText(classes);
                            tv_div.setText(division);
                            tv_emailid.setText(emailid);
                            tv_gender.setText(gender);
                            tv_sub.setText(subject);
                            tv_teacherid.setText(teacherid);




                        }

                    }   else {
                        Toast.makeText(getActivity(), "no data found ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Tag","Error :" + error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams()
            {

                Map<String, String>  params = new HashMap<String, String>();
                params.put("mobileno",mobileno );

                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);


    }


    public  void updateTeacher(final String T_name, final String T_id, final String T_address, final String T_email, final String T_class, final String T_div, final String T_sub
            , final String T_gender , final String T_mobile , final String T_token){
        mStringRequest1=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {



                if (result.trim().equals("Profile Updated Successfully")) {

                    Toast.makeText(getActivity(),"Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), WelcomeActivity.class));


                }
                if (result.trim().equals("User Not Updated Try Again")) {


                    Toast.makeText(getActivity(), "Failed !!" + "please try again", Toast.LENGTH_LONG).show();

                }
                if (result.trim().equals("exception") || result.equals("unsuccessful")) {

                    Toast.makeText(getActivity(), "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Tag","Error :" + error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", T_name);
                params.put("teacherid",T_id );
                params.put("address", T_address);
                params.put("emailid", T_email);
                params.put("class", T_class);
                params.put("division",T_div);
                params.put("subject",T_sub);
                params.put("gender",T_gender);
                params.put("mobileno",T_mobile);
                params.put("token",T_token);

                return params;
            }
        };
        mRequestQueue1 = Volley.newRequestQueue(getActivity());
        mRequestQueue1.add(mStringRequest1);
    }

}

