package centriotech.schoolmanagment.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;

public class AdminLogin extends Fragment {

    SharedPreferenceConfig sharedPreferenceConfig;
    CardView view_attendance, view_home, view_event, view_teacher, view_studet, feeUpdate, groupmessage, message, view_feedback,
            add_teacher, add_student, view_transport, download, view_fee, addTransport, postholiday;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminloginlayout, container, false);
        view_attendance = (CardView) view.findViewById(R.id.a1);
        sharedPreferenceConfig = new SharedPreferenceConfig(getActivity());
        view_home = (CardView) view.findViewById(R.id.a2);
        view_event = (CardView) view.findViewById(R.id.a3);
        view_teacher = (CardView) view.findViewById(R.id.a4);
        view_studet = (CardView) view.findViewById(R.id.a5);
        feeUpdate = (CardView) view.findViewById(R.id.a6);
        view_feedback = (CardView) view.findViewById(R.id.a9);
        add_teacher = (CardView) view.findViewById(R.id.a10);
        add_student = (CardView) view.findViewById(R.id.a11);
        view_transport = (CardView) view.findViewById(R.id.a12);
        download = (CardView) view.findViewById(R.id.a13);
        view_fee = (CardView) view.findViewById(R.id.a14);
        addTransport = (CardView) view.findViewById(R.id.a15);
        postholiday = (CardView) view.findViewById(R.id.a16);

        addTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                AddTransport addTransport = new AddTransport();
                fm.beginTransaction().replace(R.id.frame, addTransport).addToBackStack("addtrasnport").commit();


            }
        });
        postholiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                PostHoliday addTransport = new PostHoliday();
                fm.beginTransaction().replace(R.id.frame, addTransport).addToBackStack("postholiday").commit();


            }
        });
        view_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewAttendance");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("viewAttendance").commit();

            }
        });

        view_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewHome");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("viewhome").commit();
            }
        });

        view_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewEvent");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("viewevent").commit();
            }
        });


        view_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                AdminViewTeacher viewTeacher = new AdminViewTeacher();
                fm.beginTransaction().replace(R.id.frame, viewTeacher).addToBackStack("viewteacher").commit();

            }
        });

        view_studet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewStudent");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("viewstudent").commit();

            }
        });
        feeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("PostFee");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("postfee").commit();
            }
        });

        view_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewFeed");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("viewfeed").commit();

            }
        });


        add_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                AdminAddTeacher viewTransportDetails = new AdminAddTeacher();
                fm.beginTransaction().replace(R.id.frame, viewTransportDetails).addToBackStack("addteacher").commit();
            }
        });

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                AdminAddStudent postEvent = new AdminAddStudent();
                fm.beginTransaction().replace(R.id.frame, postEvent).addToBackStack("addstudent").commit();
            }
        });

        view_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminViewTransport adminViewTransport = new AdminViewTransport();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, adminViewTransport).addToBackStack("viewtransprt").commit();


            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager fm = getFragmentManager();
//                Download viewFee = new Download();
//                fm.beginTransaction().replace(R.id.frame, viewFee).addToBackStack("download").commit();
//
            }
        });
        view_fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceConfig.putFrag("viewFee");
                SelectClassDivision selectClassDivision = new SelectClassDivision();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame, selectClassDivision).addToBackStack("viewfee").commit();

            }
        });


        return view;
    }

}
