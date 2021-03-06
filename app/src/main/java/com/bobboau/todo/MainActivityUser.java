package com.bobboau.todo;

import com.bobboau.todo.TaskListAdapterUser.LIST_MODE;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

/**
 *
 * main class of the application, effectively IS the application
 *
 */
public class MainActivityUser extends Activity {

    /**
     * this is the adapter for the task list, it is referenced a bunch of places so let's keep track of it rather than hunting it down every time
     */
    TaskListAdapterUser task_list_adapter;

    /**
     * these buttons are referenced a lot too
     */
    ToggleButton complete_button;
    ToggleButton incomplete_button;
    Button sign_out;
    /**
     * function that set's up the UI
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get all of the UI elements we are going to be adding event listeners to or otherwise manipulating
        final Button add_new_button = (Button) findViewById(R.id.btn_new);
        complete_button = (ToggleButton) findViewById(R.id.btn_complete);
        incomplete_button = (ToggleButton) findViewById(R.id.btn_incomplete);
        sign_out = (Button)findViewById(R.id.signout);
        final ListView task_list_view = (ListView) findViewById(R.id.view_task_list);

        //add the complete view listener
        complete_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                showComplete();
            }
        });


        sign_out.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent newForm = new Intent(MainActivityUser.this,LoginActivity.class);
                startActivity(newForm);
            }
        });

        //add the incomplete view listener
        incomplete_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                showIncomplete();
            }
        });

        //add the listener that adds new tasks
        add_new_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                addNew();
            }
        });

        //set up the listview and it's adapter
       task_list_adapter = new TaskListAdapterUser(this);
        task_list_view.setAdapter(
                task_list_adapter
        );

        //show the tasks
        incomplete_button.setChecked(false);
        showIncomplete();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private void showComplete()
    {
        task_list_adapter.setMode(LIST_MODE.COMPLETE);
        complete_button.setChecked(true);
        incomplete_button.setChecked(false);
    }

    private void showIncomplete()
    {
        task_list_adapter.setMode(LIST_MODE.INCOMPLETE);
        incomplete_button.setChecked(true);
        complete_button.setChecked(false);
    }

    private void addNew()
    {
        //get the description from the user
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("New Task");
        alert.setMessage("Enter the description of your new task");

        final EditText input = new EditText(this);
        input.setEnabled(true);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String description = input.getText().toString();
                task_list_adapter.addTask(description);
            }
        });

        alert.setNegativeButton("Cancel", null);

        alert.show();
    }
}
