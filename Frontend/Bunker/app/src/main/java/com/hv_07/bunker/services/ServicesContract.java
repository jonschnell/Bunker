package com.hv_07.bunker.services;

import android.content.Context;
import android.content.Intent;

public interface ServicesContract {
    interface View{
        Context getApplicationContextServices();
        void AddService(Intent intent);
    }
    interface Presenter{

    }
}
