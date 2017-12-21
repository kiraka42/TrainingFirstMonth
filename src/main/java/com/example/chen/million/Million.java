package com.example.chen.million;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread;


/******************************************************************
 ==== ==== ===   ===    ########
  ==   =  = === ===     #/\/\/\#
  ==   ===  =======     #\/\  /#
  ==   =  = ==   ==     #/\/  \#
 ==== ==== ===   ===    #\/\/\/#
                        ########
 Interactive Experience

 @uthor : patrick Chen

 *******************************************************************/



public class Million extends AppCompatActivity {
    ImageView im;
    Bitmap bmp3;
    RelativeLayout relative;
    TextView tv,tv2,tv3,tv4,tv5;
    TextView somme;
    Button b1,b2,b3,b4;
    ImageButton jo,jo2,jo3;
    boolean good=true;
    MediaPlayer media;
    Base base;

    int [] montant = {1000,2000,5000,10000,50000,75000,
            150000,250000,500000,1000000};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        base =  new Base(this);

        b1 = (Button) findViewById(R.id.button5);
        b2 = (Button) findViewById(R.id.button8);
        b3 = (Button) findViewById(R.id.button6);
        b4 = (Button) findViewById(R.id.button7);

        jo=(ImageButton) findViewById(R.id.jj);
        jo.setBackgroundResource(R.drawable.jj);
        jo2=(ImageButton) findViewById(R.id.tel);
        jo2.setBackgroundResource(R.drawable.tel);
        jo3=(ImageButton) findViewById(R.id.pub);
        jo3.setBackgroundResource(R.drawable.pub);
        somme = (TextView) findViewById(R.id.textView3);

        somme.setTextColor(Color.rgb(255, 140, 0));
        somme.setText("000");


        //Doragon whito
        im = (ImageView)findViewById(R.id.imageView9);
        //relative = (RelativeLayout) findViewById(R.id.relative);


        bmp3= BitmapFactory.decodeResource(getResources(),R.drawable.qui);//image is your image
        bmp3= Bitmap.createScaledBitmap(bmp3, im.getDrawable().getIntrinsicWidth(),im.getDrawable().getIntrinsicHeight(), true);


        reset();
        try {
            retriveAll(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        newQuestion();
        media = MediaPlayer.create(getApplicationContext(), R.raw.debut);
        media.setVolume(8.7f,8.7f);
        media.setLooping(true);
        media.start();


    }

    int checking = 0 ;
    int numQuestion=0;

    public void tip(View view){
        if(view == b1) {
            checking=0;
        }else if(view == b2){
            checking=1;
        }else if(view == b3){
            checking=2;
        }else if(view == b4)
        {
            checking=3;
        }
        if(pressed) {
            pressed=false;
            Thread t = new Thread(new Clignote());
            t.start();
        }
    }

    boolean pressed = true;

    public void newQuestion(){


        tv = (TextView) findViewById(R.id.textView7);
        tv.setText(base.a[0]);
        tv.setTextColor(Color.WHITE);

        tv2 = (TextView) findViewById(R.id.textView8);
        tv2.setText(base.a[1]);
        tv2.setTextColor(Color.WHITE);


        tv3 = (TextView) findViewById(R.id.textView9);
        tv3.setText(base.a[2]);
        tv3.setTextColor(Color.WHITE);

        tv4 = (TextView) findViewById(R.id.textView10);
        tv4.setText(base.a[3]);
        tv4.setTextColor(Color.WHITE);

        tv5 = (TextView) findViewById(R.id.textView11);
        tv5.setText(base.q);
        tv5.setTextColor(Color.WHITE);
    }

    public void nextQuestion(){


        ScaleAnimation fade_in2 =  new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in2.setDuration(1500);     // animation duration in milliseconds
        fade_in2.setFillAfter(true);    // If fillAfter is true, the transformation that this animation performed will persist when it is finished.
        im.startAnimation(fade_in2);
        im.setImageBitmap(bmp3);


        try {
            if(good) {
                numQuestion++;
                somme.setText(montant[numQuestion-1]+"");
            }
            if(numQuestion < nbQ )
                retriveAll(numQuestion);
            else{
                media.stop();
                media = MediaPlayer.create(getApplicationContext(), R.raw.million);
                media.setVolume(8.7f,8.7f);

                media.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reset();

        tv.setText(base.a[0]);
        tv2.setText(base.a[1]);
        tv3.setText(base.a[2]);
        tv4.setText(base.a[3]);
        tv5.setText(base.q);
        pressed=true;

    }


    public void reset(){

        b1.setBackgroundColor(Color.parseColor("#ffffff"));
        b1.setTextColor(Color.parseColor("#DCDCDC"));

        b2.setBackgroundColor(Color.parseColor("#ffffff"));
        b2.setTextColor(Color.parseColor("#DCDCDC"));
        b3.setBackgroundColor(Color.parseColor("#ffffff"));
        b3.setTextColor(Color.parseColor("#DCDCDC"));
        b4.setBackgroundColor(Color.parseColor("#ffffff"));
        b4.setTextColor(Color.parseColor("#DCDCDC"));
        GradientDrawable gd = new GradientDrawable();

        gd.setColor(Color.rgb(48, 48, 48)); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFFFFFFFF);
        b1.setBackgroundDrawable(gd);
        b2.setBackgroundDrawable(gd);
        b3.setBackgroundDrawable(gd);
        b4.setBackgroundDrawable(gd);

    }


    public static String convertStreamToString(InputStream is)
            throws IOException {
        Writer writer = new StringWriter();

        char[] buffer = new char[2048];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
        String text = writer.toString();
        return text;
    }

    public void retriveAll(int nq) throws IOException {
        //Memoire mem = new Memoire() ;
        AssetManager mngr = getAssets();
        InputStream is = mngr.open("million.txt");

        String textfile = convertStreamToString(is);
        //  String chaine = mem.af(textfile) ;
        String[] tab = textfile.split("\n");
        nbQ = Integer.parseInt(tab[0].trim().split(",")[0]);
        String res ="";
        base.q=tab[nq+1].trim().split(",")[0];
        base.a[0]=tab[nq+1].trim().split(",")[1];
        base.a[1]=tab[nq+1].trim().split(",")[2];
        base.a[2]=tab[nq+1].trim().split(",")[3];
        base.a[3]=tab[nq+1].trim().split(",")[4];
        base.rep=Integer.parseInt(tab[nq+1].trim().split(",")[5]);

    }

    int nbQ=0;


    class Clignote implements Runnable{


        public void run(){
            MediaPlayer media = new MediaPlayer();


            //Checking the answer in the tab
            if(checking == base.rep){ good=true; media.create(getApplicationContext(), R.raw.good).start();}
            else good =false;

            for(int i=0 ; i< 10 ; i++) {
                if( i % 2 == 0)
                    check(checking).setBackgroundColor(Color.rgb(255, 140, 0));
                else
                    check(checking).setBackgroundColor(Color.BLACK);

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(good) {
                check(checking).setBackgroundColor(Color.rgb(34, 120, 15));
            }
            else {
                check(checking).setBackgroundColor(Color.RED);
                check(base.rep).setBackgroundColor(Color.rgb(34, 120, 15));
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                public void run() {
                    nextQuestion();
                }
            });

        }
    }

    public Button check(int checking){
        Button button=null;
        switch(checking) {
            case 0:
                button = b1;
                break;
            case 1:
                button = b2;
                break;
            case 2:
                button = b3;
                break;
            case 3:
                button = b4;
                break;
        }
        return button;
    }



}
