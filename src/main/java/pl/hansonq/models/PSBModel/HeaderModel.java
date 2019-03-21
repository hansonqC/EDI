package pl.hansonq.models.PSBModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "InvoiceHeader")
@XmlType(propOrder = {"applicationModel", "documentID", "priceType", "number", "invoiceDate", "salesDate", "currency",
        "paymentDueDate","paymentTerms","documentType","documentFunctionCode","paymentName","deliveryModel"})
public class HeaderModel {
    private ApplicationModel applicationModel;
    private String documentID;
    private String priceType;
    private String number;
    private String invoiceDate;
    private String salesDate;
    private String currency;
    private String paymentDueDate;
    private String paymentTerms;
    private String documentType;
    private String documentFunctionCode;
    private String paymentName;
    private DeliveryModel deliveryModel;

    public HeaderModel() {
    }

    public ApplicationModel getApplicationModel() {
        return applicationModel;
    }
    @XmlElement(name = "Application")
    public void setApplicationModel(ApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
    }

    public String getDocumentID() {
        return documentID;
    }
    @XmlElement(name = "DocumentID")
    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getPriceType() {
        return priceType;
    }
    @XmlElement(name = "PriceType")
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getNumber() {
        return number;
    }
    @XmlElement(name = "Number")
    public void setNumber(String number) {
        this.number = number;
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

    public String getCurrency() {
        return currency;
    }
    @XmlElement(name = "Currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }
    @XmlElement(name = "PaymentDueDate")
    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }
    @XmlElement(name = "PaymentTerms")
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDocumentType() {
        return documentType;
    }
    @XmlElement(name = "DocumentType")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentFunctionCode() {
        return documentFunctionCode;
    }
    @XmlElement(name = "DocumentFunctionCode")
    public void setDocumentFunctionCode(String documentFunctionCode) {
        this.documentFunctionCode = documentFunctionCode;
    }

    public String getPaymentName() {
        return paymentName;
    }
    @XmlElement(name = "PaymentName")
    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public DeliveryModel getDeliveryModel() {
        return deliveryModel;
    }
    @XmlElement(name = "Delivery")
    public void setDeliveryModel(DeliveryModel deliveryModel) {
        this.deliveryModel = deliveryModel;
    }

    @Override
    public String toString() {
        return "HeaderModel{" +
                "applicationModel=" + applicationModel +
                ", documentID='" + documentID + '\'' +
                ", priceType='" + priceType + '\'' +
                ", number='" + number + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", salesDate='" + salesDate + '\'' +
                ", currency='" + currency + '\'' +
                ", paymentDueDate='" + paymentDueDate + '\'' +
                ", paymentTerms='" + paymentTerms + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentFunctionCode='" + documentFunctionCode + '\'' +
                ", paymentName='" + paymentName + '\'' +
                ", deliveryModel=" + deliveryModel +
                '}';
    }
}
