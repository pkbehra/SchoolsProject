package centriotech.schoolmanagment.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;

public class SelectClassDivision extends Fragment {

    String[] typeOfClass={"Select Class","1","2","3","4","5","6","7","8","9","10"};
    String[] typeOfDivision={"Select Division","A","B","C","D","E"};
    String select,get;
    String frag="";
    SharedPreferenceConfig sharedPreferenceConfig;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.selectclassdivision,container,false);

        sharedPreferenceConfig =new SharedPreferenceConfig(getActivity());
        frag  =  sharedPreferenceConfig.getFrag("fragment");

        Spinner spin_class =view.findViewById(R.id.spin_class);
        Spinner spin_div=view.findViewById(R.id.spin_div);
        Button next=view.findViewById(R.id.next);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, typeOfClass);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_class.setAdapter(arrayAdapter);
        spin_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select = parent.getItemAtPosition(position).toString();


            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, typeOfDivision);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_div.setAdapter(arrayAdapter1);
        spin_div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                get = parent.getItemAtPosition(position).toString();


            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (get.equals("Select Division") || select.equals("Select Class")){
                    Toast.makeText(getActivity(), "Please Select Class And Division", Toast.LENGTH_SHORT).show();
                }
                else{
                    sharedPreferenceConfig.putClass(select);
                    sharedPreferenceConfig.putDivision(get);

                    if(frag.equals("viewStudent")){
                        FragmentManager fm=getFragmentManager();
                        AdminViewStudent adminViewStudent=new AdminViewStudent();
                        fm.beginTransaction().replace(R.id.frame,adminViewStudent).addToBackStack("viewStudent").commit();
                    }else if (frag.equals("viewAttendance")){
                        FragmentManager fm=getFragmentManager();
                        AdminAttendance viewAttendance=new AdminAttendance();
                        fm.beginTransaction().replace(R.id.frame,viewAttendance).addToBackStack("viewAttendance").commit();
                    } else if (frag.equals("viewHome")){
                        FragmentManager fm=getFragmentManager();
                        AdminHomeWork viewHomeWork=new AdminHomeWork();
                        fm.beginTransaction().replace(R.id.frame,viewHomeWork).addToBackStack("viewHome").commit();
                    } else if (frag.equals("viewEvent")){
                        FragmentManager fm=getFragmentManager();
                        AdminEvent viewEvent = new AdminEvent();
                        fm.beginTransaction().replace(R.id.frame,viewEvent).addToBackStack("viewEvent").commit();
                    } else if (frag.equals("viewFeed")){
                        FragmentManager fm=getFragmentManager();
                        AdminFeedBack addStudent=new AdminFeedBack();
                        fm.beginTransaction().replace(R.id.frame,addStudent).addToBackStack("viewFeed").commit();

                    }else if (frag.equals("viewTransport")){
//                        FragmentManager fm=getFragmentManager();
//                        AdminViewTransport viewFee=new AdminViewTransport();
//                        fm.beginTransaction().replace(R.id.frame,viewFee).addToBackStack("viewTransport").commit();
//
                    }else if (frag.equals("viewFee")){
                        FragmentManager fm=getFragmentManager();
                        AdminViewFee adminViewFee=new AdminViewFee();
                        fm.beginTransaction().replace(R.id.frame,adminViewFee).addToBackStack("viewFee").commit();
                    }else if (frag.equals("Attendance")){
                        FragmentManager fm=getFragmentManager();
                        AdminDailyAttendance adminDailyAttendance=new AdminDailyAttendance();
                        fm.beginTransaction().replace(R.id.frame,adminDailyAttendance).addToBackStack("Attendace").commit();

                    }else if (frag.equals("Homework")){
                        FragmentManager fm=getFragmentManager();
                        DailyHomeWork dailyHomeWork=new DailyHomeWork();
                        fm.beginTransaction().replace(R.id.frame,dailyHomeWork).addToBackStack("homework").commit();
                    }else if (frag.equals("PostEvent")){
                        FragmentManager fm=getFragmentManager();
                        PostEvent postEvent=new PostEvent();
                        fm.beginTransaction().replace(R.id.frame,postEvent).addToBackStack("postevent").commit();
                    }else if (frag.equals("PostFeed")){
                        FragmentManager fm=getFragmentManager();
                        PostFeedBack postFeedBack=new PostFeedBack();
                        fm.beginTransaction().replace(R.id.frame,postFeedBack).addToBackStack("postfeed").commit();
                    }else if (frag.equals("PostFee")){
                        FragmentManager fm=getFragmentManager();
                        PostFeeUpdates postFeeUpdates=new PostFeeUpdates();
                        fm.beginTransaction().replace(R.id.frame,postFeeUpdates).addToBackStack("postfee").commit();
                    }else if (frag.equals("addAnnuncement")){
                        FragmentManager fm=getFragmentManager();
                        Addannouncement postFeeUpdates=new Addannouncement();
                        fm.beginTransaction().replace(R.id.frame,postFeeUpdates).addToBackStack("addAnnouncement").commit();
                    }


                }


            }


        });


        return view;
    }




}
