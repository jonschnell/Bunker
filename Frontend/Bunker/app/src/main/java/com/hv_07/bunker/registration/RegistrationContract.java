package com.hv_07.bunker.registration;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

public interface RegistrationContract {
    interface View{



        Context getApplicationContextRegistration();

        void startActivityRegistration(Intent intent);
    }
    interface Presenter{
        boolean checkDataEntered(EditText user, EditText address, EditText firstName, EditText lastName, EditText email, EditText phone, EditText password, EditText passwordConfirm);
        boolean isEmail(String str);
        boolean isEqual(String str1,String str2);
        boolean isNotEmpty(String str);
        boolean isLong(String str);
    }
}
