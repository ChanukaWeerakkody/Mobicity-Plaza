package lk.ijse.MobiCity.entity;

public class Payment {
    private String orderId;
    private String cusId;
    private String billedDate;
    private double total;
    private double payment;
    private double balance;

    public Payment() {
    }

    public Payment(String orderId, String cusId, String billedDate, double total, double payment, double balance) {
        this.orderId = orderId;
        this.cusId = cusId;
        this.billedDate = billedDate;
        this.total = total;
        this.payment = payment;
        this.balance = balance;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(String billedDate) {
        this.billedDate = billedDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
