package lk.ijse.MobiCity.dto;

public class UserDTO {
    private String empId;
    private String systemRole;
    private String username;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String empId, String systemRole, String username, String password) {
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "empId='" + empId + '\'' +
                ", systemRole='" + systemRole + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
