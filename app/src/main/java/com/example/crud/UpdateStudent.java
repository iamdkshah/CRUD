package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.database.DatabaseHelper;

public class UpdateStudent extends AppCompatActivity {
    EditText nameEt, emailEt, phoneEt;
    Button btnBack, btnUpdate;

    String id, name, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        nameEt = findViewById(R.id.nameEt);
        emailEt = findViewById(R.id.emailEt);
        phoneEt = findViewById(R.id.phoneEt);
        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");

        nameEt.setText(name);
        emailEt.setText(email);
        phoneEt.setText(phone);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(UpdateStudent.this);
                String ename = nameEt.getText().toString();
                String eemail = emailEt.getText().toString();
                String ephone = phoneEt.getText().toString();

                boolean status = dbHelper.editStudents(Integer.parseInt(id),ename,eemail,ephone);

                if (status){
                    Toast.makeText(UpdateStudent.this, "Update Success!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(UpdateStudent.this,MainActivity.class);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(UpdateStudent.this, "Error!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(UpdateStudent.this,MainActivity.class);
                startActivity(intent_back);
            }
        });
    }
}
