package centriotech.schoolmanagment.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

import centriotech.schoolmanagment.Adapter.ViewTeacherAdapter;
import centriotech.schoolmanagment.R;

public class AdminViewTeacher extends Fragment {

    RecyclerView recyclerView;
    List<String> Name;
    List<String> ID;
    List<String> Classes;
    List<String> Divisions;
    List<String> Subjects;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String HttpUrl = "https://orapune.com/API_TEST/ViewTeacher.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminviewteacherlayout, container, false);

        Name = new ArrayList<>();
        ID = new ArrayList<>();
        Classes = new ArrayList<>();
        Divisions = new ArrayList<>();
        Subjects = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        viewTeacher();
        return view;
    }

    public void viewTeacher() {
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
                                    Name.add(car.get("name").toString());
                                    ID.add(car.get("teacherid").toString());
                                    Classes.add(car.get("class").toString());
                                    Divisions.add(car.get("division").toString());
                                    Subjects.add(car.get("subject").toString());


                                }

                                ViewTeacherAdapter viewTeacherAdapter = new ViewTeacherAdapter(ID, Name, Classes, Divisions, Subjects);
                                recyclerView.setAdapter(viewTeacherAdapter);



                            } else {
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


        };

        // Creating RequestQueue.
        requestQueue = Volley.newRequestQueue(getActivity());

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);


    }
}