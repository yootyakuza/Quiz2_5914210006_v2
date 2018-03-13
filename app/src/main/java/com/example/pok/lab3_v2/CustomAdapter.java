package com.example.pok.lab3_v2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;


/**
 * Created by pok on 24/2/2561.
 */

public class CustomAdapter extends BaseAdapter {

    Context mContext;

    String[] strName;
    String[] phoneNum;
    String[] imgPath;
    Bitmap bitmap;

    public CustomAdapter(Context mContext, String[] strName, String[] phoneNum, String[] imgPath) {
        this.mContext = mContext;
        this.strName = strName;
        this.phoneNum = phoneNum;
        this.imgPath = imgPath;
    }

    @Override
    public int getCount() {
        return strName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = mInflater.inflate(R.layout.listview_row, parent, false);

        TextView textView = convertView.findViewById(R.id.textView1);
        textView.setText(strName[position]);

        TextView textView2 = convertView.findViewById(R.id.textView2);
        textView2.setText(phoneNum[position]);

        TextView textViewPath = convertView.findViewById(R.id.textViewPath);
        textViewPath.setText(imgPath[position]);

//        bitmap = BitmapFactory.decodeFile(imgPath[position]);
//        ImageView imageView = convertView.findViewById(R.id.newImageview);
//        imageView.setImageBitmap(bitmap);


//        imageView.setImageBitmap(getResizedBitmap(bitmap,105,105));
//        Glide.with(mContext).load(url).into(imageView);


        return convertView;
    }
}
