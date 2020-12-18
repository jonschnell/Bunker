package com.hv_07.bunker.services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hv_07.bunker.R;
import com.hv_07.bunker.user.User;

import org.json.JSONException;
import org.json.JSONObject;

public class AddServiceActivity extends AppCompatActivity {
    private Button Add;
    User currentUser;
    EditText Email,Name,Password;
    String DatabaseURL="http://coms-309-hv-07.cs.iastate.edu:8080/credential";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        Bundle b=this.getIntent().getExtras();
        if(b!=null){
            currentUser=b.getParcelable("UserData");
        }
        Add=findViewById(R.id.AddServiceButton);
        Email=findViewById(R.id.EmailAddress);
        Name=findViewById(R.id.ServiceName);
        Password=findViewById(R.id.Password);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Service(v);
            }
        });
        Add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString();
                String name=Name.getText().toString();
                String password=Password.getText().toString();

                AddCredential(email,name,password);
                Service(v);
            }
        });
    }

    public void AddCredential(String email,String name,String password){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("credentialName",name);
            jsonBody.put("userName", email); //Key-Value pair that is very similar to how PostMan works
            jsonBody.put("password", password); //Key-Value pair that is very similar to how PostMan works
            System.out.println(jsonBody);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, DatabaseURL+"/"+currentUser.ID,jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String ID=response.getString("id");
                        System.out.println("ID is "+response.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY1", error.toString());
                }
            });

            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void Service(View view){
        Intent intent= new Intent(this, ServicesActivity.class);
        Bundle b=new Bundle();
        b.putParcelable("UserData",currentUser);
        intent.putExtras(b);
        startActivity(intent);
    }
}
