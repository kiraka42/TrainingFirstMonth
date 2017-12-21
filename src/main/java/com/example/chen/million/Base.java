package com.example.chen.million;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



public class Base extends SQLiteOpenHelper {

    private final String SCRIPT_CREATE= "create table questions(qq1 String primary key, a1 String,a2 String,a3 String,a4 String, reponse int)";

    String q ="qui est naruto ? ";
    String [] a = {"chaud" , "froid", "moyen","boruto"};
    int rep=0;


    public Base(Context context) {
        super(context,"questions.db" , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists " + "geo");
            onCreate(db);
        }
    }


    public boolean ajouterLigne(String qq1, String a1, String a2,String a3,String a4, int reponse){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues ligne = new ContentValues();
        ligne.put("qq1",qq1);
        ligne.put("a1",a1);
        ligne.put("a2",a2);
        ligne.put("a3",a3);
        ligne.put("a4",a4);
        ligne.put("reponse",reponse);
        long res=db.insert("questions",null,ligne);
        db.close();
        return res != -1;
    }

    public void reset(){
        SQLiteDatabase db=getWritableDatabase();
        db.delete("questions", null, null);
    }

    public String touteInfo(String pays){
        SQLiteDatabase db=getReadableDatabase();
        String[] selectionArgs={pays};
        Cursor cur=db.query("question",null,"qq1=?",selectionArgs,null,null,null);
        String s=null;
        if (cur!=null && cur.moveToFirst()){
            String capitale =cur.getString(cur.getColumnIndex("a1"));
            String continent =cur.getString(cur.getColumnIndex("a2"));
            int population=cur.getInt(cur.getColumnIndex("reponse"));
            // s="La capitale de "+pays+ " est "+capitale+". Le pays se trouve en "+ continent+". Sa population est " +population;
        }
        db.close();
        return s;
    }

    public List<String> toutesCapitales(){
        List<String> lesCaps=null;
        SQLiteDatabase db=getReadableDatabase();
        String[] columns={"capitale"};
        Cursor cur=db.query("geo",columns,null,null,null,null,null);
        if (cur!=null){
            lesCaps =new ArrayList<String>();
            while(cur.moveToNext()){
                String capitale =cur.getString(cur.getColumnIndex("capitale"));
                lesCaps.add(capitale);
            }
        }
        db.close();
        return lesCaps;
    }

    public void  affichage(){
        List<String> lesCaps=null;
        SQLiteDatabase db=getReadableDatabase();
        String[] columns={"qq1"};
        Cursor cur=db.query("questions",columns,null,null,null,null,null);
        if (cur!=null){
            lesCaps =new ArrayList<String>();
            while(cur.moveToNext()){
                String capitale =cur.getString(cur.getColumnIndex("qq1"));
                lesCaps.add(capitale);
                Log.d("testing", "o " +capitale);
            }
        }
        db.close();

    }

    public boolean verifierCapitale(String pays,String capitale){
        SQLiteDatabase db=getReadableDatabase();
        String[] selectionArgs={pays,capitale};
        Cursor cur=db.query("geo",null,"pays=? AND capitale=?",selectionArgs,null,null,null);
        boolean res= cur !=null && cur.getCount()>0;
        return res;
    }

}