package com.toreytaylor.utacaterering.controller.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.toreytaylor.utacaterering.R;
import com.toreytaylor.utacaterering.controller.Activites.MainLogin;
import com.toreytaylor.utacaterering.model.DataBaseManager.DBManager;
import com.toreytaylor.utacaterering.model.DataBaseManager.DataBase;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;

import java.util.UUID;


public class Registration extends AppCompatActivity implements View.OnClickListener {
    EditText username, password, firstName, lastName;
    Spinner role;
    SystemUser systemUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        role = findViewById(R.id.role);
        username = findViewById(R.id.username);
        password =findViewById(R.id.password);
        firstName = findViewById(R.id.firstName);
        lastName =findViewById(R.id.lastName);

        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterRole = ArrayAdapter.createFromResource(this,
                R.array.role_array, android.R.layout.simple_spinner_dropdown_item);

        //Specify the layout to use when the list of choices appears
        adapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        role.setAdapter(adapterRole);

        Button submit  = findViewById(R.id.submit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(inputIsValid()){
            setSystemUser();
            insertSystemUser();
            Intent i = new Intent(this, MainLogin.class);
            startActivity(i);
            finish();
        }
    }


    public boolean inputIsValid(){
        if(isRequiredFieldsEmpty()){
            return false;
        }
        else if(!(isUsernameAvailable())){
            return false;
        }
        else {
            printf("Creating new user");
            return true;
        }
    }
    private boolean isUsernameAvailable(){
        SystemUser temp = fetchSystemUser();
        if(temp == null){
            return true;
        }
        else {
            printf("Username already exist");
            username.getText().clear();
            return false;
        }
    }
    private SystemUser fetchSystemUser(){
        SystemUser temp;
        DBManager.SearchSystemUser Search;

        Search = new DBManager.SearchSystemUser(DataBase.getInstance(this),
                username.getText().toString());
        Search.execute();

        // TODO: 4/28/2018 add progress bar to slow down program

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        temp = Search.getSystemUser();

        return temp;
    }
    private boolean isRequiredFieldsEmpty(){
        if(isEmpty(username) || isEmpty(password) || isEmpty(firstName) ||
                isEmpty(lastName)){
            printf("Fields cannot be empty");
            username.getText().clear();
            password.getText().clear();
            firstName.getText().clear();
            lastName.getText().clear();
            return true;
        }
        return false;
    }
    private boolean isEmpty(EditText textField){
        if(textField.getText().toString().trim().length() > 0 )
            return false;
        else
            return true;
    }
    private void insertSystemUser(){
        DBManager.InsertSystemUser Insert = new DBManager.InsertSystemUser(DataBase.getInstance(this),systemUser);
        Insert.execute();

        // TODO: 4/28/2018 add progress bar to slow down program

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    private void setSystemUser(){
        SystemUser systemUser = new SystemUser(UUID.randomUUID().toString());
        systemUser.setUsername(username.getText().toString().trim());
        systemUser.setPassword(password.getText().toString());
        systemUser.setFirstName(firstName.getText().toString());
        systemUser.setLastName(lastName.getText().toString());
        systemUser.setRole(role.getSelectedItem().toString());
        this.systemUser = systemUser;
    }
    // TODO: 4/27/2018 temporary
    private void printf(String message){
        Context context = this;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }
}
