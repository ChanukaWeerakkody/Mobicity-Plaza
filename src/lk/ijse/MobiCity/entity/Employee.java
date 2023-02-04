package lk.ijse.MobiCity.entity;

public class Employee {
    private String empId;
    private String name;
    private String address;
    private int contact;

    public Employee() {
    }

    public Employee(String empId, String name, String address, int contact) {
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
}
