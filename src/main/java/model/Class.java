package model;

public class Class {
    private int classId;
    private int courseId;
    private int teacherId;
    private int maxStudents;

    // Constructor mặc định
    public Class() {
    }

    // Constructor với tham số
    public Class(int classId, int courseId, int teacherId, int maxStudents) {
        this.classId = classId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.maxStudents = maxStudents;
    }

    // Getter và Setter cho các thuộc tính
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    // Phương thức toString để in ra thông tin lớp học
    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", courseId=" + courseId +
                ", teacherId=" + teacherId +
                ", maxStudents=" + maxStudents +
                '}';
    }
}
