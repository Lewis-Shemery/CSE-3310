package com.toreytaylor.utacaterering.controller.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.toreytaylor.utacaterering.R;
import com.toreytaylor.utacaterering.model.DataBaseManager.DBManager;
import com.toreytaylor.utacaterering.model.DataBaseManager.DataBase;
import com.toreytaylor.utacaterering.model.Objects.SystemUser;


public class MainLogin extends AppCompatActivity implements View.OnClickListener{
    private EditText username, password;
    private SystemUser systemUser;
    private DBManager.SearchSystemUser Search;
    public static final String SYSTEM_USER = "systemUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        //Get field id's
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        TextView registration = findViewById(R.id.registration);
        Button submit = findViewById(R.id.submit);

        //Set onClick
        submit.setOnClickListener(this);
        registration.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Case submit button is clicked vs registration name is clicked
        switch (v.getId()){
            case R.id.registration:
                Intent i = new Intent(this, Registration.class);
                startActivity(i);
                break;
            case R.id.submit:
                if(inputIsValid()){
                sendToHomeScreen(systemUser.getRole());
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataBase.cleanUp();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clearField(username);clearField(password);
    }

    private void sendToHomeScreen(String role){
        Intent intent = new Intent();
        intent.putExtra(SYSTEM_USER, systemUser);

        switch (role){
            case "Caterer":
                intent.setClass(this, CatererHome.class);
                break;

            case "Customer":
                intent.setClass(this, CustomerHome.class);
                break;
        }
        startActivity(intent);
        finish();
    }
    private boolean inputIsValid(){
        if(isRequiredFieldsEmpty()) {
            return false;
        }
        else if(!doesSystemUserExist()){
            return false;
        }
        else if(!isSystemUserRegistered()){
            return false;
        }
        //Else username and password is valid
        else
            return true;
    }
    private boolean isSystemUserRegistered(){
        //Check if username in the database matches username given
        if(!(systemUser.getUsername().equals(getEditTextString(username)))){
            printf("Username not Found");
            clearField(username);clearField(password);
            return false;
        }

        //Check if password in the database matches the password given
        else if(!(systemUser.getPassword().equals(password.getText().toString()))){
            printf("Incorrect password");
            clearField(username);clearField(password);
            return false;
        }

        return true;
    }
    private boolean doesSystemUserExist(){
        //Fetch systemUser from the database
        fetchSystemUser();

        //Check if username is in the database
        if(systemUser == null){
            printf("Username not Found user null");
            clearField(username);clearField(password);
            return false;
        }

        return true;
    }
    private void fetchSystemUser(){
        Search = new DBManager.SearchSystemUser(DataBase.getInstance(this),
                username.getText().toString());
        Search.execute();
        // TODO: 4/28/2018 add progress bar to slow down program

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.systemUser = Search.getSystemUser();

    }
    private boolean isRequiredFieldsEmpty(){
        //Check if fields are empty
        if(isEmpty(username) || isEmpty(password)){
            printf("Fields cannot be empty");
            clearField(username); clearField(password);
            return true;
        }
        return false;
    }
    private void printf(String message){
        Context context = this;
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();

    }
    private String getEditTextString(EditText field){
        return field.getText().toString().trim();
    }
    private void clearField(EditText field){
        field.getText().clear();
    }
    private boolean isEmpty(EditText textField){
        if(textField.getText().toString().trim().length() > 0 )
            return false;

        return true;
    }
    public  void setUser(SystemUser systemUser){
        this.systemUser = systemUser;
    }

}
