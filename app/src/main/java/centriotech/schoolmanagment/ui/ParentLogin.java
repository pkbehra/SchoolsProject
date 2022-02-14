package centriotech.schoolmanagment.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;

public class ParentLogin extends Fragment {

    CardView viewAttendance, viewhomework, viewevent, viewfeedback, viewfee,viewAnnouncement;
    SharedPreferenceConfig sharedPreferenceConfig;
    String number = "", classes = "", division = "", ImageString = "";
    ImageView imageView;
    String imageName;
    Bitmap bitmap;
    ByteArrayOutputStream byteArrayOutputStream;

    byte[] byteArray;

    String ConvertImage;
    private RequestQueue mRequestQueue2;
    private StringRequest mStringRequest2;
    private String url2 = "https://www.naukarikatta.com/School/UpdateImage/UpdateImage.php";
    private static final int RESULT_CANCELED = 0;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    CharSequence[] items = {"Take Photo", "Choose from Library",
            "Cancel"};
    private int GALLERY = 1, CAMERA = 2;
    TextView tv_Roll,tv_class,tv_name;
    String full_name, mother_name, std_id, dob, religion, cast, ContactNo, alt_cont, Email, address, addhar, gender, physical_dis, doa, catogry, house, st_class, st_division, roll_no, blood_group, grNo;

    private String url = "https://orapune.com/API_TEST/ParentProfile.php";
    private RequestQueue mRequestQueue1;
    private StringRequest mStringRequest1;
    private String url1 = "https://www.naukarikatta.com/School/UpdateImage/displayImage.php";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.parentloginlayout,container,false);
        viewAttendance = view.findViewById(R.id.p1);
        viewhomework = view.findViewById(R.id.p2);
        viewevent = view.findViewById(R.id.p3);
        viewfeedback = view.findViewById(R.id.p4);
        viewfee = view.findViewById(R.id.p5);
        imageView = view.findViewById(R.id.profile_image);
        tv_class = view.findViewById(R.id.myclass);
        tv_Roll = view.findViewById(R.id.myrollno);
        tv_name=view.findViewById(R.id.studentName);
        viewAnnouncement=view.findViewById(R.id.p6);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();

            }
        });
        sharedPreferenceConfig = new SharedPreferenceConfig(getActivity());
//        String Number = sharedPreferenceConfig.getnum("user");
//
//        Toast.makeText(getActivity(), "Number"+Number, Toast.LENGTH_SHORT).show();

        myProfile(sharedPreferenceConfig.getnum("user"));
        if (sharedPreferenceConfig.getImage("image")!=null){
            String decode=sharedPreferenceConfig.getImage("image");
            byte[] imageBytes = Base64.decode(decode.getBytes(),Base64.DEFAULT);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length));

        }

        viewAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ViewAnnouncements viewAnnouncement = new ViewAnnouncements();
//                FragmentManager fm = getFragmentManager();
//                fm.beginTransaction().replace(R.id.frame, viewAnnouncement).addToBackStack("viewAnnouncement").commit();
//
            }
        });
        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ViewParentAttendance adminAttendance = new ViewParentAttendance();
