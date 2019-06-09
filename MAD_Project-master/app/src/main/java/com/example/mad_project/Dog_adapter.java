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

public class Dog_adapter extends RecyclerView.Adapter<Dog_adapter.ViewHolder>{

    public List<String>dog_name, dog_age,dog_breed, dog_gender;
    public List<Bitmap>dog_pics;
    public List<byte[]>dog_imgs;
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

    public Dog_adapter(Context context)
    {
        this.context = context;
        myDb = new DatabaseHelper(context);

        dog_name = new ArrayList<>();
        dog_age = new ArrayList<>();
        dog_breed = new ArrayList<>();
        dog_gender = new ArrayList<>();
        dog_imgs = new ArrayList<>();

        populating();
    }

    public void populating(){
        Cursor cursor = myDb.getAllData();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            dog_name.add(cursor.getString(1));
            dog_age.add(cursor.getString(4));
            dog_breed.add(cursor.getString(2));
            dog_gender.add(cursor.getString(3));
            dog_imgs.add(cursor.getBlob(5));
        }


    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public Dog_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    public void onBindViewHolder(Dog_adapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String n=dog_name.get(position).toString();
        String a=dog_age.get(position).toString();
        String b=dog_breed.get(position).toString();
        String g=dog_breed.get(position).toString();
        byte[] img=dog_imgs.get(position);

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
        return dog_age.size();
    }


}
