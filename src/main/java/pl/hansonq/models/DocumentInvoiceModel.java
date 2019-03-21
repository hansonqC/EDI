package pl.hansonq.models;

import pl.hansonq.models.PSBModel.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "DocumentInvoice")
@XmlType(propOrder = {"headerModel", "invoicePartiesModel", "linesModel", "summaryModel","invoiceInfosModel"})
public class DocumentInvoiceModel {
    private HeaderModel headerModel;
    private InvoicePartiesModel invoicePartiesModel;
    private LinesModel linesModel;
    private SummaryModel summaryModel;
    private InvoiceInfosModel invoiceInfosModel;



    public DocumentInvoiceModel() {
    }

    public HeaderModel getHeaderModel() {
        return headerModel;
    }

    @XmlElement(name = "InvoiceHeader")
    public void setHeaderModel(HeaderModel headerModel) {
        this.headerModel = headerModel;
    }

    public InvoicePartiesModel getInvoicePartiesModel() {
        return invoicePartiesModel;
    }

    @XmlElement(name = "InvoiceParties")
    public void setInvoicePartiesModel(InvoicePartiesModel invoicePartiesModel) {
        this.invoicePartiesModel = invoicePartiesModel;
    }

    public LinesModel getLinesModel() {
        return linesModel;
    }

    @XmlElement(name = "InvoiceLines")
    public void setLinesModel(LinesModel linesModel) {
        this.linesModel = linesModel;
    }

    public SummaryModel getSummaryModel() {
        return summaryModel;
    }

    @XmlElement(name = "InvoiceSummary")
    public void setSummaryModel(SummaryModel summaryModel) {
        this.summaryModel = summaryModel;
    }

    public InvoiceInfosModel getInvoiceInfosModel() {
        return invoiceInfosModel;
    }
    @XmlElement(name = "InvoiceInfos")
    public void setInvoiceInfosModel(InvoiceInfosModel invoiceInfosModel) {
        this.invoiceInfosModel = invoiceInfosModel;
    }

    @Override
    public String toString() {
        return "DocumentInvoiceModel{" +"\n"+
                "headerModel=" + headerModel +"\n"+
                ", invoicePartiesModel=" + invoicePartiesModel +"\n"+
                ", linesModel=" + linesModel +"\n"+
                ", summaryModel=" + summaryModel +"\n"+
                '}';
    }
}
