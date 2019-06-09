package com.example.mad_project;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.audiofx.BassBoost;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navMain;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private Fragment frag;

    private SQLiteDatabase sql;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDb = new DatabaseHelper(this);

        drawerLayout = findViewById(R.id.launcher_drawer);

        refresh_fragment();

        navMain = findViewById(R.id.launcher_navigation);
        navMain.setNavigationItemSelectedListener(this);

        // setting drawer
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateCatList();
        populateDogList();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item);
    }

    public void refresh_fragment()
    {
        fragmentManager = getSupportFragmentManager();

        if (fragmentManager == null)
        {
            Toast.makeText(getApplicationContext(), "fragment manager null", Toast.LENGTH_LONG).show();
        }
        else
        {
            ft = fragmentManager.beginTransaction();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        Class fragmentClass;

        String title = menuItem.getTitle().toString();

        switch (menuItem.getItemId())
        {

            case R.id.adoption:

                refresh_fragment();

                frag = new AdoptionActivity();
                ft.replace(R.id.fragment_replaceable, frag);
                ft.addToBackStack("adoption");
                ft.commit();

                break;
            case R.id.contactus:

                refresh_fragment();

                frag = new ContactUs();
                ft.replace(R.id.fragment_replaceable, frag);
                ft.addToBackStack("contact");
                ft.commit();

                break;


            case R.id.aboutus:

                refresh_fragment();

                frag = new AboutUs();
                ft.replace(R.id.fragment_replaceable, frag);
                ft.addToBackStack("about");
                ft.commit();

                break;

        }

        // setting the title for the drawer for each menu item
        setTitle(title);

        drawerLayout.closeDrawers();

        return true;
    }


    //this is to let the bitmap convert to bytes before storing in db
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }
    public Bitmap StringToBitMap(String image) {
        try {
            byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);

            InputStream inputStream = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

        public void populateCatList(){

            //String cat1_path = "C://Users//Hafsah//Downloads//MAD_Project-master//MAD_Project-master//app//src//main//res//drawable//anwer.jpg";
            Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.anwer);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte imageInByte[] = stream.toByteArray();
            myDb.insertDataCat("Whiskers", "Persian", "M", "12", imageInByte);
            myDb.insertDataCat("Melissa", "Siamese", "F", "3", imageInByte);

        }

    public void populateDogList(){

        //String cat1_path = "C://Users//Hafsah//Downloads//MAD_Project-master//MAD_Project-master//app//src//main//res//drawable//anwer.jpg";
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.boo);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();
        myDb.insertDataCat("Teddy", "Dobermien", "M", "12", imageInByte);
        myDb.insertDataCat("Tucker", "Labrador", "F", "3 moths", imageInByte);

    }
}
