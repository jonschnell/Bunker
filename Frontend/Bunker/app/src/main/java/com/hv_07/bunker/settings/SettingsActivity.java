package com.hv_07.bunker.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hv_07.bunker.R;
import com.hv_07.bunker.login.LoginActivity;
import com.hv_07.bunker.services.ServicesActivity;
import com.hv_07.bunker.user.User;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingsActivity extends AppCompatActivity {
    User currentUser;
    Button Delete;
    Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Bundle b = this.getIntent().getExtras();
        if(b!=null){
            currentUser=b.getParcelable("UserData");
        }

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Service(v);
            }
        });
        Delete=findViewById(R.id.DeleteUser);
        Delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Delete();
                Service(v);
            }
        });
        logOut=findViewById(R.id.logoutUser);
        logOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logOut();

            }
        });
    }

    public void Delete(){

        //TODO remove this when putting into production:;

        String TestUrl = "http://coms-309-hv-07.cs.iastate.edu:8080/user/"+currentUser.ID;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE,TestUrl,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status=response.getString("status");
                    if(status.equals("1")){
                        Toast.makeText(getApplicationContext(),"Account deleted",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Server Issue",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }
    public void Service(View view){
        Intent intent= new Intent(this, ServicesActivity.class);
        Bundle b=new Bundle();
        System.out.println(currentUser.ID);
        b.putParcelable("UserData",currentUser);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void logOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
