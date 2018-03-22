package ru.supplyphotos.gallery_fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.supplyphotos.supplyrxtest.R;

/**
 * @author Libgo on 21.03.2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{


    private List<String> imagePathList = new ArrayList<>();
    private Context context;

    public GalleryAdapter(Context context) {
        this.context = context;
    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateImageList(List<String> imagePathList){
        this.imagePathList.addAll(imagePathList);
        notifyDataSetChanged();

    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gallery_image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, int position) {

        Picasso.get().load(imagePathList.get(position)).resize(250 ,250)
                .centerCrop().into(holder.simpleDraweeView);

        holder.simpleDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SoftImageViewer.setImageView(context, imagePathList.get(position), position);

                return false;
            }
        });
       // ImageLoader.getInstance().displayImage(imagePathList.get(position), holder.simpleDraweeView, options);
      //  holder.simpleDraweeView.setImageBitmap(b);
        
    }

    @Override
    public int getItemCount() {
        return imagePathList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

         SimpleDraweeView simpleDraweeView;

         ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.drawee_image_gallery);
        }
    }
}
