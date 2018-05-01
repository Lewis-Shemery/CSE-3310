package com.toreytaylor.utacaterering.controller.Activites;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.toreytaylor.utacaterering.R;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;

import static com.toreytaylor.utacaterering.controller.Activites.MainLogin.SYSTEM_USER;

public class StaffHome extends AppCompatActivity implements View.OnClickListener {

    private SystemUser staff;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

        //Receive customer info from mainLogin
        Intent intent = getIntent();
        staff = (SystemUser) intent.getSerializableExtra(SYSTEM_USER);

        //Test
        printf(staff.getUsername());

        //Get button id's
        Button assignedEvent = findViewById(R.id.assignedEvent);
        TextView logout = findViewById(R.id.logout);

        //Set listeners for buttons
        assignedEvent.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int click = v.getId();
        switch(click){
            case R.id.assignedEvent:
                sendToAssignedEvent();
                break;
            case R.id.logout:
                sendToLogout();
                break;
        }
    }

    private void sendToLogout() {
        Intent intent = new Intent(this, MainLogin.class);
        startActivity(intent);
        finish();
    }

    private void sendToAssignedEvent() {
        Intent intent = new Intent();
        intent.putExtra(SYSTEM_USER, staff);
        intent.setClass(this, AssignedEvents.class);
        startActivity(intent);
    }

    private void printf(String message) {
        Context context = this;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

}
