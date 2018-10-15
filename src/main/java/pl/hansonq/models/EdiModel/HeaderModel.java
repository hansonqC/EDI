package pl.hansonq.models.EdiModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Invoice-Header")
@XmlType(propOrder = {"invoiceNumber", "invoiceDate", "salesDate", "invoiceCurrency", "invoicePaymentDueDate", "invoicePaymentTerms", "invoicePaymentType", "documentFunctionCode"})
public class HeaderModel {
    private String invoiceNumber;
    private String invoiceDate;
    private String salesDate;
    private String invoiceCurrency;
    private String invoicePaymentDueDate;
    private String invoicePaymentTerms;
    private String invoicePaymentType;
    private String documentFunctionCode;

    public HeaderModel(String invoiceNumber, String invoiceDate, String salesDate, String invoiceCurrency, String invoicePaymentDueDate, String invoicePaymentTerms, String invoicePaymentType, String documentFunctionCode) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.salesDate = salesDate;
        this.invoiceCurrency = invoiceCurrency;
        this.invoicePaymentDueDate = invoicePaymentDueDate;
        this.invoicePaymentTerms = invoicePaymentTerms;
        this.invoicePaymentType = invoicePaymentType;
        this.documentFunctionCode = documentFunctionCode;
    }

    public HeaderModel() {
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @XmlElement(name = "InvoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    @XmlElement(name = "InvoiceDate")
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSalesDate() {
        return salesDate;
    }

    @XmlElement(name = "SalesDate")
    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public String getInvoiceCurrency() {
        return invoiceCurrency;
    }

    @XmlElement(name = "InvoiceCurrency")
    public void setInvoiceCurrency(String invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    public String getInvoicePaymentDueDate() {
        return invoicePaymentDueDate;
    }

    @XmlElement(name = "InvoicePaymentDueDate")
    public void setInvoicePaymentDueDate(String invoicePaymentDueDate) {
        this.invoicePaymentDueDate = invoicePaymentDueDate;
    }

    public String getInvoicePaymentTerms() {
        return invoicePaymentTerms;
    }

    @XmlElement(name = "InvoicePaymentTerms")
    public void setInvoicePaymentTerms(String invoicePaymentTerms) {
        this.invoicePaymentTerms = invoicePaymentTerms;
    }

    public String getInvoicePaymentType() {
        return invoicePaymentType;
    }

    @XmlElement(name = "InvoicePaymentType")
    public void setInvoicePaymentType(String invoicePaymentType) {
        this.invoicePaymentType = invoicePaymentType;
    }

    public String getDocumentFunctionCode() {
        return documentFunctionCode;
    }

    @XmlElement(name = "DocumentFunctionCode")
    public void setDocumentFunctionCode(String documentFunctionCode) {
        this.documentFunctionCode = documentFunctionCode;
    }

    @Override
    public String toString() {
        return "HeaderModel{" +"\n"+
                "invoiceNumber='" + invoiceNumber + '\'' +"\n"+
                ", invoiceDate='" + invoiceDate + '\'' +"\n"+
                ", salesDate='" + salesDate + '\'' +"\n"+
                ", invoiceCurrency='" + invoiceCurrency + '\'' +"\n"+
                ", invoicePaymentDueDate='" + invoicePaymentDueDate + '\'' +"\n"+
                ", invoicePaymentTerms='" + invoicePaymentTerms + '\'' +"\n"+
                ", invoicePaymentType='" + invoicePaymentType + '\'' +"\n"+
                ", documentFunctionCode='" + documentFunctionCode + '\'' +"\n"+
                '}';
    }
}
