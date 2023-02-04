package lk.ijse.MobiCity.dto;

import javafx.scene.control.Button;

public class PlaceOrderDTO {
    private String code;
    private String description;
    private int qty;
    private double unitPrice;
    private double total;
    private Button btnDelete;

    public PlaceOrderDTO(String code, String description, int qty, double unitPrice, double total) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public PlaceOrderDTO() {
    }

    public PlaceOrderDTO(String code, String description, int qty, double unitPrice, double total, Button btnDelete) {
        this.setCode(code);
        this.setDescription(description);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setTotal(total);
        this.setBtnDelete(btnDelete);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
}
