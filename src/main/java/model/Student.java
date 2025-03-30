package model;

import java.sql.Timestamp;

public class Student {
    private int student_id;
    private String name;
    private String student_code;
    private String email;
    private String password;
    private Timestamp createdAt;

    public Student(String name, String student_code, String email, String password) {
        this.name = name;
        this.student_code = student_code;
        this.email = email;
        this.password = password;
    }

    public int getIdStudent() {
        return student_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Student{id=" + student_id + ", name='" + name + "', email='" + email + "'}";
    }
}
