package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Detail")
@XmlType(propOrder = {"lineNumber", "ean", "supplierItemCode", "description", "type", "quantity",
        "unitOfMeasure", "unitPrice", "percentDiscount", "unitPriceAfterDiscount",
        "taxRate", "taxSymbol", "taxCategoryCode", "productIndexExt", "producerIndexExt", "taxAmount", "netAmount"})
public class DetailModel {
    private String lineNumber;
    private String ean;
    private String supplierItemCode;
    private String description;
    private String type;
    private String quantity;
    private String unitOfMeasure;
    private String unitPrice;
    private String percentDiscount;
    private String unitPriceAfterDiscount;
    private String taxRate;
    private String taxSymbol;
    private String taxCategoryCode;
    private String productIndexExt;
    private String producerIndexExt;
    private String taxAmount;
    private String netAmount;

    public DetailModel() {
    }

    public String getLineNumber() {
        return lineNumber;
    }

    @XmlElement(name = "LineNumber")
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getEan() {
        return ean;
    }

    @XmlElement(name = "EAN")
    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getSupplierItemCode() {
        return supplierItemCode;

    }
    @XmlElement(name = "SupplierItemCode")
    public void setSupplierItemCode(String supplierItemCode) {
        this.supplierItemCode = supplierItemCode;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "Type")
    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    @XmlElement(name = "Quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @XmlElement(name = "UnitOfMeasure")
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    @XmlElement(name = "UnitPrice")
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPercentDiscount() {
        return percentDiscount;
    }

    @XmlElement(name = "PercentDiscount")
    public void setPercentDiscount(String percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public String getUnitPriceAfterDiscount() {
        return unitPriceAfterDiscount;
    }

    @XmlElement(name = "UnitPriceAfterDiscount")
    public void setUnitPriceAfterDiscount(String unitPriceAfterDiscount) {
        this.unitPriceAfterDiscount = unitPriceAfterDiscount;
    }

    public String getTaxRate() {
        return taxRate;
    }

    @XmlElement(name = "TaxRate")
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxSymbol() {
        return taxSymbol;
    }

    @XmlElement(name = "TaxSymbol")
    public void setTaxSymbol(String taxSymbol) {
        this.taxSymbol = taxSymbol;
    }

    public String getTaxCategoryCode() {
        return taxCategoryCode;
    }

    @XmlElement(name = "TaxCategoryCode")
    public void setTaxCategoryCode(String taxCategoryCode) {
        this.taxCategoryCode = taxCategoryCode;
    }

    public String getProductIndexExt() {
        return productIndexExt;
    }

    @XmlElement(name = "ProductIndexExt")
    public void setProductIndexExt(String productIndexExt) {
        this.productIndexExt = productIndexExt;
    }

    public String getProducerIndexExt() {
        return producerIndexExt;
    }

    @XmlElement(name = "ProducerIndexExt")
    public void setProducerIndexExt(String producerIndexExt) {
        this.producerIndexExt = producerIndexExt;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    @XmlElement(name = "TaxAmount")
    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getNetAmount() {
        return netAmount;
    }

    @XmlElement(name = "NetAmount")
    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    @Override
    public String toString() {
        return "DetailModel{" +
                "lineNumber='" + lineNumber + '\'' +
                ", ean='" + ean + '\'' +
                ", supplierItemCode='" + supplierItemCode + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", percentDiscount='" + percentDiscount + '\'' +
                ", unitPriceAfterDiscount='" + unitPriceAfterDiscount + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", taxSymbol='" + taxSymbol + '\'' +
                ", taxCategoryCode='" + taxCategoryCode + '\'' +
                ", productIndexExt='" + productIndexExt + '\'' +
                ", producerIndexExt='" + producerIndexExt + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", netAmount='" + netAmount + '\'' +
                '}';
    }
}