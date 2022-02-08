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

public class AdminFeeAdapter extends RecyclerView.Adapter<AdminFeeAdapter.ViewHolder>{

    List<String> Title;
    List<String> Description;
    List<Bitmap> Thumbnail;
    private OnItemClickListenerSave onItemClickListenerSave;
    public interface OnItemClickListenerSave{
        void onItemClickSave(int position);
    }
    public void setOnItemClickListener(OnItemClickListenerSave listener){
        onItemClickListenerSave= (OnItemClickListenerSave) listener;
    }

    public AdminFeeAdapter(List<String> Title, List<String> Description, List<Bitmap> Thumbnail) {
        this.Title=Title;
        this.Description=Description;
        this.Thumbnail=Thumbnail;


    }

    @Override
    public AdminFeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.adminfeeadapter,parent,false);
        return new AdminFeeAdapter.ViewHolder(v,  onItemClickListenerSave);
    }

    @Override
    public void onBindViewHolder(AdminFeeAdapter.ViewHolder holder, int i) {
        holder.Fetv_book_title.setText(Title.get(i));
        holder.Femessage.setText(Description.get(i));
        holder.Feimg_book_thumbnail.setImageBitmap(Thumbnail.get(i));
    }




    @Override
    public int getItemCount() {
        return Title.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Fetv_book_title,Femessage;
        ImageView Feimg_book_thumbnail;
        CardView cardView ;
        public ViewHolder(@NonNull View view, final OnItemClickListenerSave listener) {
            super(view);
            Fetv_book_title = (TextView) itemView.findViewById(R.id.Febook_title_id) ;
            Femessage = (TextView) itemView.findViewById(R.id.Femessage) ;
            Feimg_book_thumbnail = (ImageView) itemView.findViewById(R.id.Febook_img_id);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int postion=getAdapterPosition();
                        if(postion!=RecyclerView.NO_POSITION){
                            listener.onItemClickSave(postion);

                        }
                    }

                }
            });



        }
    }



}