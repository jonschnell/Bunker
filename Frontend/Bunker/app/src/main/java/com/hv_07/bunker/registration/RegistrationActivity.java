package com.hv_07.bunker.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hv_07.bunker.R;

import org.json.JSONException;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View{
    String DatabaseURL="https://10.24.226.105:8080/user";
    EditText usernameET,addressET,firstNameET,lastNameET,emailET,phoneET,passwordET,passwordConfirmET;
    public Button register;
    private String username;
    private String address;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        usernameET=(EditText)findViewById(R.id.username);
        addressET=(EditText)findViewById(R.id.address);
        firstNameET=(EditText)findViewById(R.id.firstName);
        lastNameET=(EditText)findViewById(R.id.lastName);
        emailET=(EditText)findViewById(R.id.email);
        phoneET=(EditText)findViewById(R.id.phone);
        passwordET=(EditText)findViewById(R.id.password);
        passwordConfirmET=(EditText)findViewById(R.id.passwordConfirm);
        final RegistrationActivity V = this;
        register=findViewById(R.id.registerSubmitButton);
        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                username=usernameET.getText().toString();
                address=addressET.getText().toString();
                firstName=firstNameET.getText().toString();
                lastName=lastNameET.getText().toString();
                email=emailET.getText().toString();
                phone=phoneET.getText().toString();
                password=passwordET.getText().toString();
                passwordConfirm=passwordConfirmET.getText().toString();
                RegistrationInfo r=new RegistrationInfo(username,address,firstName,lastName,email,phone,password,V);
                RegistrationPresenter p = new RegistrationPresenter(V);
                if(p.checkDataEntered(usernameET,addressET,firstNameET,lastNameET,emailET,phoneET,passwordET,passwordConfirmET)){
                    try {
                        r.SendInfo();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }


    @Override
    public Context getApplicationContextRegistration() {
        return this.getApplicationContext();
    }

    @Override
    public void startActivityRegistration(Intent intent) {
        startActivity(intent);
    }
}