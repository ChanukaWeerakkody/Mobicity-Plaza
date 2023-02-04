package lk.ijse.MobiCity.entity;

public class Item {
    private String itemId;
    private String name;
    private String brand;
    private int qty;
    private double price;

    public Item() {
    }

    public Item(String itemId, String name, String brand, int qty, double price) {
        this.itemId = itemId;
        this.name = name;
        this.brand = brand;
        this.qty = qty;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
