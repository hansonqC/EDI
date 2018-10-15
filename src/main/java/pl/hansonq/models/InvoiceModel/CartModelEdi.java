package pl.hansonq.models.InvoiceModel;

public class CartModelEdi {
    private String ean;
    private String netPice;
    private String grossPrice;
    private String quantity;
    private String supplierCode;
    private String tax;
    private String indeks;
    private String kartName;
    private String zamdostNumber;

    // private String netPriceWithoutFee;

    public CartModelEdi() {
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

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public String getKartName() {
        return kartName;
    }

    public void setKartName(String kartName) {
        this.kartName = kartName;
    }

    public String getZamdostNumber() {
        return zamdostNumber;
    }

    public void setZamdostNumber(String zamdostNumber) {
        this.zamdostNumber = zamdostNumber;
    }

    @Override
    public String toString() {
        return "CartModelEdi{" +
                "ean='" + ean + '\'' +
                ", netPice='" + netPice + '\'' +
                ", grossPrice='" + grossPrice + '\'' +
                ", quantity='" + quantity + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", tax='" + tax + '\'' +
                ", indeks='" + indeks + '\'' +
                ", kartName='" + kartName + '\'' +
                ", zamdostNumber='" + zamdostNumber + '\'' +
                '}';
    }
}
