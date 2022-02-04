package centriotech.schoolmanagment.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;

public class TeacherLogin extends Fragment {

    CardView attendance,homework,eventdetails,feedback,addstudent,feeupdates,viewstudent,download,viewfee,viewAttendance,addAnnouncement;
    SharedPreferenceConfig sharedPreferenceConfig;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.teacherloginlaout,container,false);

        sharedPreferenceConfig =new SharedPreferenceConfig(getActivity());
        attendance=(CardView)view.findViewById(R.id.t1);
        homework=(CardView)view.findViewById(R.id.t2);
        eventdetails=(CardView)view.findViewById(R.id.t3);
        feedback=(CardView)view.findViewById(R.id.t4);
        addstudent=(CardView)view.findViewById(R.id.t5);
        feeupdates=(CardView)view.findViewById(R.id.t6);
        viewstudent=(CardView)view.findViewById(R.id.t9);
        download=(CardView)view.findViewById(R.id.t10);
        viewfee=(CardView)view.findViewById(R.id.t11);
        viewAttendance=(CardView)view.findViewById(R.id.t12);
        addAnnouncement=(CardView)view.findViewById(R.id.t13);


        addAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("addAnnuncement");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("addAnnouncement").commit();
            }
        });


        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewAttendance");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("viewAttendance").commit();

            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("Attendance");
                SelectClassDivision selectClassDivision=new SelectClassDivision();
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.frame,selectClassDivision).addToBackStack("attendance").commit();
            }
        });

        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("Homework");
                SelectClassDivision selectClassDivision=new SelectClassDivision();
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.frame,selectClassDivision).addToBackStack("homework").commit();
            }
        });

        eventdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("PostEvent");
                SelectClassDivision selectClassDivision=new SelectClassDivision();
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.frame,selectClassDivision).addToBackStack("posteent").commit();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("PostFeed");
                SelectClassDivision selectClassDivision=new SelectClassDivision();
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.frame,selectClassDivision).addToBackStack("postfeed").commit();
            }
        });


        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getFragmentManager();
                AdminAddStudent addStudent=new AdminAddStudent();
                fm.beginTransaction().replace(R.id.frame,addStudent).addToBackStack("addstudent").commit();
            }
        });

        feeupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("PostFee");
                SelectClassDivision selectClassDivision=new SelectClassDivision();
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.frame,selectClassDivision).addToBackStack("feeupdate").commit();
            }
        });



        viewstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewStudent");
                SelectClassDivision selectClassDivision=new SelectClassDivision();
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.frame,selectClassDivision).addToBackStack("viewstudent").commit();
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager fm=getFragmentManager();
//                Download download=new Download();
//                fm.beginTransaction().replace(R.id.frame,download).addToBackStack("download").commit();

            }
        });

        viewfee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewFee");
                SelectClassDivision selectClassDivision=new SelectClassDivision();
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.frame,selectClassDivision).addToBackStack("viewfee").commit();
            }
        });

        return view;
    }
}
