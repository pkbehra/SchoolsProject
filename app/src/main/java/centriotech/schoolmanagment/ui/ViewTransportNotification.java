package centriotech.schoolmanagment.ui;

import android.os.Bundle;
import android.util.Log;
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

import centriotech.schoolmanagment.Adapter.ViewHoliday;
import centriotech.schoolmanagment.Adapter.ViewTeacherAdapter;
import centriotech.schoolmanagment.R;

public class ViewTransportNotification extends Fragment {

    RecyclerView NrecyclerView;
    List<String> NMessage;
    List<String> NDate;

    RequestQueue requestQueue;
    StringRequest stringRequest;
    private String HttpUrl = "https://orapune.com/API_TEST/Transport/ViewTransportNotification.php";

    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 10000;
    String result = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewholiday, container, false);
        NMessage = new ArrayList<>();
        NDate = new ArrayList<>();
        NrecyclerView = view.findViewById(R.id.Nrecycle);
        NrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NrecyclerView.setHasFixedSize(true);
        showNotification();
        return view;
    }

    private void showNotification() {
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
                                    NMessage.add(car.get("message").toString());
                                    NDate.add(car.get("date").toString());


                                }

                                ViewHoliday viewHoliday = new ViewHoliday(NMessage, NDate);
                                NrecyclerView.setAdapter(viewHoliday);


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

//    public void showNotification() {
//        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String result) {
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    int success = jsonObject.getInt("success");
//                    if (success == 1) {
//                        JSONArray cars = jsonObject.getJSONArray("cars");
//
//                        for (int i = 0; i < cars.length(); i++) {
//                            JSONObject car = cars.getJSONObject(i);
//                            NMessage.add(car.get("message").toString());
//                            NDate.add(car.get("date").toString());
//
//
//                        }
//
//                        ViewHoliday viewHoliday = new ViewHoliday(NMessage,NDate);
//                        NrecyclerView.setAdapter(viewHoliday);
//
//
//                    } else {
//                        Toast.makeText(getActivity(), "no data found ", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("Tag", "Error :" + error.toString());
//
//            }
//        });
//
//        mRequestQueue = Volley.newRequestQueue(getActivity());
//        mRequestQueue.add(mStringRequest);
//    }

//}