//                FragmentManager fm = getFragmentManager();
//                fm.beginTransaction().replace(R.id.frame, adminAttendance).addToBackStack("viewAttendanceP").commit();
//
            }
        });
        viewhomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminHomeWork adminHomeWork = new AdminHomeWork();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, adminHomeWork).addToBackStack("viewHome").commit();

            }
        });
        viewevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminEvent adminEvent = new AdminEvent();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, adminEvent).addToBackStack("adminevent").commit();


            }
        });
        viewfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdminFeedBack adminFeedBack = new AdminFeedBack();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, adminFeedBack).addToBackStack("viewfeedback").commit();
            }
        });
        viewfee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AdminViewFee adminViewFee = new AdminViewFee();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, adminViewFee).addToBackStack("viewfee").commit();


            }
        });

        return view;
    }



    public void myProfile(final String ContactNo) {
        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray cars = jsonObject.getJSONArray("cars");

                        for (int i = 0; i < cars.length(); i++) {
                            JSONObject car = cars.getJSONObject(i);
                            blood_group = (car.get("bloodgroup").toString());
                            grNo = (car.get("grNo").toString());
                            full_name = (car.get("fullname").toString());
                            mother_name = (car.get("mothername").toString());
                            std_id = (car.get("studentid").toString());
                            dob = (car.get("dateofbirth").toString());
                            religion = (car.get("religion").toString());
                            cast = (car.get("caste").toString());
                            alt_cont = (car.get("alternatecontactno").toString());
                            Email = (car.get("email").toString());
                            address = (car.get("address").toString());
                            addhar = (car.get("aadharno").toString());
                            gender = (car.get("gender").toString());
                            physical_dis = (car.get("physicallydisable").toString());
                            doa = (car.get("dateofadmission").toString());
                            catogry = (car.get("category").toString());
                            house = (car.get("House").toString());
                            st_class = (car.get("class").toString());
                            st_division = (car.get("division").toString());
                            roll_no = (car.get("rollno").toString());

                            sharedPreferenceConfig.putClass(car.get("class").toString());
                            sharedPreferenceConfig.putDivision(car.get("division").toString());
                            sharedPreferenceConfig.putRoll(car.get("rollno").toString());


                        }
//                        if (sharedPreferenceConfig.getImage("image")==null){
//                            displayImage(grNo);
//                        }
                        tv_class.setText("Class:"+st_class+"-"+st_division);
                        tv_Roll.setText("Roll No:"+roll_no);
                        tv_name.setText(full_name);


                    } else {
                        Toast.makeText(getActivity(), "no data found ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("mobileno", ContactNo);

                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);
    }
    public void UploadImage(final String grNo, final String Image, final String name) {

        mStringRequest2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {


                if (result.trim().equals("Your Image Has Been Uploaded.")) {
                    Toast.makeText(getActivity(), "image uploaded ", Toast.LENGTH_SHORT).show();

                    displayImage(grNo);
                    // startActivity(new Intent(getActivity(), DashBoard.class));


                }

                if (result.trim().equalsIgnoreCase("Please Try Again")) {
                    Toast.makeText(getActivity(), " Please Try Again", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(getActivity(), DashBoard.class));
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
                params.put("grNo", grNo);
                params.put("image", Image);
                params.put("name", name);
                return params;
            }
        };
        mRequestQueue2 = Volley.newRequestQueue(getActivity());
        mRequestQueue2.add(mStringRequest2);
    }
    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Gallery",
                "Camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), GALLERY);

    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);

                    UploadImage(grNo,UploadImageToServer(bitmap),full_name);
                    imageView.setImageBitmap(bitmap);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 180);
                    imageView.setLayoutParams(layoutParams);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            UploadImage(grNo,UploadImageToServer(bitmap),full_name);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 180);
            imageView.setLayoutParams(layoutParams);




        }
    }


    public void displayImage(final String grNo) {

        mStringRequest1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                                    JSONArray cars = jsonObject.getJSONArray("cars");

                        for (int i = 0; i < cars.length(); i++) {
                            JSONObject car = cars.getJSONObject(i);
                            imageName = (String) car.get("image");

                        }

                        GetImage getImage = new GetImage();
                        getImage.execute(imageName);

                    } else {
                        Toast.makeText(getActivity(), "no data found ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
                params.put("grNo", grNo);


                return params;
            }
        };
        mRequestQueue1 = Volley.newRequestQueue(getActivity());
        mRequestQueue1.add(mStringRequest1);
    }

    public String UploadImageToServer(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;


    }
    class GetImage extends AsyncTask<String, Void, Bitmap> {
        ProgressDialog loading;
        String id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(getActivity(), "Loading...", null, true, true);
        }

        @Override
        protected void onPostExecute(Bitmap b) {
            super.onPostExecute(b);
            loading.dismiss();
            if(b!=null){
                String image= UploadImageToServer(b);
                sharedPreferenceConfig.putImage(image);
                imageView.setImageBitmap(b);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 280);
                imageView.setLayoutParams(layoutParams);
            }

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String name = params[0];
            String add = "https://www.naukarikatta.com/School/UpdateImage/images/"+name;
            URL url = null;
            Bitmap image = null;
            try {
                url = new URL(add);
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }
    }



}
