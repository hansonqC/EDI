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
    private String supplierItemCode;
    private String unit;

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

    public String getSupplierItemCode() {
        return supplierItemCode;
    }

    public void setSupplierItemCode(String supplierItemCode) {
        this.supplierItemCode = supplierItemCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Kartoteka : " +
                "EAN  :  " + ean + "\n" +
                "Kod producenta / indeks  :  " + indeks + "\n" +
                "Nazwa kartoteki  :  " + kartName + "\n"+
                "Jednostka miary  :  " + unit + "\n"+
                "Cena netto  :  " + netPice + "\n" +
                "VAT  :  " + tax + "\n";

    }
}
