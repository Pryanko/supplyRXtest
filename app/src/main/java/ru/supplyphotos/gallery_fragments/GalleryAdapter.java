package ru.supplyphotos.gallery_fragments;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import ru.supplyphotos.supplyrxtest.R;

/**
 * @author Libgo on 21.03.2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{


    private List<Bitmap> imagePathList = new ArrayList<>();


    public void updateImageList(Bitmap bitmap){
        this.imagePathList.add(bitmap);
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
        Bitmap b = imagePathList.get(position);
        holder.simpleDraweeView.setImageBitmap(b);
    }

    @Override
    public int getItemCount() {
        return imagePathList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

         ImageView simpleDraweeView;

         ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (ImageView) itemView.findViewById(R.id.drawee_image_gallery);
        }
    }
}
