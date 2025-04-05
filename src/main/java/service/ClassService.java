package service;

import dao.ClassDAO;
import model.ClassRoom;
import java.util.List;

public class ClassService {
    private ClassDAO classDAO = new ClassDAO();

    public List<ClassRoom> getAllClasses() {
        return classDAO.getAllClasses();
    }

    public void createClass(String classId, String courseId, String teacherId, int maxStudents) {
        ClassRoom c = new ClassRoom(classId, courseId, teacherId, maxStudents);
        classDAO.addClass(c);
    }

    public void removeClass(String classId) {
        classDAO.deleteClass(classId);
    }

    public void updateClass(String classId, String courseId, String teacherId, int maxStudents) {
        ClassRoom c = new ClassRoom(classId, courseId, teacherId, maxStudents);
        classDAO.updateClass(c);
    }
}
