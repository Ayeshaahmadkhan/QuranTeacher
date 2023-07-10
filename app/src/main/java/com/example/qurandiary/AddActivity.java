
package com.example.qurandiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

public class AddActivity extends AppCompatActivity {

    EditText name_input, class_input, age_input, para_input,sabqi_input, manzil_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        class_input = findViewById(R.id.class_input);
        age_input = findViewById(R.id.age_input);
        para_input = findViewById(R.id.para_input);
        add_button = findViewById(R.id.add_button);
        sabqi_input = findViewById(R.id.sabqi_input);
        manzil_input = findViewById(R.id.manzil_input);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_input.getText().toString().trim();
                String studentClass = class_input.getText().toString().trim();
                String ageString = age_input.getText().toString().trim();
                String paraString = para_input.getText().toString().trim();
                String sabqi = sabqi_input.getText().toString().trim();
                String manzil = manzil_input.getText().toString().trim();

                if (!name.isEmpty() && !studentClass.isEmpty() && !ageString.isEmpty() && !paraString.isEmpty()) {
                    int age = Integer.parseInt(ageString);
                    int para = Integer.parseInt(paraString);

                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addStudent(name, age, studentClass, para,sabqi,manzil);

                    name_input.setText("");
                    class_input.setText("");
                    age_input.setText("");
                    para_input.setText("");
                    sabqi_input.setText("");
                    manzil_input.setText("");

                    Toast.makeText(AddActivity.this, "Student added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


