package com.toreytaylor.utacaterering.controller.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.toreytaylor.utacaterering.R;
import com.toreytaylor.utacaterering.controller.Fragments.RequestEvent;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;

import static com.toreytaylor.utacaterering.controller.Activites.MainLogin.SYSTEM_USER;

public class CatererHome extends FragmentActivity {
    private SystemUser caterer;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_home);

        Intent intent = getIntent();
        caterer = (SystemUser) intent.getSerializableExtra(SYSTEM_USER);
    }

    private void printf(String message){
        Context context = this;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

   // @Override
    public void onClick(View v) {
        int click = v.getId();
        switch (click){
            case R.id.pendingEvents:
                sendToPendingEvents();
                break;
            case R.id.reservedEvents:
                sendToReservedEvents();
                break;
            case R.id.logout:
                sendToLogout();
                break;
        }
    }

    private void sendToPendingEvents(){
        Intent intent = new Intent();
        intent.putExtra(SYSTEM_USER, caterer);
        intent.setClass(this, PendingEvents.class);
        startActivity(intent);
    }

    private void sendToReservedEvents(){
        Intent intent = new Intent();
        intent.putExtra(SYSTEM_USER, caterer);
        intent.setClass(this, ReservedEvents.class);
        startActivity(intent);
    }

    private void sendToLogout(){
        Intent intent = new Intent(this, MainLogin.class);
        startActivity(intent);
        finish();
    }
}
