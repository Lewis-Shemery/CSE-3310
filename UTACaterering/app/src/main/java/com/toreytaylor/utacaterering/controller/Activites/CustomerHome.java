package com.toreytaylor.utacaterering.controller.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.toreytaylor.utacaterering.R;
import com.toreytaylor.utacaterering.controller.Fragments.RequestEvent;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;

import static com.toreytaylor.utacaterering.controller.Activites.MainLogin.SYSTEM_USER;

public class CustomerHome extends AppCompatActivity implements View.OnClickListener
{
    private SystemUser customer;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        //Receive customer info from mainLogin
        Intent intent = getIntent();
        customer = (SystemUser) intent.getSerializableExtra(SYSTEM_USER);

        //Test
        printf(customer.getUsername());

        //Get button id's
        Button requestEvent = findViewById(R.id.requestEvent);
        Button reservedEvent = findViewById(R.id.reservedEvents);
        Button account = findViewById(R.id.account);
        TextView logout = findViewById(R.id.logout);

        //Set listeners for buttons
        requestEvent.setOnClickListener(this);
        reservedEvent.setOnClickListener(this);
        account.setOnClickListener(this);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int click = v.getId();
        switch (click){
            case R.id.requestEvent:
                sendToRequestEvent();
                break;
            case R.id.reservedEvents:
                sendToReservedEvents();
                break;
            case R.id.account:
                break;
            case R.id.logout:
                sendToLogout();
                break;
        }
    }

    private void sendToLogout(){
        Intent intent = new Intent(this, MainLogin.class);
        startActivity(intent);
        finish();
    }
    private void sendToReservedEvents(){
        Intent intent = new Intent();
        intent.putExtra(SYSTEM_USER, customer);
        intent.setClass(this, ReservedEvents.class);
        startActivity(intent);

    }
    private void sendToRequestEvent(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        RequestEvent fragment = new RequestEvent();

        //Send customer object to fragment
        bundle.putSerializable(SYSTEM_USER, customer);
        fragment.setArguments(bundle);

        //Replace screen with fragment screen
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.addToBackStack("null").commit();

    }
    private void printf(String message){
        Context context = this;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();

    }

}
