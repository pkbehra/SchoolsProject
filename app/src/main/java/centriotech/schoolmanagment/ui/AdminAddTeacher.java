package centriotech.schoolmanagment.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import centriotech.schoolmanagment.R;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AdminAddTeacher extends Fragment {

    Button classteacher, regularteacher, parttimeteacher;
    String classes_string = "", division_string = "", subject_string = "", dislay_class = "", dislay_div = "", dislay_sub = "", FinalClass = "", FinalDiv = "", FinalSub = "";
    RadioGroup radioGroup1;
    ImageButton btn_add;
    Button regsister;
    EditText name, teacherid, mobileno, address, email, password;
    TextView tgender;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/TeacherRegistration.php";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminaddteacherlayout, container, false);

        regsister = view.findViewById(R.id.register);
        name = view.findViewById(R.id.name);
        teacherid = view.findViewById(R.id.teacher_id);
        mobileno = view.findViewById(R.id.mobile_no);
        address = view.findViewById(R.id.address);
        email = view.findViewById(R.id.email_id);
        tgender = view.findViewById(R.id.tech_gender);
        password = view.findViewById(R.id.teach_pass);
        classteacher = view.findViewById(R.id.select);

        radioGroup1 = (RadioGroup) view.findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.radioButton1){
                    tgender.setText("Male");
                }else if(checkedId==R.id.radioButton2){
                    tgender.setText("Female");
                }
            }
        });


        classteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }

        });
        
        regsister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addTeacher(name.getText().toString(), teacherid.getText().toString(), mobileno.getText().toString(), address.getText().toString(), email.getText().toString(), FinalClass, FinalDiv, FinalSub, password.getText().toString(), tgender.getText().toString(), "Teacher");

            }
        });

        return view;
    }

    private void showDialog() {


        //Now we need an AlertDialog.Builder object
        final android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(getActivity());
        View mView = getLayoutInflater().inflate(R.layout.customlayout, null);
        mBuilder.setTitle("Select Classes");

        //setting the view of the builder to our custom view that we already inflated
        mBuilder.setView(mView);


        final TextView display=mView.findViewById(R.id.displaySelect);
        Button btn_cancel=mView.findViewById(R.id.cancel);
        Button btn_submit=mView.findViewById(R.id.selectedList);
        btn_add=mView.findViewById(R.id.image);
        //Spinner first
        final String[] classes = new String[] {"1", "2", "3", "4", "5", "6", "7","8","9","10","11","12"};
        Spinner spinner_class = (Spinner) mView.findViewById(R.id.classesSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, classes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_class.setAdapter(adapter);
        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                classes_string = parent.getItemAtPosition(position).toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //spinner second
        String[] divisions = new String[] {"A", "B", "C", "D", "E"};
        Spinner spinner_div = (Spinner) mView.findViewById(R.id.divSpinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, divisions);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_div.setAdapter(adapter1);
        spinner_div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                division_string = parent.getItemAtPosition(position).toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        //spinner third
        String[] subjects = new String[] {"English", "Science", "Social Science", "Algebra", "Geometry","Mathematics","History"};
        Spinner spinner_sub = (Spinner) mView.findViewById(R.id.subSpinner);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, subjects);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sub.setAdapter(adapter2);
        spinner_sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                subject_string = parent.getItemAtPosition(position).toString();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //add data to textview
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dislay_class=dislay_class+classes_string+",";
                dislay_div=dislay_div+division_string+",";
                dislay_sub=dislay_sub+subject_string+",";
                display.setText("Classes:"+dislay_class+"\n"+"Division:"+dislay_div+"\n"+"Subject:"+dislay_sub);



            }
        });
        //finally creating the alert dialog and displaying it
        final android.app.AlertDialog mDialog = mBuilder.create();
        mDialog.setCancelable(false);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalSub="";
                FinalDiv="";
                FinalClass="";
                FinalClass=dislay_class;
                FinalDiv=dislay_div;
                FinalSub=dislay_sub;
                dislay_class="";
                dislay_sub="";
                dislay_div="";

                mDialog.dismiss();


            }
        });



        mDialog.show();
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classes_string="";
                division_string="";
                subject_string="";
                dislay_class="";
                dislay_div="";
                dislay_sub="";

                mDialog.dismiss();

            }
        });
    }

    public void addTeacher(final String Teacher_name, final String Teacher_id, final String Teacher_mob, final String Teacher_add, final String Teacher_email, final String Teacher_class, final String Teacher_div, final String Teacher_sub
            , final String Teacher_pass , final String Teacher_gender, final String Teacher_type) {

        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                if (result.trim().equals("data inserted")) {

                    AdminLogin adminLogin = new AdminLogin();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame, adminLogin).commit();

                    Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();



                }
                if (result.trim().equals("Teacher Already Exists")) {


                    Toast.makeText(getActivity(), "Teacher Already Exists", Toast.LENGTH_LONG).show();

                }
                if (result.trim().equals("data not inserted")) {


                    Toast.makeText(getActivity(), "Registration Failed !!" + "please try again", Toast.LENGTH_LONG).show();

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
                params.put("name", Teacher_name);
                params.put("teacherid", Teacher_id);
                params.put("mobileno", Teacher_mob);
                params.put("address", Teacher_add);
                params.put("emailid", Teacher_email);
                params.put("class",Teacher_class);
                params.put("division", Teacher_div);
                params.put("subject",Teacher_sub );
                params.put("password", Teacher_pass);
                params.put("gender", Teacher_gender);
                params.put("typeofteacher", Teacher_type);
                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);
    }

}


