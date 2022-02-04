package centriotech.schoolmanagment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import centriotech.schoolmanagment.R;

public class AdminViewTransportAdapter extends RecyclerView.Adapter<AdminViewTransportAdapter.ViewHolder> {

    List<String> Name;
    List<String> MobileNo;
    List<String> VehicleNo;
    List<String> LicenceNo;


    public AdminViewTransportAdapter(List<String> Name, List<String> MobileNo, List<String> VehicleNo, List<String> LicenceNo) {
        this.Name = Name;
        this.MobileNo = MobileNo;
        this.VehicleNo = VehicleNo;
        this.LicenceNo = LicenceNo;

    }

    @Override
    public AdminViewTransportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adminviewtransport, parent, false);

        return new AdminViewTransportAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdminViewTransportAdapter.ViewHolder holder, int i) {
        holder.Name.setText(Name.get(i));
        holder.MobileNo.setText(MobileNo.get(i));
        holder.VehicleNo.setText(VehicleNo.get(i));
        holder.LicenceNo.setText(LicenceNo.get(i));
    }


    @Override
    public int getItemCount() {
        return Name.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, MobileNo, VehicleNo, LicenceNo;

        public ViewHolder(@NonNull View view) {
            super(view);
            Name = view.findViewById(R.id.transport_name);
            MobileNo = view.findViewById(R.id.trans_mobile);
            VehicleNo = view.findViewById(R.id.trans_vechno);
            LicenceNo = view.findViewById(R.id.trans_licno);

        }
    }


}

