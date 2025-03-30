package model;

public class Admin {
    int admin_id;
    String name;
    boolean superAdmin;
    String password;
    public Admin(String name, boolean superAdmin, String password) {
        this.name = name;
        this.superAdmin = superAdmin;
        this.password = password;
    }
    public Admin(int admin_id, String name, String password) {
        this.admin_id = admin_id;
        this.name = name;
        this.password = password;
    }
    public Admin(int admin_id, String name, boolean superAdmin, String password) {
        this.admin_id = admin_id;
        this.name = name;
        this.superAdmin = superAdmin;
        this.password = password;
    }
    public int getAdmin_id() {
        return admin_id;
    }
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isSuperAdmin() {
        return superAdmin;
    }
    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
