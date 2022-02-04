package centriotech.schoolmanagment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

import centriotech.schoolmanagment.LoginActivity;
import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;
import de.hdodenhof.circleimageview.CircleImageView;

public class TransportProfile extends Fragment {

    TextView tv_name,tv_mobileNum,tv_typeofvehicle,tv_vehiclenum,tv_licence;
    RelativeLayout logout;
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 10000;
    CircleImageView imageButton;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/TransportProfile.php";

    SharedPreferenceConfig sharedPreferenceConfig;
    String result="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transportlayout, container, false);
        tv_name=view.findViewById(R.id.trans_tname);
        imageButton=view.findViewById(R.id.trans_editprofile);
        tv_mobileNum=view.findViewById(R.id.trans_transportmobile);
        tv_licence=view.findViewById(R.id.trans_licence_no);
        tv_typeofvehicle=view.findViewById(R.id.trans_vehicle);
        tv_vehiclenum=view.findViewById(R.id.trans_vehicle_Num);
        logout=view.findViewById(R.id.rlLogOutnew);

        sharedPreferenceConfig=new SharedPreferenceConfig(getActivity());
        String Number=  sharedPreferenceConfig.getnum("user");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceConfig.writeLogoutStatus(true);
                sharedPreferenceConfig.putnum("");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
               // finish();

                Toast.makeText(getActivity(), "Logout sucessfull", Toast.LENGTH_SHORT).show();


            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateTransport updateTransport=new UpdateTransport();
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.frame,updateTransport).addToBackStack("Trans").commit();
            }
        });

        transportProfile(Number);
        return view;
    }

    public  void transportProfile(final String mobileNo){
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
                            tv_name.setText(name);
                            tv_mobileNum.setText(mobileNo);
                            tv_licence.setText(Licence_No);
                            tv_typeofvehicle.setText(TypeOfVehical);
                            tv_vehiclenum.setText(vehicleNo);

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

}
