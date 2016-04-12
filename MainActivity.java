package com.example.sibideiveegan.sleepanalyser1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import static java.lang.Integer.parseInt;

public class
        MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void open_app(View view){
        Intent intent =new Intent(this, DisplayActivity.class);
        EditText editText = (EditText)findViewById(R.id.editText);
        try {
            String message = editText.getText().toString();
            int pass = parseInt(message);

            if (pass == 3024) {
                editText.setText("");
                startActivity(intent);
            }
             /*else if (message == "") {
             //Null Pointer Exception
                Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_LONG).show();

            } */
            else {
                Toast.makeText(MainActivity.this, "Password incorrect", Toast.LENGTH_LONG).show();

            }
        }
        catch (Exception e)
        {
            Snackbar.make(view, "Enter Your Password", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
          //  Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
        }


    }
}
