package centriotech.schoolmanagment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import centriotech.schoolmanagment.R;

public class AdminViewStudentAdapter extends RecyclerView.Adapter<AdminViewStudentAdapter.ViewHolder> {
    List<String> RollNo;
    List<String> StudentName;
    List<String> StudentClass;
    List<String> Division;
    List<String> GrNo;
//        private OnItemClickListenersimilar onItemClickListenersimilar;
//        public interface OnItemClickListenersimilar{
//            void onItemClick(int position);
//            void onFeesClicked(int position);
//            void onFeedBackClicked(int position);
//        }
//        public void setOnItemClickListener(OnItemClickListenersimilar listener){
//            onItemClickListenersimilar=listener;
//        }


    public AdminViewStudentAdapter(List<String> StudentName, List<String> RollNo,List<String> StudentClass,List<String> Division,List<String> GrNo) {

        this.StudentName = StudentName;
        this.RollNo = RollNo;
            this.StudentClass=StudentClass;
            this.Division=Division;
            this.GrNo=GrNo;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.viewstudentadapterlayout, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.StudentName.setText(StudentName.get(i));
        holder.RollNo.setText(RollNo.get(i));
            holder.StudentClass.setText(StudentClass.get(i));
            holder.Division.setText(Division.get(i));
            holder.GrNo.setText(GrNo.get(i));
    }


    @Override
    public int getItemCount() {
        return StudentName.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView StudentName, RollNo, StudentClass, Division, GrNo;

        Button fees, feedbacks;

        public ViewHolder(@NonNull View view) {
            super(view);
            StudentName = view.findViewById(R.id.studentname);
            RollNo = view.findViewById(R.id.student_rollno);
                StudentClass =view.findViewById(R.id.student_class);
            GrNo=view.findViewById(R.id.student_grNo);
            Division=view.findViewById(R.id.student_division);
//                fees=view.findViewById(R.id.fee);
//                feedbacks=view.findViewById(R.id.feedback);


//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(listener!=null){
//                            int postion=getAdapterPosition();
//                            if(postion!=RecyclerView.NO_POSITION){
//                                listener.onItemClick(postion);
//
//                            }
//                        }
//
//                    }
//                });
//                fees.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        if(listener!=null){
//
//                            int postion=getAdapterPosition();
//                            if(postion!=RecyclerView.NO_POSITION){
//                                listener.onFeesClicked(postion);
//
//                            }
//                        }
//
//                    }
//                });
//                feedbacks.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        if(listener!=null){
//                            int postion=getAdapterPosition();
//                            if(postion!=RecyclerView.NO_POSITION){
//                                listener.onFeedBackClicked(postion);
//
//
//
//                            }
//                        }
//
//                    }
//                });


        }
    }


}




