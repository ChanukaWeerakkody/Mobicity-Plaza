package lk.ijse.MobiCity.dto;

public class CustomerDTO {
    private String cusId;
    private String name;
    private String address;
    private int contact;

    public CustomerDTO() {
    }

    public CustomerDTO(String cusId, String name, String address, int contact) {
        this.cusId = cusId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
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
        return "CustomerDTO{" +
                "cusId='" + cusId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                '}';
    }
}
