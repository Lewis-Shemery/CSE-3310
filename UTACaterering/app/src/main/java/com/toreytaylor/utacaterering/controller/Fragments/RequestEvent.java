package com.toreytaylor.utacaterering.controller.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.toreytaylor.utacaterering.R;
import com.toreytaylor.utacaterering.model.DataBaseManager.DBManager;
import com.toreytaylor.utacaterering.model.DataBaseManager.DataBase;
import com.toreytaylor.utacaterering.model.Objects.Event;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import static com.toreytaylor.utacaterering.controller.Activites.MainLogin.SYSTEM_USER;


public class RequestEvent extends Fragment implements View.OnClickListener{
    private Activity context;
    private Button submit;
    private EditText sizeOfParty, textDate, nameOfEvent;
    private Spinner startTime, endTime, formality, mealType,drinkVenue;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private SystemUser customer;
    private int DayOfMonth, Year, Month;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        context = getActivity();
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_request_event, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get customer object
        if(getArguments() != null)
            customer = (SystemUser)getArguments().getSerializable(SYSTEM_USER);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Set view id's
        setIds();

        //Set spinners
        setSpinners();

        setCalendar();

        //Set listeners
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submit){
            setRequestedEvent();
            sendToHomeScreen();
        }
    }
    private void sendToHomeScreen(){
        if(getFragmentManager().getBackStackEntryCount() == 0)
            this.context.finish();
        else
            getFragmentManager().popBackStack();

    }
    private void updateLabel(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        textDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void setCalendar(){
        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month;
                DayOfMonth = dayOfMonth;
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void setIds(){
        sizeOfParty= context.findViewById(R.id.sizeOfParty);
        textDate = context.findViewById(R.id.date);
        nameOfEvent = context.findViewById(R.id.nameOfEvent);
        startTime = context.findViewById(R.id.startTime);
        endTime = context.findViewById(R.id.endTime);
        mealType = context.findViewById(R.id.mealType);
        formality= context.findViewById(R.id.formality);
        drinkVenue= context.findViewById(R.id.drinkVenue);
        submit = context.findViewById(R.id.submit);
    }

    private void setSpinners(){
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterTime = ArrayAdapter.createFromResource(this.context,
                R.array.time, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterFormality = ArrayAdapter.createFromResource(this.context,
                R.array.formality, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterMealType = ArrayAdapter.createFromResource(this.context,
                R.array.meal_type, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterDrinkVenue = ArrayAdapter.createFromResource(this.context,
                R.array.drink_venue, android.R.layout.simple_spinner_dropdown_item);

        //Specify the layout to use when the list of choices appears
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterFormality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMealType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDrinkVenue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //SetAdapters
        startTime.setAdapter(adapterTime);
        endTime.setAdapter(adapterTime);
        formality.setAdapter(adapterFormality);
        mealType.setAdapter(adapterMealType);
        drinkVenue.setAdapter(adapterDrinkVenue);

    }
    private long parseTime(String t){
        String newString;
        int size =  t.length() - 1;

        newString = t.substring(0, size-1);

        if(t.charAt(size-1) == 'a')
            newString = "00" + newString;
        else
            newString = "10" + newString;

        return Long.parseLong(newString);
    }
    private Event setEvent(){
        Event event = new Event(UUID.randomUUID().toString());
        int isizeOfParty = Integer.parseInt(sizeOfParty.getText().toString());

        event.setSizeOfParty(isizeOfParty);
        event.setNameOfEvent(nameOfEvent.getText().toString());
        event.setStartTime(parseTime(startTime.getSelectedItem().toString().trim()));
        event.setEndTime(parseTime(endTime.getSelectedItem().toString().trim()));
        event.setDrinkVenue(drinkVenue.getSelectedItem().toString());
        event.setFormality(formality.getSelectedItem().toString());
        event.setMealType(mealType.getSelectedItem().toString());
        event.setApproval(false);
        event.setCustomerId(customer.getUserId());
        event.setDay(DayOfMonth);
        event.setMonth(Month);
        event.setYear(Year);
        return event;
    }

    private void setRequestedEvent(){
        //        //Check if fields are empty
        DBManager.InsertEvent  insertEvent = new DBManager.InsertEvent(DataBase.getInstance(context),
                setEvent());
        insertEvent.execute();

        // TODO: 4/28/2018 add progress bar to slow down program
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        printf("Event created!");
    }
    // TODO: 4/30/2018 check fields
//    private boolean isRequiredFieldsEmpty(){

//        if(isEmpty(sizeOfParty) || isEmpty(date) || isEmpty(time) || isEmpty(duration) ||
//                isEmpty(formality) || isEmpty(drinkVenue)){
//            printf("Fields cannot be empty");
//            return true;
//        }
//        return false;
//    }
//    private boolean isEmpty(EditText textField){
//        if(textField.getText().toString().trim().length() > 0 )
//            return false;
//
//        return true;
//    }
    private void printf(String message){
        Context context = this.context;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();

    }

}
