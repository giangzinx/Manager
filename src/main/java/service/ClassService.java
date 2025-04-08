package service;

import dao.ClassDAO;
import dao.InterfaceDAO;
import model.ClassRoom;

import java.util.ArrayList;

public class ClassService {
    private InterfaceDAO<ClassRoom> classDAO = new ClassDAO();

    public ArrayList<ClassRoom> getAllClasses() {
        return classDAO.selectAll();
    }

    public void createClass(String classId, String courseId, String teacherId, int maxStudents) {
        ClassRoom c = new ClassRoom(classId, courseId, teacherId, maxStudents);
        classDAO.add(c);
    }

    public void removeClass(String classId) {
        ClassRoom c = new ClassRoom(classId, null, null, 0);
        classDAO.delete(c);
    }

    public void updateClass(String classId, String courseId, String teacherId, int maxStudents) {
        ClassRoom c = new ClassRoom(classId, courseId, teacherId, maxStudents);
        classDAO.update(c);
    }

    public ClassRoom getClassById(String classId) {
        return classDAO.selectById(new ClassRoom(classId, null, null, 0));
    }

    public ArrayList<ClassRoom> getClassesByTeacherId(String teacherId) {
        return classDAO.selectByCondition(new ClassRoom(null, null, teacherId, 0));
    }

    public ClassRoom getClassByName(String name) {
        return classDAO.selectByName(name);
    }
}
