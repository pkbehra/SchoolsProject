package centriotech.schoolmanagment.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import centriotech.schoolmanagment.Adapter.AdminViewStudentAdapter;
import centriotech.schoolmanagment.Adapter.ViewTeacherAdapter;
import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;

public class AdminViewStudent extends Fragment {
    SharedPreferenceConfig sharedPreferenceConfig;
    RecyclerView recyclerView;
    List<String> StudentName;
    List<String> RollNo;
    List<String> StudentClass;
    List<String> GrNo;
    List<String> MobileNo;
    List<String> shareData;
    List<String> shareData1;
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 10000;
    String result = "";
    List<String> Division;

    RequestQueue requestQueue;
    StringRequest stringRequest;
    String HttpUrl = "https://orapune.com/API_TEST/ViewStudent.php";
    SwipeRefreshLayout swipeRefreshLayout;

    String classes = "", division = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminviewstudentlayout, container, false);

        sharedPreferenceConfig = new SharedPreferenceConfig(getActivity());
        classes = sharedPreferenceConfig.getClass("class");
        division = sharedPreferenceConfig.getDivision("division");
        swipeRefreshLayout = view.findViewById(R.id.swipe);


        StudentName = new ArrayList<>();
        RollNo = new ArrayList<>();

        MobileNo = new ArrayList<>();
        shareData = new ArrayList<>();
        shareData1 = new ArrayList<>();
        GrNo = new ArrayList<>();
        StudentClass = new ArrayList<>();
        Division = new ArrayList<>();
        recyclerView = view.findViewById(R.id.student_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        viewStudent(classes, division);

        return view;
    }


    public void viewStudent(final String Student_class, final String Student_div) {

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
                                    StudentName.add(car.get("fullname").toString());
                                    RollNo.add(car.get("rollno").toString());
                                    StudentClass.add(car.get("class").toString());
                                    Division.add(car.get("division").toString());
//                                    MobileNo.add(car.get("contactno").toString());
                                    GrNo.add(car.get("grNo").toString());


                                }

                                AdminViewStudentAdapter adminViewStudentAdapter = new AdminViewStudentAdapter(StudentName, RollNo, StudentClass, Division, GrNo);
                                recyclerView.setAdapter(adminViewStudentAdapter);
                                // Toast.makeText(getActivity(), "Data"+StudentName, Toast.LENGTH_SHORT).show();

                            } else {

                                SelectClassDivision selectClassDivision = new SelectClassDivision();
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("attendance").commit();

                                Toast.makeText(getActivity(), "no data found ", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("class", Student_class);
                params.put("division", Student_div);

                return params;
            }

        };

        // Creating RequestQueue.
        requestQueue = Volley.newRequestQueue(getActivity());

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);


    }


}
