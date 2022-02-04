package centriotech.schoolmanagment.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import centriotech.schoolmanagment.R;

public class AdminAddStudent extends Fragment {

    final String Category[] = new String[]{"Select Category", "Open", "OBC", "SC", "ST", "SBC", "VJ", "NT-B", "NT-C", "NT-D", "Other"};
    EditText Grno, rollno, fullname, mothername, studentid, religion, caste, password, mobiles, contactno, altercontactno, emailid, address, adhar, DateOfAdmission, conpass;
    TextView Dob, gender, physicallydisabled, tgender, tphysical;
    String home = "";
    String mobile = "", pass = "";
    Spinner bloodgroup, category, house, Classes, div_spin;
    Button regsister, dob, doa, select_class, select_div, select_cat, slect_blood, select_house;
    ImageButton btn_date, btn_date_add;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String Category_string = "";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/student_registration.php";
    String group = "";
    DatePickerDialog datePickerDialog;
    String[] typeOfClass = {"Select Class", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] typeOfDivision = {"Select Division", "A", "B", "C", "D", "E"};
    String Blood = "", String_div = "", string_class = "";
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    RadioGroup radioGroup1, radioGroup2;
    final String blood[] = new String[]{"Select Blood Group", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminaddstudentlayout, container, false);


        regsister = view.findViewById(R.id.btn_add_st);

        Grno = view.findViewById(R.id.gr_no);
        rollno = view.findViewById(R.id.roll_no);
        studentid = view.findViewById(R.id.stud_id);
        fullname = view.findViewById(R.id.name);
        mothername = view.findViewById(R.id.mname);

        dob = view.findViewById(R.id.btn_dob);
        doa = view.findViewById(R.id.btn_doa);
        radioGroup1 = view.findViewById(R.id.radioGroup1);
        tgender = view.findViewById(R.id.tgender);
        tphysical = view.findViewById(R.id.tphysical);

        mobiles = view.findViewById(R.id.mobile);
        religion = view.findViewById(R.id.religion);
        caste = view.findViewById(R.id.caste);


        select_class = view.findViewById(R.id.btn_class);
        select_div = view.findViewById(R.id.btn_div);
        select_cat = view.findViewById(R.id.btn_cat);
        slect_blood = view.findViewById(R.id.btn_blood);
        select_house = view.findViewById(R.id.btn_house);

        password = view.findViewById(R.id.st_pass);
        //contactno = view.findViewById(R.id.contact_no);
        altercontactno = view.findViewById(R.id.st_alt_contact_no);
        emailid = view.findViewById(R.id.st_email_id);
        address = view.findViewById(R.id.st_address);
        adhar = view.findViewById(R.id.st_addhar);
        radioGroup2 = view.findViewById(R.id.radioGroup2);

