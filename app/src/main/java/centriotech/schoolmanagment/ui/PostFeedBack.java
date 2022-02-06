package centriotech.schoolmanagment.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;
import centriotech.schoolmanagment.WelcomeActivity;

public class PostFeedBack extends Fragment {
    CharSequence[] items = {"Take Photo", "Choose from Library",
            "Cancel"};
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    String result = "";
    Button feedbtn_submit;

    final int min = 20;
    final int max = 80;
    int random;
    ImageView imageView;
    EditText et_post, et_grno;
    List<String> getData;
    Bitmap bitmapOrg;
    private static final int RESULT_CANCELED = 0;


    Button send;

    Bitmap bitmap;

    String ImageTag = "image_tag";

    String ImageName = "image_data";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://orapune.com/API_TEST/Feedback/postfeedback.php";

    ProgressDialog progressDialog;

    ByteArrayOutputStream byteArrayOutputStream;

    byte[] byteArray;

    String ConvertImage;

    String GetImageNameFromEditText;

    HttpURLConnection httpURLConnection;


    OutputStream outputStream;

    BufferedWriter bufferedWriter;

    int RC;

    BufferedReader bufferedReader;

    StringBuilder stringBuilder;
    String classes = "", division = "";
    boolean check = true;
    private int GALLERY = 1, CAMERA = 2;
    private RequestQueue mRequestQueue1;
    private StringRequest mStringRequest1;
    private String url1 = "https://www.naukarikatta.com/School/checkGrNo.php";
    SharedPreferenceConfig sharedPreferenceConfig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.postfeedbacklayout, container, false);

        imageView = view.findViewById(R.id.postfeedback_camera);
        feedbtn_submit = view.findViewById(R.id.postfeedback_submit);
        et_post = view.findViewById(R.id.postfeedback_homework);
        et_grno = view.findViewById(R.id.postfeedback_grNo);
        getData = new ArrayList<>();
        sharedPreferenceConfig = new SharedPreferenceConfig(getActivity());

//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            getData = bundle.getStringArrayList("message");
//            final String GrNo = getData.get(0);
//            classes = getData.get(1);
//            division = getData.get(2);
//            et_grno.setText(GrNo);
//
//        } else {
            classes = sharedPreferenceConfig.getClass("class");
            division = sharedPreferenceConfig.getDivision("division");
     //   }

        byteArrayOutputStream = new ByteArrayOutputStream();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPictureDialog();

            }
        });
        feedbtn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    UploadImageToServer();


            }
        });
        return view;
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

                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

        }
    }


    public void UploadImageToServer() {

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byteArray = byteArrayOutputStream.toByteArray();

        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);


        addFeedBack(classes, division, ConvertImage, et_post.getText().toString(), et_grno.getText().toString());

    }

    public void addFeedBack(final String Classes, final String Div, final String Image, final String MessageS, final String GrNo) {

        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {

                if (result.trim().equals("data inserted")) {

                    Intent intent=new Intent(getActivity(), WelcomeActivity.class);
                    startActivity(intent);

                    Toast.makeText(getActivity(), "Post Feedback Successful", Toast.LENGTH_SHORT).show();


                }

                if (result.trim().equals("data not inserted")) {


                    Toast.makeText(getActivity(), "Registration Failed " + "please try again", Toast.LENGTH_LONG).show();

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
                params.put("class", Classes);
                params.put("division", Div);
                params.put("image", Image);
                params.put("message", MessageS);
                params.put("grNo", GrNo);
                return params;
            }
        };
        mRequestQueue = Volley.newRequestQueue(getActivity());
        mRequestQueue.add(mStringRequest);
    }



}