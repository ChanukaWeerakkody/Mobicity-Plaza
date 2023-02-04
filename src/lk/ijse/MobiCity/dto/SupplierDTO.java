package lk.ijse.MobiCity.dto;

public class SupplierDTO {
    private String supId;
    private String name;
    private String itemName;
    private String brand;
    private int qty;
    private double price;

    public SupplierDTO() {
    }

    public SupplierDTO(String supId, String name, String itemName, String brand, int qty, double price) {
        this.supId = supId;
        this.name = name;
        this.itemName = itemName;
        this.brand = brand;
        this.qty = qty;
        this.price = price;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "supId='" + supId + '\'' +
                ", name='" + name + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
