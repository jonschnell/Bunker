package com.hv_07.bunker.login;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hv_07.bunker.services.ServicesActivity;
import com.hv_07.bunker.user.User;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginInfo {
private String username;
private String password;
private LoginContract.View V;
private String DatabaseURL="http://10.24.226.105:8080/user/login";
    public LoginInfo(String username,String password, LoginContract.View V){
        this.username=username;
        this.password=password;
        this.V=V;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setUsername(String user){
        this.username=user;
    }
    public void setPassword(String pass){
        this.password=pass;
    }
    public void LoginAttempt() throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(V.getApplicationContextLogin());
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("userName", username); //Key-Value pair that is very similar to how PostMan works
        jsonBody.put("password", password); //Key-Value pair that is very similar to how PostMan works
        System.out.println(jsonBody);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, DatabaseURL,jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(responseChecker(response.getString("status"))){
                        V.getUser().setID(response.getString("id"));
                        V.startIntentServices(new Intent(V.getApplicationContextLogin(), ServicesActivity.class));
                    }
                    else{
                        Toast.makeText(V.getApplicationContextLogin(),"Incorrect Username or Password",Toast.LENGTH_LONG).show();
                    }

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

        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(request);
    }
    public boolean responseChecker(String response){
        return response.equals("1");
    }
}
