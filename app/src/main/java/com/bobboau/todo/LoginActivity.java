package com.bobboau.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button signButton = (Button) findViewById(R.id.signin);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = ((EditText)findViewById(R.id.txtuser)).getText().toString();
                String password = ((EditText)findViewById(R.id.txtpassword)).getText().toString();
                if (user.equals("admin") && password.equals("admin"))
                {
                    Intent newForm = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(newForm);
                }
                else
                if (user.equals("user") && password.equals("user"))
                {
                    Intent newForm = new Intent(LoginActivity.this,MainActivityUser.class);
                    startActivity(newForm);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "User Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
