package com.hv_07.bunker.registration;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationPresenter implements RegistrationContract.Presenter{

    private RegistrationContract.View registrationView;

    public RegistrationPresenter(RegistrationContract.View registrationView){
        this.registrationView=registrationView;
    }
    @Override
    public boolean checkDataEntered(EditText user, EditText address, EditText firstName, EditText lastName, EditText email, EditText phone, EditText password, EditText passwordConfirm) {
        boolean ok=true;
        if(!isNotEmpty(email.getText().toString())||!isNotEmpty(user.getText().toString())||!isNotEmpty(password.getText().toString())||!isNotEmpty(passwordConfirm.getText().toString())){
            Toast t = Toast.makeText(registrationView.getApplicationContextRegistration(), "You must fill out all fields to register!", Toast.LENGTH_SHORT);
            t.show();
            ok=false;
        }
        if(!isEqual(password.getText().toString(),passwordConfirm.getText().toString())){
            passwordConfirm.setError("Passwords to not match");
            ok=false;
        }
        if(!isEmail(email.getText().toString())){
            email.setError("Please enter a valid email address");
            ok=false;
        }
        if(!isLong(password.getText().toString())){
            password.setError("Please make sure password is longer than 8 characters");
            ok=false;
        }
        return ok;
    }

    @Override
    public boolean isEmail(String email){
        return(isNotEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    @Override
    public boolean isEqual(String str1,String str2){
        return(str1.equals(str2));
    }
    @Override
    public boolean isNotEmpty(String str){
        return (str.length()>0);
    }
    @Override
    public boolean isLong(String str){
        return(str.length()>=8);
    }
}
