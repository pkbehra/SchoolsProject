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
import centriotech.schoolmanagment.WelcomeActivity;

public class PostHoliday extends Fragment {

    EditText et_post;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/Transport/notification.php";
    Button post_submit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.postholiday,container,false);
        et_post=view.findViewById(R.id.post_holiday);
        post_submit=view.findViewById(R.id.post_submit);
        post_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_post.getText().toString().length()>0){
                    postHoliday(et_post.getText().toString());

                }else{
                    Toast.makeText(getActivity(), "Please Enter Text", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    public void postHoliday(final String Message) {
        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                if (result.trim().equals("data inserted")) {

//                    AdminLogin adminLogin = new AdminLogin();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.frame, adminLogin).commit();
                    Intent intent=new Intent(getActivity(), WelcomeActivity.class);
                    startActivity(intent);

                    Toast.makeText(getActivity(), "Post Holiday Successful", Toast.LENGTH_SHORT).show();


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
                params.put("message", Message);

                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);
    }


//    public void postHoliday(final String Message){
//        mStringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String result) {
//                String kept = result.substring( 0, result.indexOf(","));
//                if (kept.trim().equals("file Added")){
//                    Toast.makeText(getActivity(), "Holiday Sent  Successfully", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getActivity(), WelcomeActivity.class));
////
//                }
//
//                if (result.equals("data not inserted")) {
//
//
//                    Toast.makeText(getActivity(), "Registration Failed " + "please try again", Toast.LENGTH_LONG).show();
//
//                }
//                if (result.equals("User Already Exists")) {
//
//
//                    Toast.makeText(getActivity(),  "User Already Exists", Toast.LENGTH_LONG).show();
//
//                }
//                if (result.equals("exception") || result.equals("unsuccessful")) {
//
//                    Toast.makeText(getActivity(), "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("Tag","Error :" + error.toString());
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams()
//            {
//
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("message", Message);
//
//                return params;
//            }
//        };
//        mRequestQueue = Volley.newRequestQueue(getActivity());
//        mRequestQueue.add(mStringRequest);
//    }

}

