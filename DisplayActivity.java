package com.example.sibideiveegan.sleepanalyser1;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DisplayActivity extends AppCompatActivity {
    public boolean flag = false;
    Button button2, button3;
    SleepDatabase da;
    TextView tv2;
    private int avgHour = 0;
    private int avgMin = 0;
    private int avgSec = 0;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        tv2 = (TextView) findViewById(R.id.textView2);
        da = new SleepDatabase(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setVisibility(View.VISIBLE);
                //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //              .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

    }

    public void RecordSleepTime(View view) {
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.VISIBLE);
        /*   if (flag == false) {
                    flag = true;
                    Date t = new Date();
                    long x = t.getTime();
                }
                else{

                }*/
        //date
        //Calendar c = Calendar.getInstance();
        //String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        //time
        /*Time now = new Time();
        now.setToNow();
        String curtime = String.valueOf(now);*/
        /*Calendar t = Calendar.getInstance();
        SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss a");
        String curtime = tf.format(t.getTime());*/
        //   String k= String.valueOf(tf.getTimeInstance());
        //LocalDateTime.now()
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String curdate = df.format(cd.getTime());
        String Hour = String.valueOf(cd.get(Calendar.HOUR_OF_DAY));
        String Min = String.valueOf(cd.get(Calendar.MINUTE));
        String Sec = String.valueOf(cd.get(Calendar.SECOND));
        //Toast.makeText(this, curdate+Hour+Min+Sec,Toast.LENGTH_LONG).show();
        da.insertdata("SleepTimeRecorder", "A01", curdate, Hour, Min, Sec);
    }

    public void RecordWakeTime(View view) {
        button3.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.VISIBLE);
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String curdate = df.format(cd.getTime());
        String Hour = String.valueOf(cd.get(Calendar.HOUR_OF_DAY));
        String Min = String.valueOf(cd.get(Calendar.MINUTE));
        String Sec = String.valueOf(cd.get(Calendar.SECOND));
        //Toast.makeText(this, curdate+Hour+Min+Sec,Toast.LENGTH_LONG).show();
        da.insertdata("WakeUpTimeRecorder", "A01", curdate, Hour, Min, Sec);

        /* if (flag == true){
                    flag = false;
                    Date t=new Date();
                    long x = t.getTime();
                }
                else {

                }*/
        //date
        /* Time now = new Time();
        now.setToNow();*/
        /*Calendar cd = Calendar.getInstance();
        int seconds = cd.get(Calendar.HOUR_OF_DAY);*/
        //String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        //time
        /*  Time now = new Time();
            now.setToNow();
            String curtime = String.valueOf(now);*/
        /*Calendar t = Calendar.getInstance();
        SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss a");
        String curtime = tf.format(t.getTime());*/
        //String curdate = df.format(cd.getTime());
    }

    public void summary(View view) {
    /*Intent intent = new Intent(this, Summary.class);
      startActivity(intent);*/
//Display
        Cursor r = da.getavg();
        int i=3;
        int j = 7;
        if (r.getCount() == 0) {
            showmsg("err", "no data");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (r.moveToNext()) {
            for (i=3,j=7;i<6;i++,j++) {
                int t1 = Integer.parseInt(r.getString(i));
                int t2 = Integer.parseInt(r.getString(j));
            /* if (r.getCount() == 1) {
                avgHour = r.getString(2);
                avgMin = r.getString(3);
                avgSec = r.getString(5)
            }*/
                if (i==3 && j==7)
                {
                    if(t2<t1) {
                        avgHour = 24 - t1;
                        avgHour += t2;
                    }
                    else{
                        avgHour = t2-t1;
                    }
                }
                if (i==4 && j==8)
                {
                    if (t2<t1){
                        avgMin=t1-t2;
                        avgHour -=1;
                        avgMin=60-avgMin;
                    }
                    else {
                        avgMin =t2 - t1;
                    }

                }
                Toast.makeText(this,avgHour+":"+avgMin+":"+avgSec,Toast.LENGTH_LONG);
                buffer.append("Date:" + r.getString(2) +r.getString(5)+ "\n");
                buffer.append("AvgSleepTime:" + avgHour +":"+ avgMin+":"+avgSec+ "\n");
                buffer.append("---------------");
                showmsg("Data", buffer.toString());
                Log.d("after msg call", "working");
               // avg = (float) ((0.6 * avg) + (0.4 * avg));
            }
        }
    }
        public void showmsg(String title, String message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            Log.d("infunc", "reached");
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.show();
        }
//showmsg("Data", buffer.toString());

        /*Cursor r = da.getall("SleepTimeRecorder");
        //  Cursor w =da.getall("WakeUpTimeRecorder");
       if (r.getCount() == 0) {
            showmsg("err", "no data");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (r.moveToNext()) {
            buffer.append("Date:" + r.getString(2) + "\n");
            buffer.append("SleepTime:" + r.getString(3) +":"+ r.getString(4)+":"+r.getString(5)+ "\n");
        }
        showmsg("Data", buffer.toString());
        Log.d("after msg call", "working");
    }

    public void showmsg(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.d("infunc", "reached");
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }*/

    /*StringBuffer buffer = new StringBuffer();
        while (r.moveToNext()) {
            buffer.append("Date:" + r.getString(1) + "\n");
            buffer.append("SleepTime:" + r.getString(2) + "\n");
        }
        Log.d("after msg call", "working");

    }*/

        //Edit Mode

    public void EditMode(View view) {
        fab.setVisibility(View.INVISIBLE);
    }
}



