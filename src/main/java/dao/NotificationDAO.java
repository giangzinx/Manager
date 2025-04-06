package dao;

import model.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public void insertNotification(Notification noti) {
        String sql = "INSERT INTO notifications (sender_id, sender_role, message) VALUES (?, ?, ?)";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, noti.getSenderId());
            stmt.setString(2, noti.getSenderRole());
            stmt.setString(3, noti.getMessage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Notification> getAllNotifications() {
        List<Notification> list = new ArrayList<>();
        String sql = "SELECT * FROM notifications ORDER BY created_at DESC";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Notification noti = new Notification(
                        rs.getInt("notification_id"),
                        rs.getInt("sender_id"),
                        rs.getString("sender_role"),
                        rs.getString("message"),
                        rs.getTimestamp("created_at")
                );
                list.add(noti);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Cập nhật thông báo
    public void updateNotification(int id, String newMessage) {
        String sql = "UPDATE notifications SET message = ? WHERE notification_id = ?";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, newMessage);
            stmt.setInt(2, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("❌ Không tìm thấy thông báo để cập nhật!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa thông báo
    public void deleteNotification(int id) {
        String sql = "DELETE FROM notifications WHERE notification_id = ?";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("❌ Không tìm thấy thông báo để xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
