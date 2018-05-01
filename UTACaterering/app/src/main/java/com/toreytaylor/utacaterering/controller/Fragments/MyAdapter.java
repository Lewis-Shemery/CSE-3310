package com.toreytaylor.utacaterering.controller.Fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toreytaylor.utacaterering.R;
import com.toreytaylor.utacaterering.model.Objects.Event;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Event> events;
    private String dates, customer_name;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameOfEvent, date, name;

        public ViewHolder(View view){
            super(view);
            nameOfEvent = view.findViewById(R.id.nameOfEvent);
            date = view.findViewById(R.id.date);
            name = view.findViewById(R.id.name);
        }
    }

    public MyAdapter(List<Event> events, String name){
        this.events = events;
        this.customer_name = name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list, parent, false);
        return new ViewHolder(itemView);
    }

    private String setDate(Event event){
        String newString;
        int day, month, year;

        day = event.getDay(); month = event.getMonth(); year = event.getYear();
        newString = day + " / " + month + " / " + year;
        return newString;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.nameOfEvent.setText(event.getNameOfEvent());
        holder.date.setText(setDate(event));
        holder.name.setText(customer_name);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
