package com.example.studentportal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studentportal.models.Course;
import com.example.studentportal.models.Result;
import com.example.studentportal.models.Student;
import com.example.studentportal.models.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_portal.db";
    private static final int DATABASE_VERSION = 2; // increment version for upgrade

    // Table names
    public static final String TABLE_STUDENT = "students";
    public static final String TABLE_TEACHER = "teachers";
    public static final String TABLE_COURSE = "courses";
    public static final String TABLE_ENROLLMENT = "enrollment";
    public static final String TABLE_RESULT = "results";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // STUDENT TABLE (Login via reg_id)
        db.execSQL("CREATE TABLE " + TABLE_STUDENT + " (" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT, " +
                "phone TEXT, " +
                "reg_id TEXT UNIQUE, " +      // used as login ID
                "password TEXT)");

        // TEACHER TABLE (Login via username)
        db.execSQL("CREATE TABLE " + TABLE_TEACHER + " (" +
                "teacher_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT, " +
                "phone TEXT, " +
                "username TEXT UNIQUE, " +    // login ID
                "password TEXT)");

        // COURSE TABLE (credit is REAL now)
        db.execSQL("CREATE TABLE " + TABLE_COURSE + " (" +
                "course_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "course_code TEXT, " +
                "credit REAL, " +
                "teacher_id INTEGER, " +
                "FOREIGN KEY(teacher_id) REFERENCES teachers(teacher_id))");

        // ENROLLMENT TABLE
        db.execSQL("CREATE TABLE " + TABLE_ENROLLMENT + " (" +
                "student_id INTEGER, " +
                "course_id INTEGER, " +
                "PRIMARY KEY(student_id, course_id), " +
                "FOREIGN KEY(student_id) REFERENCES students(student_id), " +
                "FOREIGN KEY(course_id) REFERENCES courses(course_id))");

        // RESULT TABLE
        db.execSQL("CREATE TABLE " + TABLE_RESULT + " (" +
                "student_id INTEGER, " +
                "course_id INTEGER, " +
                "marks REAL, " +
                "grade TEXT, " +
                "PRIMARY KEY(student_id, course_id), " +
                "FOREIGN KEY(student_id) REFERENCES students(student_id), " +
                "FOREIGN KEY(course_id) REFERENCES courses(course_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENROLLMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        onCreate(db);
    }

    public Teacher getTeacherById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM teachers WHERE teacher_id = ?",
                new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            Teacher t = new Teacher(
                    cursor.getInt(cursor.getColumnIndexOrThrow("teacher_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                    cursor.getString(cursor.getColumnIndexOrThrow("username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password"))
            );
            cursor.close();
            return t;
        }
        cursor.close();
        return null;
    }

    public Teacher getTeacherByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM teachers WHERE username = ?",
                new String[]{username}
        );

        if (cursor != null && cursor.moveToFirst()) {
            Teacher t = new Teacher(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            cursor.close();
            return t;
        }
        return null;
    }
    public Student getStudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM students WHERE student_id = ?",
                new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            Student s = new Student(
                    cursor.getInt(cursor.getColumnIndexOrThrow("student_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password")),
                    cursor.getString(cursor.getColumnIndexOrThrow("reg_id"))
            );
            cursor.close();
            return s;
        }
        cursor.close();
        return null;
    }

    public Student getStudentByRegId(String regId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM students WHERE reg_id = ?",
                new String[]{regId}
        );

        if (cursor != null && cursor.moveToFirst()) {
            Student s = new Student(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(5),   // password
                    cursor.getString(4)    // reg_id
            );
            cursor.close();
            return s;
        }
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM students", null);

        if (cursor.moveToFirst()) {
            do {
                Student s = new Student(
                        cursor.getInt(cursor.getColumnIndexOrThrow("student_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("email")),
                        cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                        cursor.getString(cursor.getColumnIndexOrThrow("password")),
                        cursor.getString(cursor.getColumnIndexOrThrow("reg_id"))
                );
                list.add(s);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }
    public List<Teacher> getAllTeachers() {
        List<Teacher> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM teachers", null);

        if (cursor.moveToFirst()) {
            do {
                Teacher t = new Teacher(
                        cursor.getInt(cursor.getColumnIndexOrThrow("teacher_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("email")),
                        cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                        cursor.getString(cursor.getColumnIndexOrThrow("username")),
                        cursor.getString(cursor.getColumnIndexOrThrow("password"))
                );
                list.add(t);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM courses", null);

        if (cursor.moveToFirst()) {
            do {
                Course c = new Course(
                        cursor.getInt(cursor.getColumnIndexOrThrow("course_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("course_code")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("credit")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("teacher_id"))
                );

                list.add(c);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    public boolean assignStudentToCourse(int studentId, int courseId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if already exists
        Cursor cursor = db.rawQuery(
                "SELECT * FROM enrollment WHERE student_id = ? AND course_id = ?",
                new String[]{String.valueOf(studentId), String.valueOf(courseId)}
        );

        if (cursor.moveToFirst()) {
            cursor.close();
            return false; // already assigned
        }
        cursor.close();

        ContentValues cv = new ContentValues();
        cv.put("student_id", studentId);
        cv.put("course_id", courseId);

        long result = db.insert("enrollment", null, cv);
        return result != -1;
    }

    public boolean assignTeacherToCourse(int courseId, int teacherId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("teacher_id", teacherId);

        int rows = db.update("courses", cv, "course_id=?", new String[]{String.valueOf(courseId)});
        return rows > 0;
    }

    public Map<Integer, String> getStudentMap() {
        Map<Integer, String> map = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT student_id, name FROM students", null);

        if (cursor.moveToFirst()) {
            do {
                map.put(cursor.getInt(0), cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return map;
    }

    public Map<Integer, String> getTeacherMap() {
        Map<Integer, String> map = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT teacher_id, name FROM teachers", null);

        if (cursor.moveToFirst()) {
            do {
                map.put(cursor.getInt(0), cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return map;
    }

    public Map<Integer, String> getCourseMap() {
        Map<Integer, String> map = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT course_id, title FROM courses", null);

        if (cursor.moveToFirst()) {
            do {
                map.put(cursor.getInt(0), cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return map;
    }

    /*crud methods*/
    public boolean addStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", s.getName());
        cv.put("email", s.getEmail());
        cv.put("phone", s.getPhone());
        cv.put("password", s.getPassword());
        cv.put("reg_id", s.getRegId());

        long result = db.insert("students", null, cv);
        return result != -1;
    }
    public boolean updateStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", s.getName());
        cv.put("email", s.getEmail());
        cv.put("phone", s.getPhone());
        cv.put("password", s.getPassword());
        cv.put("reg_id", s.getRegId());

        int result = db.update("students", cv, "student_id=?",
                new String[]{String.valueOf(s.getId())});

        return result > 0;
    }
    public boolean deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete("students", "student_id=?",
                new String[]{String.valueOf(studentId)});

        return result > 0;
    }
    public boolean addTeacher(Teacher t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", t.getName());
        cv.put("email", t.getEmail());
        cv.put("phone", t.getPhone());
        cv.put("username", t.getUsername());
        cv.put("password", t.getPassword());

        long result = db.insert("teachers", null, cv);
        return result != -1;
    }
    public boolean updateTeacher(Teacher t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", t.getName());
        cv.put("email", t.getEmail());
        cv.put("phone", t.getPhone());
        cv.put("username", t.getUsername());
        cv.put("password", t.getPassword());

        int result = db.update("teachers", cv, "teacher_id=?",
                new String[]{String.valueOf(t.getId())});

        return result > 0;
    }
    public boolean deleteTeacher(int teacherId) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete("teachers", "teacher_id=?",
                new String[]{String.valueOf(teacherId)});

        return result > 0;
    }
    public Course getCourseById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM courses WHERE course_id=?",
                new String[]{String.valueOf(id)});

        if (c.moveToFirst()) {
            Course course = new Course(
                    c.getInt(c.getColumnIndexOrThrow("course_id")),
                    c.getString(c.getColumnIndexOrThrow("name")),
                    c.getString(c.getColumnIndexOrThrow("code")),
                    c.getDouble(c.getColumnIndexOrThrow("credit")),
                    c.getInt(c.getColumnIndexOrThrow("teacher_id"))
            );
            c.close();
            return course;
        }

        c.close();
        return null;
    }
    public boolean addCourse(Course c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("title", c.getTitle());
        cv.put("course_code", c.getCourseCode());
        cv.put("credit", c.getCredit());
        cv.put("teacher_id", c.getTeacherId()); // can be -1 if no teacher yet

        long result = db.insert("courses", null, cv);
        return result != -1;
    }
    public boolean updateCourse(Course c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("title", c.getTitle());
        cv.put("course_code", c.getCourseCode());
        cv.put("credit", c.getCredit());
        cv.put("teacher_id", c.getTeacherId());

        int result = db.update("courses", cv, "course_id=?",
                new String[]{String.valueOf(c.getCourseId())});

        return result > 0;
    }
    public boolean deleteCourse(int courseId) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete("courses", "course_id=?",
                new String[]{String.valueOf(courseId)});

        return result > 0;
    }
    // ===== MyDatabaseHelper additions =====

    // 1. get courses NOT assigned to a given student
    public List<Course> getUnassignedCoursesForStudent(int studentId) {
        List<Course> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_COURSE + " WHERE course_id NOT IN (" +
                "SELECT course_id FROM " + TABLE_ENROLLMENT + " WHERE student_id = ?)";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(studentId)});

        if (c.moveToFirst()) {
            do {
                Course course = new Course(
                        c.getInt(c.getColumnIndexOrThrow("course_id")),
                        c.getString(c.getColumnIndexOrThrow("title")),
                        c.getString(c.getColumnIndexOrThrow("course_code")),
                        c.getDouble(c.getColumnIndexOrThrow("credit")),
                        c.isNull(c.getColumnIndexOrThrow("teacher_id")) ? -1 :
                                c.getInt(c.getColumnIndexOrThrow("teacher_id"))
                );
                list.add(course);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    // 2. get courses that are unassigned to any teacher
    public List<Course> getUnassignedCoursesForTeacher() {
        List<Course> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // teacher_id IS NULL OR teacher_id = -1
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_COURSE +
                " WHERE teacher_id IS NULL OR teacher_id = -1", null);

        if (c.moveToFirst()) {
            do {
                Course course = new Course(
                        c.getInt(c.getColumnIndexOrThrow("course_id")),
                        c.getString(c.getColumnIndexOrThrow("title")),
                        c.getString(c.getColumnIndexOrThrow("course_code")),
                        c.getDouble(c.getColumnIndexOrThrow("credit")),
                        c.isNull(c.getColumnIndexOrThrow("teacher_id")) ? -1 :
                                c.getInt(c.getColumnIndexOrThrow("teacher_id"))
                );
                list.add(course);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    // 3. assign many courses to a student (batch)
    public boolean assignStudentToCourses(int studentId, List<Integer> courseIds) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (int courseId : courseIds) {
                // skip if already exists
                Cursor cursor = db.rawQuery(
                        "SELECT 1 FROM " + TABLE_ENROLLMENT + " WHERE student_id = ? AND course_id = ?",
                        new String[]{String.valueOf(studentId), String.valueOf(courseId)}
                );
                boolean already = cursor.moveToFirst();
                cursor.close();
                if (already) continue;

                ContentValues cv = new ContentValues();
                cv.put("student_id", studentId);
                cv.put("course_id", courseId);
                long res = db.insert(TABLE_ENROLLMENT, null, cv);
                if (res == -1) {
                    // rollback
                    db.endTransaction();
                    return false;
                }
            }
            db.setTransactionSuccessful();
            return true;
        } finally {
            db.endTransaction();
        }
    }

    // 4. assign many courses to a teacher (batch) — sets teacher_id for courses
    public boolean assignTeacherToCourses(int teacherId, List<Integer> courseIds) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (int courseId : courseIds) {
                ContentValues cv = new ContentValues();
                cv.put("teacher_id", teacherId);
                int rows = db.update(TABLE_COURSE, cv, "course_id=?", new String[]{String.valueOf(courseId)});
                if (rows <= 0) {
                    db.endTransaction();
                    return false;
                }
            }
            db.setTransactionSuccessful();
            return true;
        } finally {
            db.endTransaction();
        }
    }
    public List<Course> getCoursesByTeacher(int teacherId) {
        List<Course> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM courses WHERE teacher_id=?",
                new String[]{String.valueOf(teacherId)}
        );

        while (c.moveToNext()) {
            list.add(new Course(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getDouble(3),
                    c.getInt(4)
            ));
        }
        c.close();
        return list;
    }
    public List<Student> getStudentsByCourse(int courseId) {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT s.* FROM students s " +
                        "JOIN enrollment e ON s.student_id=e.student_id " +
                        "WHERE e.course_id=?",
                new String[]{String.valueOf(courseId)}
        );

        while (c.moveToNext()) {
            list.add(new Student(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(5),
                    c.getString(4)
            ));
        }
        c.close();
        return list;
    }
    public void saveOrUpdateResult(int studentId, int courseId,
                                   double marks, String grade) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("student_id", studentId);
        cv.put("course_id", courseId);
        cv.put("marks", marks);
        cv.put("grade", grade);

        long res = db.insert("results", null, cv);
        if (res == -1) {
            db.update("results", cv,
                    "student_id=? AND course_id=?",
                    new String[]{String.valueOf(studentId), String.valueOf(courseId)});
        }
    }
    public List<Map<Course, Result>> getCoursesWithResult(int studentId) {

        List<Map<Course, Result>> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT " +
                        "c.course_id, c.title, c.course_code, c.credit, c.teacher_id, " +
                        "r.marks, r.grade " +
                        "FROM courses c " +
                        "JOIN enrollment e ON c.course_id = e.course_id " +
                        "LEFT JOIN results r ON c.course_id = r.course_id " +
                        "AND r.student_id = ?",
                new String[]{String.valueOf(studentId)}
        );

        while (c.moveToNext()) {

            // ---- Course object ----
            Course course = new Course(
                    c.getInt(0),     // course_id
                    c.getString(1),  // title
                    c.getString(2),  // course_code
                    c.getDouble(3),  // credit
                    c.getInt(4)      // teacher_id
            );

            // ---- Result object (can be null) ----
            Result result = null;

            if (!c.isNull(6)) { // grade column
                result = new Result(
                        studentId,
                        course.getCourseId(),
                        c.isNull(5) ? null : c.getDouble(5), // marks
                        c.getString(6)                       // grade
                );
            }

            Map<Course, Result> map = new HashMap<>();
            map.put(course, result); // result may be null = Pending

            list.add(map);
        }

        c.close();
        return list;
    }





}
