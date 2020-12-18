package com.hv_07.bunker.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hv_07.bunker.MainActivity;
import com.hv_07.bunker.R;
import com.hv_07.bunker.user.User;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    EditText usernameET,passET;
    public User currentUser=new User();
    private String username;
    private String password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameET=(EditText)findViewById(R.id.username);
        passET=(EditText)findViewById(R.id.password);
        Login=findViewById(R.id.Login);
        final LoginActivity V=this;
        Login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                username=usernameET.getText().toString();
                password=passET.getText().toString();
                LoginInfo login = new LoginInfo(username,password,V);
                try {
                    login.LoginAttempt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startIntentServices(Intent intent) {
        Bundle b = new Bundle();
        b.putParcelable("UserData",currentUser);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public Context getApplicationContextLogin() {
        return this;
    }

    @Override
    public User getUser() {
        return currentUser;
    }
}