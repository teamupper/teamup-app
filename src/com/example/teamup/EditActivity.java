package com.example.teamup;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        final EditText entityName = (EditText) findViewById(R.id.entityName);
        final EditText key1 = (EditText) findViewById(R.id.propertyKey1);
        final EditText val1 = (EditText) findViewById(R.id.propertyValue1);
        final EditText key2 = (EditText) findViewById(R.id.propertyKey2);
        final EditText val2 = (EditText) findViewById(R.id.propertyValue2);
        final EditText key3 = (EditText) findViewById(R.id.propertyKey3);
        final EditText val3 = (EditText) findViewById(R.id.propertyValue3);

        Button loginButton = (Button) findViewById(R.id.saveButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {          
            }
        });
    }
}