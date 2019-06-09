package com.example.mad_project;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AdoptionActivity extends Fragment implements View.OnClickListener {

    Button dog_button,cat_button;
    private SQLiteDatabase sql;
    DatabaseHelper myDb;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.activity_adoption, null);
        dog_button=view.findViewById(R.id.dog);
        cat_button=view.findViewById(R.id.cat);
        dog_button.setOnClickListener(this);
        cat_button.setOnClickListener(this);
        return view;
    }

   public void onClick(View v){

        Fragment fragment=null;
        switch(v.getId()){
            case R.id.dog:
                fragment=new DogFragment();
                replaceFragment(fragment);
                break;

            case R.id.cat:
                fragment=new CatFragment();
                replaceFragment(fragment);
                break;
        }
   }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_replaceable,fragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }




}
