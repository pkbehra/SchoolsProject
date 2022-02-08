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

public class ParentProfileUpdate extends Fragment {
    EditText gr_no,roll_no,std_id,full_name,mother_name,dob,doa,gender,religion,
            cast,classes,division,catogry,blood_grop,house,contact,alt_cont,Email,address,addhar,physical_dis;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://www.naukarikatta.com/School/ParentProfile.php";
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String HttpUrl="https://www.naukarikatta.com/School/UpdateParentProfile.php";
    String email="";

    SharedPreferenceConfig sharedPreferenceConfig;
    Button btn_submit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.parentprofileupdate,container,false);

        btn_submit=view.findViewById(R.id.submit);
        gr_no=view.findViewById(R.id.gr_no);
        roll_no=view.findViewById(R.id.roll_no);
        std_id=view.findViewById(R.id.studentid);
        full_name=view.findViewById(R.id.name);

        mother_name=view.findViewById(R.id.mothername);
        dob=view.findViewById(R.id.dateofbirth);
        doa=view.findViewById(R.id.dateofadmission);

        gender=view.findViewById(R.id.gender);
        religion=view.findViewById(R.id.religion);
        cast=view.findViewById(R.id.caste);

        classes=view.findViewById(R.id.classes);
        division=view.findViewById(R.id.division);
        catogry=view.findViewById(R.id.category);

        blood_grop=view.findViewById(R.id.blood);
        house=view.findViewById(R.id.house);
//      contact=view.findViewById(R.id.contact);

        alt_cont=view.findViewById(R.id.altcontact);
        Email=view.findViewById(R.id.email);
        address=view.findViewById(R.id.address);
        addhar=view.findViewById(R.id.addhar);


        sharedPreferenceConfig=new SharedPreferenceConfig(getActivity());
        final String Number=  sharedPreferenceConfig.getnum("user");
        final String token = SharedPreference.getInstance(getActivity()).getDeviceToken();


        displayParentDetails(Number);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateParentProfile(gr_no.getText().toString(),roll_no.getText().toString(),doa.getText().toString(),Number
                        ,catogry.getText().toString(),full_name.getText().toString(),mother_name.getText().toString(),std_id.getText().toString(),dob.getText().toString(),
                        religion.getText().toString(),cast.getText().toString(),alt_cont.getText().toString(),Email.getText().toString(),address.getText().toString(),
                        addhar.getText().toString(),gender.getText().toString(),blood_grop.getText().toString(),house.getText().toString(),classes.getText().toString(),
                        division.getText().toString(),token);

            }
        });




        return view;
    }
    public  void displayParentDetails(final String Contact_No){


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

                            blood_grop.setText(car.get("bloodgroup").toString());
                            gr_no.setText(car.get("grNo").toString());
                            full_name.setText(car.get("fullname").toString());
                            mother_name.setText(car.get("mothername").toString());
                            std_id.setText(car.get("studentid").toString());
                            dob.setText(car.get("dateofbirth").toString());
                            religion.setText(car.get("religion").toString());
                            cast.setText(car.get("caste").toString());
                            alt_cont.setText(car.get("alternatecontactno").toString());
                            Email.setText(car.get("email").toString());
                            address.setText(car.get("address").toString());
                            addhar.setText(car.get("aadharno").toString());
                            gender.setText(car.get("gender").toString());
                            doa.setText(car.get("dateofadmission").toString());
                            catogry.setText(car.get("category").toString());
                            house.setText(car.get("House").toString());
                            classes.setText(car.get("class").toString());
                            division.setText(car.get("division").toString());
                            roll_no.setText(car.get("rollno").toString());




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
                params.put("contactno", Contact_No);

                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);



    }
    public  void  updateParentProfile(final String GrNo, final String RollNo, final String DateOfAd, final String ContactNo, final String Category , final String FullName
            , final String MotherName , final String StudentID , final String DateOfBirth, final String Religion, final String Caste , final String AlterCon, final String Email_Id
            , final String AddressS, final String AadharNo, final String Gender, final String BloodGroup, final String House, final String Classes, final String Divison, final String TokenS){


        // Creating string request with post method.
        stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {



                        if (result.trim().equals("User Updated Successfully")) {
                            Toast.makeText(getActivity(), "User Updated Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), WelcomeActivity.class));


                        }
                        if (result.equals("User Not Updated Try Again")) {


                            Toast.makeText(getActivity(), "Failed !!" + "please try again", Toast.LENGTH_LONG).show();

                        }
                        if (result.equals("exception") || result.equals("unsuccessful")) {

                            Toast.makeText(getActivity(), "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.

                        // Showing error message if something goes wrong.
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                params.put("grNo",GrNo );
                params.put("rollno",RollNo );
                params.put("dateofadmission",DateOfAd );
                params.put("contactno",ContactNo );
                params.put("category",Category);
                params.put("fullname",FullName );
                params.put("mothername",MotherName );
                params.put("studentid",StudentID );
                params.put("dateofbirth",DateOfBirth );
                params.put("religion",Religion );
                params.put("caste",Caste );
                params.put("alternatecontactno",AlterCon );
                params.put("email",Email_Id );
                params.put("address",AddressS );
                params.put("aadharno",AadharNo );
                params.put("gender",Gender );
                params.put("bloodgroup",BloodGroup );
                params.put("House",House );
                params.put("class",Classes );
                params.put("division",Divison );
                params.put("token",TokenS );

                return params;
            }

        };

        // Creating RequestQueue.
        requestQueue = Volley.newRequestQueue(getActivity());

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);




    }



}