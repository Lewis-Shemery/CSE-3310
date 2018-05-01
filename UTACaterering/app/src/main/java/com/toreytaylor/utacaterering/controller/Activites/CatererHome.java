package com.toreytaylor.utacaterering.controller.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.toreytaylor.utacaterering.R;

public class CatererHome extends FragmentActivity {
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_home);

        Intent intent = getIntent();
        username = intent.getStringExtra(MainLogin.SYSTEM_USER);

        // TODO: 4/29/2018 test
        printf(username);
    }
    private void printf(String message){
        Context context = this;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();

    }

}
