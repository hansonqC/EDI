package pl.hansonq.models.InvoiceModel;

public class CartModel {
    private String ean;
    private String netPice;
    private String grossPrice;
    private String quantity;
    private String supplierCode;
    private String tax;

    public CartModel(String ean, String netPice, String grossPrice, String quantity, String supplierCode, String tax) {
        this.ean = ean;
        this.netPice = netPice;
        this.grossPrice = grossPrice;
        this.quantity = quantity;
        this.supplierCode = supplierCode;
        this.tax = tax;
    }

    public CartModel() {
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getNetPice() {
        return netPice;
    }

    public void setNetPice(String netPice) {
        this.netPice = netPice;
    }

    public String getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(String grossPrice) {
        this.grossPrice = grossPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "ean='" + ean + '\'' +
                ", netPice='" + netPice + '\'' +
                ", grossPrice='" + grossPrice + '\'' +
                ", quantity='" + quantity + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", tax='" + tax + '\'' +
                '}';
    }
}
