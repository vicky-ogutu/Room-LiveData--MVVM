package com.example.rom1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUser extends AppCompatActivity {

    public static final String EXTRA_FIRSTNAME = "com.example.rom1.EXTRA_FIRSTNAME";
    public static final String EXTRA_LASTNAME = "com.example.rom1.EXTRA_LASTNAME";
    public static final String EXTRA_UID = "com.example.rom1.EXTRA_UID";

    EditText firstEdit, lastEdit;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        firstEdit = findViewById(R.id.firstname);
        lastEdit = findViewById(R.id.lastname);
        sendBtn =  findViewById(R.id.send);
        
        
        getSupportActionBar();
        
        Intent intent =getIntent();
        if (intent.hasExtra(EXTRA_UID)){
            setTitle("Edit Student");
            firstEdit.setText(intent.getStringExtra(EXTRA_FIRSTNAME));
            lastEdit.setText(intent.getStringExtra(EXTRA_LASTNAME));
        }else{
            setTitle("Add student");
        }

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstEdit.getText().toString();
                String lastName = lastEdit.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(EXTRA_FIRSTNAME, firstName);
                intent.putExtra(EXTRA_LASTNAME, lastName);

                int id = getIntent().getIntExtra(EXTRA_UID, -1);

                if (id!= -1){
                    intent.putExtra(EXTRA_UID, id);
                }

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}