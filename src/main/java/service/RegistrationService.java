package service;

import dao.RegistrationDAO;
import model.Registration;

import java.util.List;

public class RegistrationService {
    private RegistrationDAO dao = new RegistrationDAO();

    public List<Registration> getByStudentId(int studentId) {
        return dao.getRegistrationsByStudentId(studentId);
    }
}