        //Raadio Button
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    tgender.setText("Male");
                } else if (checkedId == R.id.radioButton2) {
                    tgender.setText("Female");
                }
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton3) {
                    tphysical.setText("Yes");
                } else if (checkedId == R.id.radioButton4) {
                    tphysical.setText("No");
                }
            }
        });

        // Calander button

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth + "/" + month + "/" + year);
                        dob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();

            }
        });

        doa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        doa.setText(dayOfMonth + "/" + month + "/" + year);
                        doa.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();

            }
        });


        // Spinner Button

        select_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                classAlertDialog();


            }
        });

        select_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                divAlertDialog();


            }
        });

        select_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                catAlertDialog();


            }
        });
        slect_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bloodAlertDialog();


            }
        });

        select_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                houseAlertDialog();

            }
        });

        // Button for Add students

        regsister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStudents(Grno.getText().toString(), rollno.getText().toString(), studentid.getText().toString(), fullname.getText().toString(), mothername.getText().toString(), dob.getText().toString(), doa.getText().toString(), tgender.getText().toString(), mobiles.getText().toString(), religion.getText().toString(), caste.getText().toString(), select_class.getText().toString(), select_div.getText().toString(), select_cat.getText().toString(), slect_blood.getText().toString(), select_house.getText().toString(), password.getText().toString(), altercontactno.getText().toString(), emailid.getText().toString(), address.getText().toString(), adhar.getText().toString(), tphysical.getText().toString());
               // AddStudents(Grno.getText().toString(), rollno.getText().toString(), studentid.getText().toString(), fullname.getText().toString(), mothername.getText().toString(), dob.getText().toString(), doa.getText().toString(), tgender.getText().toString(),mobiles.getText().toString(), religion.getText().toString(), caste.getText().toString(), select_class.getText().toString(), select_div.getText().toString(), select_cat.getText().toString(), slect_blood.getText().toString(), select_house.getText().toString(),password.getText().toString(), altercontactno.getText().toString(), emailid.getText().toString(),"jhdgf","12345","yes");


            }
        });


        return view;
    }

    private void AddStudents(final String GRNO, final String RollNo, final String StudentId, final String FullName, final String MotherName, final String DateOfBirth, final String DateOfAd, final String Gender, final String ContactNo,
                             final String Religion, final String Caste, final String ClassName, final String DivName, final String Cate, final String BloodGroup, final String House, final String PassWord, final String AlterContactNo, final String Email, final String Address, final String AadharNo, final String PhisicalHand) {

        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                if (result.trim().equals("Student Already Exists")) {


                    Toast.makeText(getActivity(), "Student Already Exists", Toast.LENGTH_LONG).show();

                }
                if (result.trim().equals("data inserted")) {

                    AdminLogin adminLogin = new AdminLogin();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame, adminLogin).commit();

                    Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_LONG).show();



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

                params.put("grNo", GRNO);
                params.put("rollno", RollNo);
                params.put("studentid", StudentId);
                params.put("fullname", FullName);
                params.put("mothername", MotherName);

                params.put("dateofbirth", DateOfBirth);
                params.put("dateofadmission", DateOfAd);
                params.put("gender", Gender);
                params.put("mobileno", ContactNo);

                params.put("religion", Religion);
                params.put("caste", Caste);
                params.put("class", ClassName);
                params.put("division", DivName);
                params.put("category", Cate);
                params.put("bloodgroup", BloodGroup);

                params.put("House", House);
                params.put("password", PassWord);


                params.put("alternatecontactno", AlterContactNo);
                params.put("email", Email);
                params.put("address", Address);
                params.put("aadharno", AadharNo);

                params.put("physicallydisable", PhisicalHand);


                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);


    }

    private void houseAlertDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Select House");
        String[] items = {"A", "B", "C", "D", "E"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        select_house.setText("A");
                        dialog.dismiss();
                        break;
                    case 1:
                        select_house.setText("B");
                        dialog.dismiss();
                        break;
                    case 2:
                        select_house.setText("C");
                        dialog.dismiss();
                        break;
                    case 3:
                        select_house.setText("D");
                        dialog.dismiss();
                        break;
                    case 4:
                        select_house.setText("D");
                        dialog.dismiss();
                        break;


                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }

    private void bloodAlertDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Select Blood Group");
        String[] items = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        slect_blood.setText("A+");
                        dialog.dismiss();
                        break;
                    case 1:
                        slect_blood.setText("A-");
                        dialog.dismiss();
                        break;
                    case 2:
                        slect_blood.setText("B+");
                        dialog.dismiss();
                        break;

                    case 3:
                        slect_blood.setText("B-");
                        dialog.dismiss();
                        break;
                    case 4:
                        slect_blood.setText("AB+");
                        dialog.dismiss();
                        break;

                    case 5:
                        slect_blood.setText("AB-");
                        dialog.dismiss();
                        break;
                    case 6:
                        slect_blood.setText("O+");
                        dialog.dismiss();
                        break;
                    case 7:
                        slect_blood.setText("O-");
                        dialog.dismiss();
                        break;


                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();


    }

    private void catAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Select Category");
        String[] items = {"Open", "OBC", "SC", "ST", "SBC", "VJ", "NT-B", "NT-C", "NT-D", "Other"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        select_cat.setText("Open");
                        dialog.dismiss();
                        break;
                    case 1:
                        select_cat.setText("OBC");
                        dialog.dismiss();
                        break;
                    case 2:
                        select_cat.setText("SC");
                        dialog.dismiss();
                        break;
                    case 3:
                        select_cat.setText("ST");
                        dialog.dismiss();
                        break;
                    case 4:
                        select_cat.setText("SBC");
                        dialog.dismiss();
                        break;
                    case 5:
                        select_cat.setText("VJ");
                        dialog.dismiss();
                        break;
                    case 6:
                        select_cat.setText("NT-B");
                        dialog.dismiss();
                        break;
                    case 7:
                        select_cat.setText("NT-C");
                        dialog.dismiss();
                        break;
                    case 8:
                        select_cat.setText("NT-D");
                        dialog.dismiss();
                        break;
                    case 9:
                        select_class.setText("Others");
                        dialog.dismiss();
                        break;


                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();


    }

    private void divAlertDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Select Class Division");
        String[] items = {"A", "B", "C", "D", "E"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        select_div.setText("A");
                        dialog.dismiss();
                        break;
                    case 1:
                        select_div.setText("B");
                        dialog.dismiss();
                        break;
                    case 2:
                        select_div.setText("C");
                        dialog.dismiss();
                        break;
                    case 3:
                        select_div.setText("D");
                        dialog.dismiss();
                        break;
                    case 4:
                        select_div.setText("D");
                        dialog.dismiss();
                        break;


                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();


    }

    private void classAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Select Class");
        String[] items = {"Class 1", "Class 2", "Class 3", "Class 4", "Class 5", "Class 6", "Class 7", "Class 8", "Class 9", "Class 10", "Class 11", "Class 12"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        select_class.setText("1");
                        dialog.dismiss();
                        break;
                    case 1:
                        select_class.setText("2");
                        dialog.dismiss();
                        break;
                    case 2:
                        select_class.setText("3");
                        dialog.dismiss();
                        break;
                    case 3:
                        select_class.setText("4");
                        dialog.dismiss();
                        break;
                    case 4:
                        select_class.setText("5");
                        dialog.dismiss();
                        break;
                    case 5:
                        select_class.setText("6");
                        dialog.dismiss();
                        break;
                    case 6:
                        select_class.setText("7");
                        dialog.dismiss();
                        break;
                    case 7:
                        select_class.setText("8");
                        dialog.dismiss();
                        break;
                    case 8:
                        select_class.setText("9");
                        dialog.dismiss();
                        break;
                    case 9:
                        select_class.setText("10");
                        dialog.dismiss();
                        break;
                    case 10:
                        select_class.setText("11");
                        dialog.dismiss();
                        break;
                    case 11:
                        select_class.setText("12");
                        dialog.dismiss();
                        break;


                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }

}
