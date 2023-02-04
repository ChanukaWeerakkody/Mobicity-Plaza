package lk.ijse.MobiCity.dto;

import java.time.LocalDate;

public class OrderDTO {
    String oId;
    LocalDate date;
    String cusId;
    String cusName;
    double orderTotal;

    public OrderDTO() {
    }

    public OrderDTO(String oId, LocalDate date, String cusId) {
        this.oId = oId;
        this.date = date;
        this.cusId = cusId;
    }

    public OrderDTO(String oId, LocalDate date, String cusId, String cusName, double orderTotal) {
        this.oId = oId;
        this.date = date;
        this.cusId = cusId;
        this.cusName = cusName;
        this.orderTotal = orderTotal;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "oId='" + oId + '\'' +
                ", date=" + date +
                ", cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
