package centriotech.schoolmanagment.Adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import centriotech.schoolmanagment.R;

public class AdminViewHomeWorkAdapter extends RecyclerView.Adapter<AdminViewHomeWorkAdapter.ViewHolder>{

    List<String> FTitle;
    List<String> FSubject;
    List<Bitmap> FThumbnail;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener=listener;
    }

    public AdminViewHomeWorkAdapter(List<String> FTitle, List<String> FSubject, List<Bitmap> FThumbnail) {
        this.FTitle=FTitle;
        this.FSubject=FSubject;
        this.FThumbnail=FThumbnail;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.adminhomeworkadapter,parent,false);

        return new  ViewHolder(v,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.Ftv_book_title.setText(FTitle.get(i));
        holder.Fmessage.setText(FSubject.get(i));
        holder.Fimg_book_thumbnail.setImageBitmap(FThumbnail.get(i));
    }




    @Override
    public int getItemCount() {
        return FTitle.size();
    }





    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Ftv_book_title,Fmessage;
        ImageView Fimg_book_thumbnail;
        CardView cardView ;
        public ViewHolder(@NonNull View view, final OnItemClickListener listener) {
            super(view);
            Ftv_book_title = (TextView) itemView.findViewById(R.id.Fbook_title_id) ;
            Fmessage = (TextView) itemView.findViewById(R.id.Fmessage) ;
            Fimg_book_thumbnail = (ImageView) itemView.findViewById(R.id.Fbook_img_id);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int postion=getAdapterPosition();
                        if(postion!=RecyclerView.NO_POSITION){
                            listener.onItemClick(postion);

                        }
                    }

                }
            });




        }
    }



}

