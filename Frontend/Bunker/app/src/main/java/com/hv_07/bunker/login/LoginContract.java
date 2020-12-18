package com.hv_07.bunker.login;

import android.content.Context;
import android.content.Intent;

import com.hv_07.bunker.user.User;

public interface LoginContract {
    interface View{
        void startIntentServices(Intent intent);
        Context getApplicationContextLogin();
        User getUser();
    }
    interface Presenter{

    }
}
