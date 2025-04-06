package service;

import dao.NotificationDAO;
import model.Notification;
import java.util.List;

public class NotificationService {
    private NotificationDAO dao = new NotificationDAO();

    public void sendNotification(int senderId, String role, String message) {
        Notification noti = new Notification(senderId, role, message);
        dao.insertNotification(noti);
    }

    public List<Notification> getAllNotifications() {
        return dao.getAllNotifications();
    }

    public void updateNotification(int id, String newMessage) {
        dao.updateNotification(id, newMessage);
    }

    public void deleteNotification(int id) {
        dao.deleteNotification(id);
    }
}
