package centriotech.schoolmanagment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import centriotech.schoolmanagment.LoginActivity;
import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;
import de.hdodenhof.circleimageview.CircleImageView;

public class ParentProfile extends Fragment {

    RelativeLayout logout;
    CircleImageView parent_imageButton;
    SharedPreferenceConfig sharedPreferenceConfig;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parentprofile, container, false);

        parent_imageButton=view.findViewById(R.id.parents_editprofile);
        logout=view.findViewById(R.id.parent_rlLogOutnew);

        sharedPreferenceConfig=new SharedPreferenceConfig(getActivity());
        String Number=  sharedPreferenceConfig.getnum("user");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceConfig.writeLogoutStatus(true);
                sharedPreferenceConfig.putnum("");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                // finish();

                Toast.makeText(getActivity(), "Logout sucessfull", Toast.LENGTH_SHORT).show();


            }
        });


        parent_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Parent profil edit", Toast.LENGTH_SHORT).show();

            }
        });
//        editprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ParentProfileUpdate parentProfileUpdate = new ParentProfileUpdate();
////                FragmentManager fmmm = getFragmentManager();
////                fmmm.beginTransaction().replace(R.id.frame, parentProfileUpdate).addToBackStack("Parent").commit();
//
//            }
//        });
        return view;
    }
}