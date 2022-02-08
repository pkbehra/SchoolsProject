package centriotech.schoolmanagment.ui;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import centriotech.schoolmanagment.Adapter.AdminFeeAdapter;
import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;

public class AdminViewFee extends Fragment {
    RecyclerView recyclerView;
    List<String> Title;
    List<String> Category;
    List<String> Description;
    List<Bitmap> Thumbnail;
    List<String> Data;
    List<String> Share;
    List<String> ID;
    List<Bitmap> Image;
    List<String> ImageSplit;
    String imagename = "";
    SharedPreferenceConfig sharedPreferenceConfig;
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 10000;
    String result = "";
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String HttpUrl = "https://orapune.com/API_TEST/Fees/ViewFee.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminviewfeelayout, container, false);

        Title = new ArrayList<>();
        Data = new ArrayList<>();
        Description = new ArrayList<>();
        Thumbnail = new ArrayList<>();
        ImageSplit = new ArrayList<>();
        Image = new ArrayList<>();
        Share = new ArrayList<>();
        ID = new ArrayList<>();
        Title = new ArrayList<>();
        Data = new ArrayList<>();
        Description = new ArrayList<>();
        Thumbnail = new ArrayList<>();
        ImageSplit = new ArrayList<>();
        Image = new ArrayList<>();
        Share = new ArrayList<>();
        ID = new ArrayList<>();
        sharedPreferenceConfig = new SharedPreferenceConfig(getActivity());
        String classes = sharedPreferenceConfig.getClass("class");
        String division = sharedPreferenceConfig.getDivision("division");
        if (sharedPreferenceConfig.getGR("GR") == null) {
            viewFees(classes, division, "");

        } else {

            String feed = sharedPreferenceConfig.getGR("GR");
            viewFees("", "", feed);
        }

        recyclerView = view.findViewById(R.id.Ferecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        return view;


    }

    public void viewFees(final String Student_class, final String Student_div, final String GRNO) {


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

                                    Data.add(car.get("image").toString());
                                    Title.add(car.get("date").toString());
                                    Description.add(car.get("message").toString());
                                    ID.add(car.get("id").toString());


                                }

                                for (int i = 0; i < Data.size(); i++) {
//                       GetImage getImage=new GetImage();
//                       getImage.execute(Data.get(i));
                                    imagename = imagename + Data.get(i) + ",";


                                }
                                GetImage getImage = new GetImage();
                                getImage.execute(imagename);


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
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                params.put("class", Student_class);
                params.put("division", Student_div);
                params.put("grNo", GRNO);
                return params;
            }

        };

        // Creating RequestQueue.
        requestQueue = Volley.newRequestQueue(getActivity());

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);


    }


    class GetImage extends AsyncTask<String, Void, List<Bitmap>> {
        ProgressDialog pdLoading = new ProgressDialog(getActivity());
        String id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();


        }

        @Override
        protected void onPostExecute(final List<Bitmap> b) {
            pdLoading.dismiss();
            AdminFeeAdapter adminFeeAdapter = new AdminFeeAdapter(Title, Description, b);
            recyclerView.setAdapter(adminFeeAdapter);

            adminFeeAdapter.setOnItemClickListener(new AdminFeeAdapter.OnItemClickListenerSave() {
                @Override
                public void onItemClickSave(int position) {

//                    String time = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
//
//                    File path = Environment.getExternalStorageDirectory();
//                    File dir = new File(path + "/DCIM");
//                    dir.mkdir();
//                    String imagename = time + ".PNG";
//                    File file = new File(dir, imagename);
//                    OutputStream out;
//
//                    try {
//
//                        out = new FileOutputStream(file);
//                        b.get(position).compress(Bitmap.CompressFormat.PNG, 100, out);
//                        out.flush();
//                        out.close();
//
//                        Toast.makeText(getActivity(), "Image Saved", Toast.LENGTH_SHORT).show();
//
//                    } catch (Exception e) {
//
//
//                        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
               }
            });


        }

        @Override
        protected List<Bitmap> doInBackground(String... params) {
            String name = params[0];
            List<String> elephantList = Arrays.asList(name.split(",[ ]*"));
            ImageSplit.clear();
            for (int i = 0; i < elephantList.size(); i++) {
                if (elephantList.get(i).equals("null")) {
                } else {
                    ImageSplit.add(elephantList.get(i));

                    // Toast.makeText(getActivity(), ""+elephantList.get(i), Toast.LENGTH_SHORT).show();
                }

            }
            for (int i = 0; i < ImageSplit.size(); i++) {
                String add = "https://orapune.com/API_TEST/Fees/images/" + ImageSplit.get(i);
                URL url = null;
                Bitmap image = null;
                try {
                    url = new URL(add);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    Image.add(image);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return Image;
        }
    }
}



