package com.csc475.studentdata;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.csc475.studentdata.database.StudentDataContract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Assuming you have the list of students from your SQLite database
        ArrayList<Student> studentList = // Get the ArrayList of Student objects from your database

                StudentAdapter studentAdapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(studentAdapter);

    }
}