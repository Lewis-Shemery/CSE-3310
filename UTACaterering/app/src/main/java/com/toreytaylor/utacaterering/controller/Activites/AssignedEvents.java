package com.toreytaylor.utacaterering.controller.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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

public class AssignedEvents extends FragmentActivity {
    private Activity context;
    private List<Event> events;
    private SystemUser staff;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_events);

        recyclerView = findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this.context);
        recyclerView.setLayoutManager(layoutManager);

        //Get staff object
        Intent intent = getIntent();
        if(intent != null){
            staff = (SystemUser) intent.getSerializableExtra(SYSTEM_USER);
            events = getEventList();
            adapter = new MyAdapter(events, staff.getFirstName());
            recyclerView.setAdapter(adapter);
        }
        else
            printf("list is null");
    }

    public List<Event> getEventList() {
        //TODO: search logic change to staff's assigned events
        DBManager.SearchEventForUser Search
                = new DBManager.SearchEventForUser(DataBase.getInstance(this),
                staff.getUserId());

        Search.execute();

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return  Search.getList();

    }

    private void printf(String message) {
        Context context = this;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }
}
