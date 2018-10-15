package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Line-Item")
@XmlType(propOrder = {"lineNumber", "EAN", "supplierItemCode", "itemDescription", "invoiceQuantity", "unitOfMeasure", "invoiceUnitNetPrice", "taxRate", "taxCategoryCode", "taxAmount", "netAmount","productFeeDetailsModel"})
public class LineItemModel {
    private String lineNumber;
    private String EAN;
    private String supplierItemCode;
    private String itemDescription;
    private String invoiceQuantity;
    private String unitOfMeasure;
    private String invoiceUnitNetPrice;
    private String taxRate;
    private String taxCategoryCode;
    private String taxAmount;
    private String netAmount;
    private ProductFeeDetailsModel productFeeDetailsModel;



    public LineItemModel() {
    }

    public String getLineNumber() {
        return lineNumber;
    }

    @XmlElement(name = "LineNumber")
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getEAN() {
        return EAN;
    }

    @XmlElement(name = "EAN")
    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getSupplierItemCode() {
        return supplierItemCode;
    }

    @XmlElement(name = "SupplierItemCode")
    public void setSupplierItemCode(String supplierItemCode) {
        this.supplierItemCode = supplierItemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    @XmlElement(name = "ItemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getInvoiceQuantity() {
        return invoiceQuantity;
    }

    @XmlElement(name = "InvoiceQuantity")
    public void setInvoiceQuantity(String invoiceQuantity) {
        this.invoiceQuantity = invoiceQuantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @XmlElement(name = "UnitOfMeasure")
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getInvoiceUnitNetPrice() {
        return invoiceUnitNetPrice;
    }

    @XmlElement(name = "InvoiceUnitNetPrice")
    public void setInvoiceUnitNetPrice(String invoiceUnitNetPrice) {
        this.invoiceUnitNetPrice = invoiceUnitNetPrice;
    }

    public String getTaxRate() {
        return taxRate;
    }

    @XmlElement(name = "TaxRate")
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxCategoryCode() {
        return taxCategoryCode;
    }

    @XmlElement(name = "TaxCategoryCode")
    public void setTaxCategoryCode(String taxCategoryCode) {
        this.taxCategoryCode = taxCategoryCode;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    @XmlElement(name = "TaxAmount")
    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getNetAmount() {
        return this.netAmount;
    }

    @XmlElement(name = "NetAmount")
    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public ProductFeeDetailsModel getProductFeeDetailsModel() {
        return productFeeDetailsModel;
    }
    @XmlElement(name = "ProductFeeDetails",required = false)
    public void setProductFeeDetailsModel(ProductFeeDetailsModel productFeeDetailsModel) {
        this.productFeeDetailsModel = productFeeDetailsModel;
    }

    @Override
    public String toString() {
        return "LineItemModel{" +
                "lineNumber='" + lineNumber + '\'' +
                ", EAN='" + EAN + '\'' +
                ", supplierItemCode='" + supplierItemCode + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", invoiceQuantity='" + invoiceQuantity + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", invoiceUnitNetPrice='" + invoiceUnitNetPrice + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", taxCategoryCode='" + taxCategoryCode + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", netAmount='" + netAmount + '\'' +
                ", productFeeDetailsModel=" + productFeeDetailsModel +
                '}';
    }
}
