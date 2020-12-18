package com.hv_07.bunker.friends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hv_07.bunker.R;
import com.hv_07.bunker.services.Service;
import com.hv_07.bunker.services.ServicesActivity;
import com.hv_07.bunker.services.ServicesAdapter;
import com.hv_07.bunker.settings.SettingsActivity;
import com.hv_07.bunker.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {
    private FloatingActionButton AddService;
    // Array of strings...
    ListView simpleList;
    User currentUser;
    String friendList[] = {"Jon","Isabel","Ben","Brent"};
    private String DatabaseURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Service(v);
            }
        });

        Bundle b = this.getIntent().getExtras();
        if(b!=null){
            currentUser=b.getParcelable("UserData");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, friendList);
        simpleList.setAdapter(arrayAdapter);
        AddService=findViewById(R.id.NewServiceButton);
        AddService.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                AddFriend(view);
            }
        });
    }

    private void updateFriendList(){
        DatabaseURL = "http://coms-309-hv-07.cs.iastate.edu:8080/friend/getAll/"+currentUser.ID;
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        JSONObject jsonBody = new JSONObject();
        final FriendsActivity V = this;
        System.out.println(DatabaseURL);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, DatabaseURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    System.out.println("Turning Response into Array");
                    System.out.println("Creating array of friends");
                    ArrayList<Friend> services = new ArrayList<Friend>();
                    for(int i=0;i<response.length();i++){
                        String name=response.getJSONObject(i).getString("friendName");
                        String id=response.getJSONObject(i).getString("id");
                        System.out.println(name+" "+id);
                        friendList[i] = name;
                    }
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
            Intent intent=new Intent(this, this.getClass());
            Bundle b=new Bundle();
            b.putParcelable("UserDataFromServices",currentUser);
            intent.putExtras(b);
            startActivity(intent);
            return true;
        }
        if(id==R.id.action_settings){
            Intent intent=new Intent(this, SettingsActivity.class);
            Bundle b=new Bundle();
            b.putParcelable("UserDataFromServices",currentUser);
            intent.putExtras(b);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Intent starters
    public void AddFriend(View view){
        Intent intent= new Intent(this, AddFriendActivity.class);
        Bundle b=new Bundle();
        b.putParcelable("UserDataFromServices",currentUser);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void Service(View view){
        Intent intent= new Intent(this, ServicesActivity.class);
        Bundle b=new Bundle();
        System.out.println(currentUser.ID);
        b.putParcelable("UserData",currentUser);
        intent.putExtras(b);
        startActivity(intent);
    }
}