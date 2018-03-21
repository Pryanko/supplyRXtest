package ru.supplyphotos.gallery_fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import ru.supplyphotos.supplyrxtest.R;

/**
 * @author Libgo on 21.03.2018.
 */

public class GridAdapter extends BaseAdapter {

    private List<String> thumbnails;
    private LayoutInflater layoutInflater;
    private int count;

    public GridAdapter(Object o,  int count) {
        layoutInflater = (LayoutInflater) o;

        this.count = count;

    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("123","333");
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.gallery_image_item, null);
            viewHolder.imageview = (ImageView) convertView.findViewById(R.id.drawee_image_gallery);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageview.setId(position);

        
        File file = new File("File://" + thumbnails.get(position));

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            Log.d("isERROR", "notERROR");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("ERROR", "ERROR");
        }
        viewHolder.imageview.setImageBitmap(bitmap);
        return convertView;
    }



    class ViewHolder {

        ImageView imageview;

        ViewHolder() {
        }
    }
}
