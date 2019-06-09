package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Employee.db";
    public static final String TABLE1_NAME="CAT_Table";
    public static final String COL1_1="ID";
    public static final String COL1_2="NAME";
    public static final String COL1_3="BREED";
    public static final String COL1_4="GENDER";
    public static final String COL1_5="AGE";
    public static final String COL1_6="IMG";

    public static final String TABLE2_NAME="User_Table";
    public static final String COL2_1="ID";
    public static final String COL2_2="NAME";
    public static final String COL2_3="EMAIL";
    public static final String COL2_4="PASSWORD";

    public static final String TABLE3_NAME="Dog_Table";
    public static final String COL3_1="ID";
    public static final String COL3_2="NAME";
    public static final String COL3_3="BREED";
    public static final String COL3_4="GENDER";
    public static final String COL3_5="AGE";
    public static final String COL3_6="IMG";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, BREED TEXT, GENDER TEXT,AGE INTEGER,IMG BLOB)");
        db.execSQL("create table " + TABLE2_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("create table " + TABLE3_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, BREED TEXT, GENDER TEXT,AGE INTEGER,IMG BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3_NAME);
        onCreate(db);
    }

    public boolean insertDataUser(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_2,name);
        contentValues.put(COL2_3,email);
        contentValues.put(COL2_4,password);
        long result = db.insert(TABLE2_NAME,null, contentValues);
        if(result==1){
            return false;
        }
        else return true;
    }
    public boolean insertDataCat(String name, String breed, String gender, String age, byte[] img){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_2,name);
        contentValues.put(COL1_3,breed);
        contentValues.put(COL1_4,gender);
        contentValues.put(COL1_5,age);
        contentValues.put(COL1_6,img);
        long result = db.insert(TABLE1_NAME,null, contentValues);
        if(result==1){
            return false;
        }
        else return true;
    }

    public boolean insertDataDog(String name, String breed, String gender, String age, byte[] img){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3_2,name);
        contentValues.put(COL3_3,breed);
        contentValues.put(COL3_4,gender);
        contentValues.put(COL3_5,age);
        contentValues.put(COL3_6,img);
        long result = db.insert(TABLE3_NAME,null, contentValues);
        if(result==1){
            return false;
        }
        else return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select*from "+TABLE1_NAME,null);
        return res;
    }

    public Cursor getDogData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select*from "+TABLE3_NAME,null);
        return res;
    }

    public boolean getUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = TABLE2_NAME;
        String[] columns = {COL2_3, COL2_4};
        String selection = COL2_3 + " =? AND " + COL2_4 + " =?";
        String[] selectionArgs = {email,password};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = "10";

        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        if(cursor == null || cursor.isNull(3)){
            return false;
        }
        else return true;
    }

    public boolean updateData(String id, String name, String company, String designation, String phno){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_1,id);
        contentValues.put(COL1_2,name);
        contentValues.put(COL1_3,company);
        contentValues.put(COL1_4,designation);
        contentValues.put(COL1_5,phno);
        db.update(TABLE1_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public boolean updateDataUser(String id, String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_1,id);
        contentValues.put(COL2_2,name);
        contentValues.put(COL2_3,email);
        contentValues.put(COL2_4,password);
        db.update(TABLE2_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE1_NAME,"ID=?", new String[]{id});
    }
    public Integer deleteDataUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE2_NAME,"ID=?", new String[]{id});
    }

    public List<DataModel> getdata(){
        // DataModel dataModel = new DataModel();
        List<DataModel> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE1_NAME+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new DataModel();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
            String designation = cursor.getString(cursor.getColumnIndexOrThrow("DESIGNATION"));
            dataModel.setName(name);
            dataModel.setDesignation(designation);
            stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        //

        return data;
    }
}