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

public class UpdateTransport extends Fragment {

    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 10000;
    String result = "";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/TransportProfile.php";
    private RequestQueue mRequestQueue1;
    private StringRequest mStringRequest1;
    private String url1 = "https://orapune.com/API_TEST/TransportProfileUpdate.php";

    Button btn_update;
    SharedPreferenceConfig sharedPreferenceConfig;

    EditText et_name,et_typeofvehicle,et_vehicleno,et_licenceNo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transportupdate, container, false);
        et_name=view.findViewById(R.id.trans_update_transportname);
        et_typeofvehicle=view.findViewById(R.id.trans_update_typeofvehicle);
        et_vehicleno=view.findViewById(R.id.trans_update_vehiclenumber);
        et_licenceNo=view.findViewById(R.id.trans_update_licence_no);
        btn_update=view.findViewById(R.id.trans_update_submit);

        sharedPreferenceConfig=new SharedPreferenceConfig(getActivity());

        final String Number=  sharedPreferenceConfig.getnum("user");

        final String token = SharedPreference.getInstance(getActivity()).getDeviceToken();

        Toast.makeText(getActivity(), "Tokan"+token, Toast.LENGTH_SHORT).show();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTransport(et_name.getText().toString(),Number,et_typeofvehicle.getText().toString(),et_vehicleno.getText().toString(),
                        et_licenceNo.getText().toString(),token);
            }
        });


        transportDisplay(Number);

        return view;
    }
    public  void transportDisplay(final String mobileNo){
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
                            String mobileNo= (String) car.get("mobileNo");
                            String TypeOfVehical= (String) car.get("TypeOfVehical");
                            String vehicleNo= (String) car.get("VehicleNo");
                            String Licence_No= (String) car.get("Licence_No");
                            et_name.setText(name);
                            et_licenceNo.setText(Licence_No);
                            et_typeofvehicle.setText(TypeOfVehical);
                            et_vehicleno.setText(vehicleNo);

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
                params.put("mobileNo", mobileNo);

                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);
    }


    public  void updateTransport(final String NameT, final String MobileT, final String VehicleT, final String VehicleNoT, final String LicenceT, final String TokenT) {
        mStringRequest1= new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
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
                Log.i("Tag", "Error :" + error.toString());

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("name", NameT);
                params.put("mobileNo", MobileT);
                params.put("TypeOfVehical", VehicleT);
                params.put("VehicleNo", VehicleNoT);
                params.put("Licence_No", LicenceT);
                params.put("token", TokenT);

                return params;
            }
        };
        mRequestQueue1 = Volley.newRequestQueue(getActivity());
        mRequestQueue1.add(mStringRequest1);
    }


}

