package pl.hansonq.models.InvoiceModel;

public class OrderModel {
    private String number;
    private String quantity;
    private int idKartoteka;

    public OrderModel() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getIdKartoteka() {
        return idKartoteka;
    }

    public void setIdKartoteka(int idKartoteka) {
        this.idKartoteka = idKartoteka;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "number='" + number + '\'' +
                 ", quantity='" + quantity + '\'' +
                '}';
    }

}