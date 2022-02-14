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

public class TransportLogin extends Fragment {

    SharedPreferenceConfig sharedPreferenceConfig;
    CardView view_notification;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.transportloginlayout,container,false);

        view_notification = (CardView) view.findViewById(R.id.view_notification);
        view_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                ViewTransportNotification addTransport = new ViewTransportNotification();
                fm.beginTransaction().replace(R.id.frame, addTransport).addToBackStack("viewHoliday").commit();

            }
        });
        return view;
    }
}
