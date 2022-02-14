package centriotech.schoolmanagment.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import centriotech.schoolmanagment.R;

public class ViewHoliday extends RecyclerView.Adapter<ViewHoliday.ViewHolder> {

    List<String> NMessage;
    List<String> NDate;


    public ViewHoliday(List<String> NMessage, List<String> NDate) {
        this.NMessage=NMessage;
        this.NDate=NDate;


    }
    @NonNull
    @Override
    public ViewHoliday.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.holidaylayoutadapter, parent, false);

        return new ViewHoliday.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoliday.ViewHolder holder, int i) {

        holder.Nmessage.setText(NMessage.get(i));
        holder.Ndate.setText(NDate.get(i));

    }

    @Override
    public int getItemCount() {
        return NDate.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nmessage, Ndate;

        public ViewHolder(@NonNull View view) {
            super(view);
            Ndate = view.findViewById(R.id.Nhdate);
            Nmessage = view.findViewById(R.id.Nholidaymessage);

        }
    }


}

