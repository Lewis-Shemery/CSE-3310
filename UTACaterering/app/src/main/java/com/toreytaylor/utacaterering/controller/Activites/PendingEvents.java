package com.toreytaylor.utacaterering.controller.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.toreytaylor.utacaterering.R;
import com.toreytaylor.utacaterering.controller.Fragments.MyAdapter;
import com.toreytaylor.utacaterering.model.DataBaseManager.DBManager;
import com.toreytaylor.utacaterering.model.DataBaseManager.DataBase;
import com.toreytaylor.utacaterering.model.Objects.Event;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;

import java.util.List;

import static com.toreytaylor.utacaterering.controller.Activites.MainLogin.SYSTEM_USER;

public class PendingEvents extends FragmentActivity{
    private Activity context;
    private List<Event> events;
    private SystemUser user;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_events);

        recyclerView = findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this.context);
        recyclerView.setLayoutManager(layoutManager);

        //Get customer object
        Intent intent = getIntent();
        if(intent != null) {
            user = (SystemUser) intent.getSerializableExtra(SYSTEM_USER);
            events = getEventList();
            adapter = new MyAdapter(events, user.getFirstName());
            recyclerView.setAdapter(adapter);
        }
        else
            printf("list is null");

    }

    private List<Event> getEventList(){
        DBManager.SearchPendingForCaterer Search
                = new DBManager.SearchPendingForCaterer(DataBase.getInstance(this));

        Search.execute();
        // TODO: 4/28/2018 add progress bar to slow down program

        try {Thread.sleep(1000);}
        catch (InterruptedException e){e.printStackTrace();}

        return  Search.getList();
    }
    private void printf(String message){
        Context context = this;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();

    }

}