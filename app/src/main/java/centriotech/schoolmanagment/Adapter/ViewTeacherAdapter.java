package centriotech.schoolmanagment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import centriotech.schoolmanagment.R;

public class ViewTeacherAdapter  extends RecyclerView.Adapter<ViewTeacherAdapter.ViewHolder>{
    List<String> ID;
    List<String> Name;
    List<String> Classes;
    List<String> Divisions;
    List<String>Subjects;




    public ViewTeacherAdapter(List<String> ID, List<String> Name, List<String> Classes, List<String> Divisions, List<String>Subjects) {
        this.Name=Name;
        this.Classes=Classes;
        this.Divisions=Divisions;
        this.Subjects=Subjects;
        this.ID=ID;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.adminviewteacheradapterlayout,parent,false);

        return new  ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.Name.setText(Name.get(i));
        holder.ID.setText(ID.get(i));
        holder.classes.setText(Classes.get(i));
        holder.divisions.setText(Divisions.get(i));
        holder.subjects.setText(Subjects.get(i));
    }




    @Override
    public int getItemCount() {
        return ID.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name,ID,classes,divisions,subjects;

        public ViewHolder(@NonNull View view) {
            super(view);
            Name =view.findViewById(R.id.teachername);
            ID=view.findViewById(R.id.ID);
            classes =view.findViewById(R.id.classes);
            divisions=view.findViewById(R.id.division);
            subjects=view.findViewById(R.id.subjects);

        }
    }



}



