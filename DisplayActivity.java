package com.example.thandayuthapani.sleepanalyser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import static java.lang.Integer.parseInt;

public class DisplayActivity extends AppCompatActivity {
    public boolean flag= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

    }

        public void RecordSleepTime(View view) {
            if (flag == false) {
                flag = true;
                Date t = new Date();
                long x = t.getTime();
            }
            else{

            }
        }

    public void RecordWakeTime(View view)
    {
            if (flag == true){
                flag = false;
                Date t=new Date();
                long x = t.getTime();
            }
            else {

            }
    }



    public void summary(View view) {
        Intent intent = new Intent(this, Summary.class);
        startActivity(intent);
    }


}


