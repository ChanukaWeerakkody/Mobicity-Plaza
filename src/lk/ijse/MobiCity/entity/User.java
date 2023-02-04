package lk.ijse.MobiCity.entity;

public class User {
    private String empId;
    private String systemRole;
    private String username;
    private String password;

    public User() {
    }

    public User(String empId, String systemRole, String username, String password) {
        this.empId = empId;
        this.systemRole = systemRole;
        this.username = username;
        this.password = password;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
