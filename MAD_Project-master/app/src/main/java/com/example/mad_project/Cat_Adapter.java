package com.example.mad_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Cat_Adapter extends RecyclerView.Adapter<Cat_Adapter.ViewHolder>{

    public List<String>cat_name, cat_age,cat_breed, cat_gender;
    public List<Bitmap>cat_pics;
    public List<byte[]>cat_imgs;
    private SQLiteDatabase sql;
    DatabaseHelper myDb;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView cat_image;
        public TextView breed,gender,age,name;


        public ViewHolder(View itemView){
            super(itemView);
;
            name=itemView.findViewById(R.id.cat_name);
            breed=itemView.findViewById(R.id.cat_breed);
            gender=itemView.findViewById(R.id.cat_gender);
            age=itemView.findViewById(R.id.cat_age);
            cat_image=itemView.findViewById(R.id.cat_img);
        }

    }

    public Cat_Adapter(Context context)
    {
        this.context = context;
        myDb = new DatabaseHelper(context);

        cat_name = new ArrayList<>();
        cat_age = new ArrayList<>();
        cat_breed = new ArrayList<>();
        cat_gender = new ArrayList<>();
        cat_imgs = new ArrayList<>();

        populating();
    }

    public void populating(){
        Cursor cursor = myDb.getAllData();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            cat_name.add(cursor.getString(1));
            cat_age.add(cursor.getString(4));
            cat_breed.add(cursor.getString(2));
            cat_gender.add(cursor.getString(3));
            cat_imgs.add(cursor.getBlob(5));
        }


    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public Cat_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    public void onBindViewHolder(Cat_Adapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
         String n=cat_name.get(position).toString();
         String a=cat_age.get(position).toString();
         String b=cat_breed.get(position).toString();
         String g=cat_breed.get(position).toString();
         byte[] img=cat_imgs.get(position);

         Bitmap bit=getImage(img);

        // Set item views based on your views and data model
        ImageView image=viewHolder.cat_image;
        image.setImageBitmap(bit);
        TextView textView1 = viewHolder.name;
        textView1.setText(n);
        TextView textView2 = viewHolder.breed;
        textView2.setText(b);
        TextView textView = viewHolder.gender;
        textView.setText(g);
        TextView textView3 = viewHolder.age;
        textView3.setText(a);

    }

    public int getItemCount(){
        return cat_age.size();
    }


}
