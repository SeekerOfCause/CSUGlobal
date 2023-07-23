package com.csc475.studentdata.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class StudentDataContract {

    private StudentDataContract() {

    }

    public static class StudentDataEntry implements BaseColumns {

        public static final String TABLE_NAME = "StudentData";
        public static final String COLUMN_FIRST_NAME = "First";
        public static final String COLUMN_LAST_NAME = "Last";
        public static final String COLUMN_GRADE = "Grade";
        public static final String COLUMN_ID = "ID";

    }

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + StudentDataEntry.TABLE_NAME + " (" +
            StudentDataEntry._ID + " INTEGER PRIMARY KEY," +
            StudentDataEntry.COLUMN_FIRST_NAME + " TEXT," +
            StudentDataEntry.COLUMN_LAST_NAME + " TEXT," +
            StudentDataEntry.COLUMN_GRADE + " TEXT," +
            StudentDataEntry.COLUMN_ID + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentDataEntry.TABLE_NAME;

    public static class StudentDataHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "StudentData.db";

        public StudentDataHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    public void write(Context context, Student student) {
        StudentDataHelper dbHelper = new StudentDataHelper(context);

        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        double grade = student.getGrade();
        int id = student.getId();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(StudentDataEntry.COLUMN_FIRST_NAME, firstName);
        cv.put(StudentDataEntry.COLUMN_LAST_NAME, lastName);
        cv.put(StudentDataEntry.COLUMN_GRADE, grade);
        cv.put(StudentDataEntry.COLUMN_ID, id);
        db.insert(StudentDataEntry.TABLE_NAME, null, cv);
    }

    public Student read(Context context, String firstName, String lastName) {

        Student student = null;

        SQLiteDatabase db = new StudentDataHelper(context).getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                StudentDataEntry.COLUMN_LAST_NAME,
                StudentDataEntry.COLUMN_FIRST_NAME,
                StudentDataEntry.COLUMN_GRADE,
                StudentDataEntry.COLUMN_ID,
        };

        String selection = StudentDataEntry.COLUMN_FIRST_NAME + " = ? AND " +
                StudentDataEntry.COLUMN_LAST_NAME + " = ?";
        String[] selectionArgs = {firstName, lastName};

        Cursor cursor = db.query(
                StudentDataEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Extract the data from the cursor and create a Student object
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_ID));
            double grade = cursor.getDouble(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_GRADE));

            // Assuming your Student class has a constructor that takes firstName, lastName, grade, and id as parameters
            student = new Student(firstName, lastName, grade, id);

            cursor.close();
        }

        return student;
    }

    public ArrayList<Student> readAll(Context context) {
        StudentDataHelper dbHelper = new StudentDataHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                StudentDataEntry.COLUMN_LAST_NAME,
                StudentDataEntry.COLUMN_FIRST_NAME,
                StudentDataEntry.COLUMN_GRADE,
                StudentDataEntry.COLUMN_ID,
        };

        Cursor cursor = db.query(
                StudentDataEntry.TABLE_NAME,
                projection,
                null, // No selection, so it retrieves all rows
                null,
                null,
                null,
                null
        );

        ArrayList<Student> studentList = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Extract the data from the cursor for each row and create a Student object
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_LAST_NAME));
                double grade = cursor.getDouble(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_GRADE));
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_ID));

                // Assuming your Student class has a constructor that takes firstName, lastName, grade, and id as parameters
                Student student = new Student(firstName, lastName, grade, id);

                studentList.add(student);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return studentList;
    }

    public void deleteStudentByName(Context context, String firstName, String lastName) {
        StudentDataHelper dbHelper = new StudentDataHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = StudentDataEntry.COLUMN_FIRST_NAME + " = ? AND " +
                StudentDataEntry.COLUMN_LAST_NAME + " = ?";
        String[] selectionArgs = {firstName, lastName};

        db.delete(StudentDataEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void deleteStudentById(Context context, int id) {
        StudentDataHelper dbHelper = new StudentDataHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = StudentDataEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        db.delete(StudentDataEntry.TABLE_NAME, selection, selectionArgs);
    }

}
