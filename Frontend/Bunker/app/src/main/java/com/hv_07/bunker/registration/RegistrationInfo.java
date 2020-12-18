package com.hv_07.bunker.registration;

import android.content.Intent;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hv_07.bunker.login.LoginActivity;

public class RegistrationInfo {
    private String user;
    private String address;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String url="https://10.24.226.105:8080/user";
    private RegistrationContract.View V;
    public RegistrationInfo(String user,String address,String firstName, String lastName, String email, String phone, String password,RegistrationContract.View V){
        this.user=user;
        this.address=address;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phone=phone;
        this.password=password;
        this.V=V;
    }
    //Getters:
    public String getUser(){
        return this.user;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getAddress(){
        return this.address;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public RegistrationContract.View getView(){
        return this.V;
    }
    public void SendInfo() throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("user", user); //Key-Value pair that is very similar to how PostMan works
        jsonBody.put("address", address); //Key-Value pair that is very similar to how PostMan works
        jsonBody.put("firstName", firstName); //Key-Value pair that is very similar to how PostMan works
        jsonBody.put("lastName", lastName); //Key-Value pair that is very similar to how PostMan works
        jsonBody.put("email", email); //Key-Value pair that is very similar to how PostMan works
        jsonBody.put("phone", phone); //Key-Value pair that is very similar to how PostMan works
        jsonBody.put("password", password); //Key-Value pair that is very similar to how PostMan works*/

        RequestQueue request = Volley.newRequestQueue(V.getApplicationContextRegistration());
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Got Response From Server");
                System.out.println(response.toString());
                V.startActivityRegistration(new Intent(V.getApplicationContextRegistration(), LoginActivity.class));
                Toast.makeText(V.getApplicationContextRegistration(),"You have been successfully registered.",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(V.getApplicationContextRegistration(),"There is an error connecting to the server, please try again.",Toast.LENGTH_LONG).show();
            }
        });
        request.add(jsonRequest);
    }
}
