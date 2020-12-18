package com.hv_07.bunker.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class AddFriendActivity extends AppCompatActivity {
    private Button Add;
    private TextView text;
    User currentUser;
    String DatabaseURL="http://coms-309-hv-07.cs.iastate.edu:8080";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        //This method grabs the information from the previous activity and uses it here.
        Bundle b=this.getIntent().getExtras();
        if(b!=null){
            currentUser = b.getParcelable("UserDataFromServices");
        }

        text = (TextView)findViewById(R.id.textView_User);
        text.setText(R.string.username);

        final EditText etfUser = (EditText) findViewById(R.id.editTextFriendUser);


        Add=findViewById(R.id.button_addFriend);
        Add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String fUser = etfUser.getText().toString();

                AddFriend(fUser);
            }
        });
    }

    public void AddFriend(String friendUsername){
        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("friendName", friendUsername);
            System.out.println(jsonBody);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, DatabaseURL+"/friend/"+currentUser.ID,jsonBody,new Response.Listener<JSONObject>() {
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
        } catch (JSONException e){
            e.printStackTrace();
        }

    }
}