package com.csc475.studentdata;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csc475.studentdata.adapter.StudentAdapter;
import com.csc475.studentdata.database.RandomStudents;
import com.csc475.studentdata.database.Student;
import com.csc475.studentdata.database.StudentDataContract;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private StudentDataContract.StudentDataHelper dbHelper;

    private ArrayList<Student> studentList;

    private View view;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.student_info_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHelper = new StudentDataContract.StudentDataHelper(this);
        RandomStudents initialStudents = new RandomStudents(this);

        getAdapter();

        ImageButton deleteStudent = findViewById(R.id.delete_option);
        ImageButton addStudent = findViewById(R.id.add_option);
        ImageButton searchStudent = findViewById(R.id.search_option);
        ImageButton updateStudent = findViewById(R.id.update_option);
        deleteStudent.setOnClickListener(e -> showModal(0));
        addStudent.setOnClickListener(e -> showModal(1));
        searchStudent.setOnClickListener(e -> showModal(2));
        updateStudent.setOnClickListener(e -> showModal(3));


    }

    private void getAdapter() {
        studentList = dbHelper.readAll();
        StudentAdapter studentAdapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(studentAdapter);
    }

    private void clearFilters() {
    }

    private void showModal(int i) {
        // Inflate the layout for the delete modal
        view = getLayoutInflater().inflate(R.layout.cardview_options, null);

        // Create and customize the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        // Add any other customizations to the dialog
        setModal(view, i);

        // Show the AlertDialog
        alertDialog = builder.create();
        alertDialog.setOnCancelListener(e -> getAdapter());
        alertDialog.setOnDismissListener(f -> getAdapter());
        alertDialog.show();

    }


    private void setModal(View view, int i) {
        switchSearchReturn(view, 0);
        TextView title = view.findViewById(R.id.student_title);
        TextView gradeTitle = view.findViewById(R.id.grade_input_title);
        EditText grade = view.findViewById(R.id.grade_input_entry);
        TextView idTitle = view.findViewById(R.id.student_id_title);
        EditText id = view.findViewById(R.id.id_input_entry);
        Button submit = view.findViewById(R.id.submit_button);

        switch (i) {
            case (0):
                //delete
                title.setText(R.string.delete);
                gradeTitle.setText(R.string.or);
                grade.setVisibility(View.INVISIBLE);
                break;
            case (1):
                //add
                title.setText(R.string.add);
                break;
            case (2):
                //search
                title.setText(R.string.search);
                gradeTitle.setText(R.string.or);
                grade.setVisibility(View.INVISIBLE);
                break;
            case (3):
                //update
                title.setText(R.string.update);
                gradeTitle.setText(R.string.or);
                grade.setVisibility(View.INVISIBLE);
                break;
            default:
                title.setText("Default");
        }
        submit.setOnClickListener(e -> getCallbackFunctions(view, i));
    }

    private void getCallbackFunctions(View view, int v) {
        switch (v) {
            case (0):
                //delete
                deleteStudent(view);
                break;
            case (1):
                //add
                addStudent(view);
                break;
            case (2):
                //search
                searchStudent(view);
                break;
            case (3):
                //update
                updateStudent(view);
                break;
            default:
        }
    }

    private void deleteStudent(View view) {
        StudentDataContract.StudentDataHelper dbHelper = new StudentDataContract.StudentDataHelper(view.getRootView().getContext());
        Student student = null;


        Button delete = view.findViewById(R.id.return_button);
        delete.setText("Delete Student");

        student = getStudent();
        if (student != null) {
            setReturn(student);
            Student finalStudent = student;
            delete.setOnClickListener(e -> {
                new AlertDialog.Builder(view.getContext()).setMessage(R.string.student_delete).setPositiveButton(getString(R.string.confirm_delete), (dialog, which) -> {
                    dbHelper.deleteStudentByName(this, finalStudent.getFirstName(), finalStudent.getLastName());
                    toastIt("Student Deleted");
                    alertDialog.dismiss();
                }).setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                }).create().show();
            });
        }
    }

    private Student getStudent() {
        Student student = null;
        EditText firstName = view.findViewById(R.id.first_name_input);
        EditText lastName = view.findViewById(R.id.last_name_input);
        EditText idInput = view.findViewById(R.id.id_input_entry);
        String first, last;
        int id;

        if ((TextUtils.isEmpty(firstName.getText().toString()) || TextUtils.isEmpty(lastName.getText().toString())) && TextUtils.isEmpty(idInput.getText().toString())) {
            toastIt("Please enter search parameters...");
        } else {
            if (TextUtils.isEmpty(firstName.getText().toString()) || TextUtils.isEmpty(lastName.getText().toString())) {
                ;
                id = Integer.parseInt(idInput.getText().toString());
                try {
                    student = dbHelper.read(id);
                } catch (Exception e) {
                    toastIt("Please check your input");
                }
            } else {
                first = firstName.getText().toString();
                last = lastName.getText().toString();
                try {
                    student = dbHelper.read(first, last);
                } catch (Exception p) {
                    toastIt("Please check your input");
                }
            }
            if (student == null) {
                toastIt("Please enter required input");
            }
        }
        return student;
    }


    private void addStudent(View view) {
        EditText firstName = view.findViewById(R.id.first_name_input);
        EditText lastName = view.findViewById(R.id.last_name_input);
        EditText idInput = view.findViewById(R.id.id_input_entry);
        EditText gradeInput = view.findViewById(R.id.grade_input_entry);
        boolean hasInputs = true;
        EditText[] inputs = {firstName, lastName, idInput, gradeInput};

        for (int i = 0; i < 4; i++) {
            if (TextUtils.isEmpty(inputs[i].getText().toString())) {
                hasInputs = false;
            }
        }

        if (!hasInputs) {
            toastIt("Please enter all information");
        } else {
            Button add = view.findViewById(R.id.return_button);
            add.setText(getString(R.string.add));

            Student student = new Student(firstName.getText().toString(), lastName.getText().toString(), Double.parseDouble(gradeInput.getText().toString()), Integer.parseInt(idInput.getText().toString()));

            setReturn(student);

            add.setOnClickListener(e -> {
                new AlertDialog.Builder(view.getContext()).setMessage(R.string.student_added).setPositiveButton(getString(R.string.confirm_add), (dialog, which) -> {
                    boolean added = dbHelper.addStudent(student);
                    if (added) {
                        toastIt("Student Added");
                        alertDialog.dismiss();
                    } else {
                        switchSearchReturn(view, 0);
                        toastIt("Unable to add student, please check their information");
                    }
                }).setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                }).create().show();
            });
        }

    }

    private void searchStudent(View view) {

        Button close = view.findViewById(R.id.return_button);
        close.setText("Close");

        Student student = getStudent();

        if (student != null) {
            setReturn(student);
            close.setOnClickListener(e -> alertDialog.dismiss());
        }
    }

    private void updateStudent(View view) {

        Button find = view.findViewById(R.id.return_button);
        find.setText("Update this Student");

        Student student = getStudent();

        if (student != null) {
            setReturn(student);
            find.setOnClickListener(e -> submitUpdate(view, student));
        }

    }

    private void submitUpdate(View view, Student student) {
        EditText firstName = view.findViewById(R.id.first_name_input);
        EditText lastName = view.findViewById(R.id.last_name_input);
        EditText idInput = view.findViewById(R.id.id_input_entry);
        EditText gradeInput = view.findViewById(R.id.grade_input_entry);
        TextView gradeTitle = view.findViewById(R.id.grade_input_title);
        EditText grade = view.findViewById(R.id.grade_input_entry);

        gradeTitle.setText(R.string.grade_input_title);
        grade.setVisibility(View.VISIBLE);

        firstName.setText(student.getFirstName());
        lastName.setText(student.getLastName());
        idInput.setText(String.valueOf(student.getId()));
        gradeInput.setText(String.valueOf(student.getGrade()));

        switchSearchReturn(view, 0);
        Button submit = view.findViewById(R.id.submit_button);
        submit.setText("Submit Update");
        submit.setOnClickListener(e -> {
            Student update = new Student(firstName.getText().toString(), lastName.getText().toString(), Double.parseDouble(gradeInput.getText().toString()), Integer.parseInt(idInput.getText().toString()));
            new AlertDialog.Builder(view.getContext()).setMessage(R.string.confirm_update).setPositiveButton(getString(R.string.confirm_update), (dialog, which) -> {
                boolean updated = dbHelper.updateStudent(update, student.getId());
                if (updated) {
                    toastIt("Student updated");
                    alertDialog.dismiss();
                } else {
                    switchSearchReturn(view, 0);
                    toastIt("Unable to update student information, please check their information");
                }
            }).setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                firstName.setText(student.getFirstName());
                lastName.setText(student.getLastName());
                idInput.setText(String.valueOf(student.getId()));
                gradeInput.setText(String.valueOf(student.getGrade()));
            }).show();
        });

    }

    private void getStudent(View view) {

    }

    private void toastIt(String s) {
        Toast toast = new Toast(this);
        toast.setText(s);
        toast.show();
    }

    private void switchSearchReturn(View view, int i) {
        ConstraintLayout searchView = view.findViewById(R.id.search_view);
        CardView returnView = view.findViewById(R.id.return_view);

        if (i == 0) {
            searchView.setVisibility(View.VISIBLE);
            returnView.setVisibility(View.GONE);
        } else {
            searchView.setVisibility(View.GONE);
            returnView.setVisibility(View.VISIBLE);
        }
    }

    private void setReturn(Student student) {

        TextView firstRet = view.findViewById(R.id.returned_card_first_name);
        TextView lastRet = view.findViewById(R.id.returned_card_last_name);
        TextView idRet = view.findViewById(R.id.returned_card_id);
        TextView gradeRet = view.findViewById(R.id.returned_card_grade);

        switchSearchReturn(view, 1);
        firstRet.setText(student.getFirstName());
        lastRet.setText(student.getLastName());
        idRet.setText(String.valueOf(student.getId()));
        gradeRet.setText(String.valueOf(student.getGrade()));
    }

}