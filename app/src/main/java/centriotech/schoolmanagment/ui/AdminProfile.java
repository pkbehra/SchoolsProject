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

import centriotech.schoolmanagment.LoginActivity;
import centriotech.schoolmanagment.R;
import centriotech.schoolmanagment.Utility.SharedPreferenceConfig;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdminProfile extends Fragment {

    RelativeLayout logout;
    SharedPreferenceConfig sharedPreferenceConfig;
    CircleImageView admin_editprofile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminprofile, container, false);

        logout=view.findViewById(R.id.admin_rlLogOutnew);
        admin_editprofile=view.findViewById(R.id.admin_editprofile);

        sharedPreferenceConfig=new SharedPreferenceConfig(getActivity());
        String Number=  sharedPreferenceConfig.getnum("user");

        admin_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Edit AdminProfile", Toast.LENGTH_SHORT).show();
            }
        });

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

        return view;
    }
}