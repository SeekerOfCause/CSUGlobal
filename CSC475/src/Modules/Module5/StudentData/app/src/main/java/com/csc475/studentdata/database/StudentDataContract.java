package com.csc475.studentdata.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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

    public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + StudentDataEntry.TABLE_NAME + " (" + StudentDataEntry._ID + " INTEGER PRIMARY KEY," + StudentDataEntry.COLUMN_FIRST_NAME + " TEXT," + StudentDataEntry.COLUMN_LAST_NAME + " TEXT," + StudentDataEntry.COLUMN_GRADE + " TEXT," + StudentDataEntry.COLUMN_ID + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + StudentDataEntry.TABLE_NAME;

    public static class StudentDataHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "StudentData.db";

        public StudentDataHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            SQLiteDatabase db = this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public void write(Student student) {

            SQLiteDatabase db = this.getWritableDatabase();

            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            double grade = student.getGrade();
            int id = student.getId();

            ContentValues cv = new ContentValues();
            cv.put(StudentDataEntry.COLUMN_FIRST_NAME, firstName);
            cv.put(StudentDataEntry.COLUMN_LAST_NAME, lastName);
            cv.put(StudentDataEntry.COLUMN_GRADE, grade);
            cv.put(StudentDataEntry.COLUMN_ID, id);
            db.insert(StudentDataEntry.TABLE_NAME, null, cv);
            db.close();


        }

        public boolean updateStudent(Student student, int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            boolean exists;

            if (student.getId() == id) {
                exists = false;
            } else {
                exists = checkForEntryById(student.getId());
            }

            if (!exists) {
                values.put(StudentDataEntry.COLUMN_FIRST_NAME, student.getFirstName());
                values.put(StudentDataEntry.COLUMN_LAST_NAME, student.getLastName());
                values.put(StudentDataEntry.COLUMN_GRADE, student.getGrade());
                values.put(StudentDataEntry.COLUMN_ID, student.getId());

                try {
                    db.update(StudentDataEntry.TABLE_NAME, values, StudentDataEntry.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
                    db.close();
                    return true;
                } catch (SQLiteException e) {
                    return false;
                }
            } else {
                return false;
            }
        }

        public boolean addStudent(Student student) {

            SQLiteDatabase db = this.getWritableDatabase();

            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            double grade = student.getGrade();
            int id = student.getId();

            if (checkForEntryById(student.getId())) {
                return false;
            } else {
                ContentValues cv = new ContentValues();
                cv.put(StudentDataEntry.COLUMN_FIRST_NAME, firstName);
                cv.put(StudentDataEntry.COLUMN_LAST_NAME, lastName);
                cv.put(StudentDataEntry.COLUMN_GRADE, grade);
                cv.put(StudentDataEntry.COLUMN_ID, id);
                db.insert(StudentDataEntry.TABLE_NAME, null, cv);
                db.close();
                return true;
            }
        }


        public Student read(String firstName, String lastName) {

            Student student = null;

            SQLiteDatabase db = this.getReadableDatabase();

            String[] projection = {BaseColumns._ID, StudentDataEntry.COLUMN_LAST_NAME, StudentDataEntry.COLUMN_FIRST_NAME, StudentDataEntry.COLUMN_GRADE, StudentDataEntry.COLUMN_ID,};

            String selection = StudentDataEntry.COLUMN_FIRST_NAME + " = ? AND " + StudentDataEntry.COLUMN_LAST_NAME + " = ?";
            String[] selectionArgs = {firstName, lastName};

            Cursor cursor = db.query(StudentDataEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                // Extract the data from the cursor and create a Student object
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_ID));
                double grade = cursor.getDouble(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_GRADE));

                // Assuming your Student class has a constructor that takes firstName, lastName, grade, and id as parameters
                student = new Student(firstName, lastName, grade, id);

                cursor.close();
            }
            db.close();
            return student;
        }

        public Student read(int id) {

            Student student = null;

            SQLiteDatabase db = this.getReadableDatabase();

            String[] projection = {BaseColumns._ID, StudentDataEntry.COLUMN_LAST_NAME, StudentDataEntry.COLUMN_FIRST_NAME, StudentDataEntry.COLUMN_GRADE, StudentDataEntry.COLUMN_ID,};

            String selection = StudentDataEntry.COLUMN_ID + " = ?";
            String[] selectionArgs = {String.valueOf(id)};

            Cursor cursor = db.query(StudentDataEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                // Extract the data from the cursor and create a Student object
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_LAST_NAME));
                double grade = cursor.getDouble(cursor.getColumnIndexOrThrow(StudentDataEntry.COLUMN_GRADE));

                // Assuming your Student class has a constructor that takes firstName, lastName, grade, and id as parameters
                student = new Student(firstName, lastName, grade, id);

                cursor.close();
                db.close();
            }
            return student;
        }

        public ArrayList<Student> readAll() {
            SQLiteDatabase db = this.getReadableDatabase();

            String[] projection = {BaseColumns._ID, StudentDataEntry.COLUMN_LAST_NAME, StudentDataEntry.COLUMN_FIRST_NAME, StudentDataEntry.COLUMN_GRADE, StudentDataEntry.COLUMN_ID,};

            Cursor cursor = db.query(StudentDataEntry.TABLE_NAME, projection, null, // No selection, so it retrieves all rows
                    null, null, null, null);

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
                db.close();
            }
            return studentList;
        }

        public void deleteStudentByName(Context context, String firstName, String lastName) {
            SQLiteDatabase db = this.getWritableDatabase();

            String selection = StudentDataEntry.COLUMN_FIRST_NAME + " = ? AND " + StudentDataEntry.COLUMN_LAST_NAME + " = ?";
            String[] selectionArgs = {firstName, lastName};

            db.delete(StudentDataEntry.TABLE_NAME, selection, selectionArgs);
            db.close();
        }

        public void deleteStudentById(Context context, int id) {

            SQLiteDatabase db = this.getWritableDatabase();

            String selection = StudentDataEntry.COLUMN_ID + " = ?";
            String[] selectionArgs = {String.valueOf(id)};

            db.delete(StudentDataEntry.TABLE_NAME, selection, selectionArgs);
            db.close();
        }

        public int databaseSize() {
            SQLiteDatabase db = this.getReadableDatabase();
            String countQuery = "SELECT COUNT(*) FROM " + StudentDataEntry.TABLE_NAME;
            Cursor cursor = db.rawQuery(countQuery, null);
            int count = 0;
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    count = cursor.getInt(0);
                }
                cursor.close();
                db.close();
            }
            return count;
        }


        private boolean checkForEntryById(int id) {
            boolean idCheck = false;
            SQLiteDatabase db = this.getReadableDatabase();
            String[] idColumns = {StudentDataEntry.COLUMN_ID};
            String idSelection = StudentDataEntry.COLUMN_ID + " = ?";
            String[] idSelectionArgs = {String.valueOf(id)};


            try {
                Cursor idCursor = db.query(StudentDataEntry.TABLE_NAME, idColumns, idSelection, idSelectionArgs, null, null, null, null);
                idCheck = (idCursor.getCount() > 0);
                idCursor.close();
            } catch (SQLException b) {
                System.out.println("No column (id) " + b);
            }

            return idCheck;
        }

    }


}
