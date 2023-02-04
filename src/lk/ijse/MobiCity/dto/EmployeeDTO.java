package lk.ijse.MobiCity.dto;

public class EmployeeDTO {
    private String empId;
    private String name;
    private String address;
    private int contact;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String empId, String name, String address, int contact) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                '}';
    }
}
