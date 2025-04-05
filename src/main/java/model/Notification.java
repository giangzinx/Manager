package model;

import java.sql.Timestamp;

public class Notification {
    private int id;
    private int senderId;
    private String senderRole;
    private String message;
    private Timestamp createdAt;

    // Constructor dùng để đọc dữ liệu
    public Notification(int id, int senderId, String senderRole, String message, Timestamp createdAt) {
        this.id = id;
        this.senderId = senderId;
        this.senderRole = senderRole;
        this.message = message;
        this.createdAt = createdAt;
    }

    // Constructor dùng để thêm mới
    public Notification(int senderId, String senderRole, String message) {
        this.senderId = senderId;
        this.senderRole = senderRole;
        this.message = message;
    }

    // Getters
    public int getId() { return id; }
    public int getSenderId() { return senderId; }
    public String getSenderRole() { return senderRole; }
    public String getMessage() { return message; }
    public Timestamp getCreatedAt() { return createdAt; }
}
