package pl.hansonq.models;

import pl.hansonq.models.EdiModel.HeaderModel;
import pl.hansonq.models.EdiModel.InvoicePartiesModel;
import pl.hansonq.models.EdiModel.LinesModel;
import pl.hansonq.models.EdiModel.SummaryModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Document-Invoice")
@XmlType(propOrder = {"headerModel", "invoicePartiesModel", "linesModel", "summaryModel"})
public class DocumentInvoiceModel {
    private HeaderModel headerModel;
    private InvoicePartiesModel invoicePartiesModel;
    private LinesModel linesModel;
    private SummaryModel summaryModel;

    public DocumentInvoiceModel(HeaderModel headerModel, InvoicePartiesModel invoicePartiesModel, LinesModel linesModel, SummaryModel summaryModel) {
        this.headerModel = headerModel;
        this.invoicePartiesModel = invoicePartiesModel;
        this.linesModel = linesModel;
        this.summaryModel = summaryModel;
    }

    public DocumentInvoiceModel() {
    }

    public HeaderModel getHeaderModel() {
        return headerModel;
    }

    @XmlElement(name = "Invoice-Header")
    public void setHeaderModel(HeaderModel headerModel) {
        this.headerModel = headerModel;
    }

    public InvoicePartiesModel getInvoicePartiesModel() {
        return invoicePartiesModel;
    }

    @XmlElement(name = "Invoice-Parties")
    public void setInvoicePartiesModel(InvoicePartiesModel invoicePartiesModel) {
        this.invoicePartiesModel = invoicePartiesModel;
    }

    public LinesModel getLinesModel() {
        return linesModel;
    }

    @XmlElement(name = "Invoice-Lines")
    public void setLinesModel(LinesModel linesModel) {
        this.linesModel = linesModel;
    }

    public SummaryModel getSummaryModel() {
        return summaryModel;
    }

    @XmlElement(name = "Invoice-Summary")
    public void setSummaryModel(SummaryModel summaryModel) {
        this.summaryModel = summaryModel;
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
