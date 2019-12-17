package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.adapters.StudentsAdapter;
import com.example.crud.database.DatabaseHelper;
import com.example.crud.models.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edittName, editEmail, editPhone;
    Button btnSubmit;
    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    List<Student> std = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edittName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        btnSubmit = findViewById(R.id.btnSubmit);
        recyclerView = findViewById(R.id.recyclerView);

        StudentsAdapter adapter = new StudentsAdapter(std,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(this);

        getDetails();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = edittName.getText().toString();
                String e = editEmail.getText().toString();
                String p = editPhone.getText().toString();

                Student student = new Student(0,n,e,p);

                if (databaseHelper.addStudent(student)){
                    Toast.makeText(MainActivity.this, "Student added!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getDetails(){
        List<Student> students = databaseHelper.getStudents();
        for (Student student:students){
            std.add(new Student(student.getId(),student.getName(),student.getEmail(),student.getPhone()));
        }
    }
}
