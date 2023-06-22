package com.example.qurandiary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, class_input, age_input;
    Button update_button, delete_button;

    String name,id,className;


    int age;
    int para;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        class_input = findViewById(R.id.class_input2);
        age_input = findViewById(R.id.age_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        // First, we call this
        getAndSetIntentData();

        // Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                className = class_input.getText().toString().trim(); // Remove unnecessary parsing
                age = Integer.parseInt(age_input.getText().toString().trim());
                myDB.updateData(id, name, age, className, para);
                finish();
            }
        });


        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("class") && getIntent().hasExtra("age") &&
                getIntent().hasExtra("para")) {
            // Getting Data from Intent
            id = String.valueOf(getIntent().getIntExtra("id", 0));
            name = getIntent().getStringExtra("name");
            className = getIntent().getStringExtra("class");
            age = getIntent().getIntExtra("age", 0);
            para = getIntent().getIntExtra("para", 0); // Initialize para variable

            // Setting Intent Data
            name_input.setText(name);
            class_input.setText(className);
            age_input.setText(String.valueOf(age));
            Log.d("stev", name + " " + className + " " + age);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }



    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(Integer.parseInt(id));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // No action needed
            }
        });
        builder.create().show();
    }
}

