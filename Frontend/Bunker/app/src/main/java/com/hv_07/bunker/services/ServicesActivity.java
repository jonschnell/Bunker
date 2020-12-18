package com.hv_07.bunker.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hv_07.bunker.R;
import com.hv_07.bunker.chat.ChatActivity;
import com.hv_07.bunker.friends.FriendsActivity;
import com.hv_07.bunker.login.LoginActivity;
import com.hv_07.bunker.settings.SettingsActivity;
import com.hv_07.bunker.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity implements ServicesContract.View{
    private FloatingActionButton AddService;
    ListView simpleList;
    User currentUser;
    private String DatabaseURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Bundle b = this.getIntent().getExtras();
        if(b!=null){
            currentUser=b.getParcelable("UserData");
        }
        getServices();
        AddService=findViewById(R.id.NewServiceButton);
        final ServicesContract.View V=this;
        AddService.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AddService(new Intent(V.getApplicationContextServices(), AddServiceActivity.class));;
            }
        });

    }
    public void getServices(){
        DatabaseURL="http://coms-309-hv-07.cs.iastate.edu:8080/credential/getAll/"+currentUser.ID;
        RequestQueue requestQueue= Volley.newRequestQueue(this.getApplicationContext());
        JSONObject jsonBody = new JSONObject();
        final ServicesActivity V=this;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, DatabaseURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(V,"Got response from Server",Toast.LENGTH_LONG).show();
                try {
                    JSONArray arr = response.getJSONArray("credentials");
                    ArrayList<Service> services = new ArrayList<Service>();
                    for(int i=0;i<arr.length();i++){
                        String name=arr.getJSONObject(i).getString("credentialName");
                        String username=arr.getJSONObject(i).getString("userName");
                        String password=arr.getJSONObject(i).getString("password");
                        String id=arr.getJSONObject(i).getString("id");
                        System.out.println(id);
                        services.add(new Service(name,username,password,id));

                    }
                    ServicesAdapter s = new ServicesAdapter(services);
                    RecyclerView rvContacts = (RecyclerView) findViewById(R.id.servicesView);
                    rvContacts.setAdapter(s);
                    rvContacts.setLayoutManager(new LinearLayoutManager(V));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(V,"Error connecting to Server",Toast.LENGTH_LONG).show();
                System.out.println(error);
            }
        });

        requestQueue.add(request);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.services_activity, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_friends) {
            Intent intent=new Intent(this, FriendsActivity.class);
            Bundle b=new Bundle();
            b.putParcelable("UserData",currentUser);
            intent.putExtras(b);
            startActivity(intent);
            return true;
        }
        if(id==R.id.action_settings){
            Intent intent=new Intent(this, SettingsActivity.class);
            Bundle b=new Bundle();
            b.putParcelable("UserData",currentUser);
            intent.putExtras(b);
            startActivity(intent);
            return true;
        }
        if(id==R.id.action_chat){
            Intent intent = new Intent(this, ChatActivity.class);
            Bundle b = new Bundle();
            b.putParcelable("UserData", currentUser);
            intent.putExtras(b);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public Context getApplicationContextServices() {
        return this;
    }

    @Override
    public void AddService(Intent intent) {
        Bundle b=new Bundle();
        b.putParcelable("UserData",currentUser);
        intent.putExtras(b);
        startActivity(intent);
    }
}