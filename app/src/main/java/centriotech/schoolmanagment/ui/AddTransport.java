package centriotech.schoolmanagment.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

import centriotech.schoolmanagment.R;

public class AddTransport extends Fragment {

    Button slectvecl;
    EditText name, mobileno, vechicalno, licenceno, password, conpass;
    Spinner typeofvechical;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/AddTransport.php";
    Button tregsister;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addtransportlayout, container, false);

        tregsister = view.findViewById(R.id.tran_register);
        name = view.findViewById(R.id.tran_name);
        mobileno = view.findViewById(R.id.tran_mobile_no);
        typeofvechical = view.findViewById(R.id.Tran_spin_vehical);
        vechicalno = view.findViewById(R.id.tran_vehical_no);
        licenceno = view.findViewById(R.id.tran_licence_no);
        password = view.findViewById(R.id.tran_password);

        slectvecl = view.findViewById(R.id.slelect_vec);


        slectvecl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showAlertDialog();


            }
        });


        tregsister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AddTransport(name.getText().toString(), mobileno.getText().toString(), slectvecl.getText().toString(), vechicalno.getText().toString(), licenceno.getText().toString(), password.getText().toString());

            }
        });
        return view;
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Select Vehicle");
        String[] items = {"Bus", "Travels", "Cabs"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        slectvecl.setText("Bus");
                        dialog.dismiss();
                        break;
                    case 1:
                        slectvecl.setText("Travles");
                        dialog.dismiss();
                        break;
                    case 2:
                        slectvecl.setText("Cabs");
                        dialog.dismiss();
                        break;

                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }


    public void AddTransport(final String transport_name, final String transport_num, final String transport_type, final String transport_vehicle, final String transport_lic, final String transport_pass) {
        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                if (result.trim().equals("data inserted")) {

                    AdminLogin adminLogin = new AdminLogin();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame, adminLogin).commit();

                    Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();


                }

                if (result.trim().equals("data not inserted")) {


                    Toast.makeText(getActivity(), "Registration Failed " + "please try again", Toast.LENGTH_LONG).show();

                }
                if (result.trim().equals("User Already Exists")) {


                    Toast.makeText(getActivity(), "User Already Exists", Toast.LENGTH_LONG).show();

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
                params.put("name", transport_name);
                params.put("mobileNo", transport_num);
                params.put("TypeOfVehical", transport_type);
                params.put("VehicleNo", transport_vehicle);
                params.put("Licence_No", transport_lic);
                params.put("password", transport_pass);
                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);
    }

}
